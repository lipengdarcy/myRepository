package com.runlion.shop.service.region;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspProductsregionsbrandMapper;
import com.runlion.shop.dao.BspProductsregionsbrandplaceMapper;
import com.runlion.shop.dao.BspProductsregionspriceLogMapper;
import com.runlion.shop.dao.BspProductsregionspriceMapper;
import com.runlion.shop.dao.BspProductsregionssaleaddressMapper;
import com.runlion.shop.dao.BspProregionextpriceMapper;
import com.runlion.shop.dao.BspRegionpriecedefunitsMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.entity.BspJobLog;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsExample;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProductsregionspriceExample;
import com.runlion.shop.entity.BspProductsregionspriceLog;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspProregionextpriceExample;
import com.runlion.shop.entity.BspRegionpriecedefunits;
import com.runlion.shop.entity.BspRegionpriecedefunitsExample;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.service.PriceUpdateJobService;
import com.runlion.shop.vo.RegionPriceVO;

@Service
public class RegionsPriceService {
	@Autowired
	private BspRegionsMapper regionsMapper;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private PriceUpdateJobService priceUpdateJobService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	private BspProductsregionsMapper bspProductsregionsMapper;

	@Autowired
	private BspProductsregionsbrandMapper bspProductsregionsbrandMapper;

	@Autowired
	private BspProductsregionsbrandplaceMapper bspProductsregionsbrandplaceMapper;

	@Autowired
	private BspProductsregionspriceMapper bspProductsregionspriceMapper;

	@Autowired
	private BspProductsregionssaleaddressMapper bspProductsregionssaleaddressMapper;

	@Autowired
	private BspRegionpriecedefunitsMapper bspRegionpriecedefunitsMapper;

	@Autowired
	private BspProductsregionspriceLogMapper bspProductsregionspriceLogMapper;

	@Autowired
	private BspProregionextpriceMapper bspProregionextpriceMapper;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	// 获取区域价格
	public BspProductsregionsprice getProductsregionspriceById(int id) {
		return bspProductsregionspriceMapper.selectByPrimaryKey(id);
	}

	// 区域价格的数量(包括产品价格、运费、装卸费)
	public int getProductRegionPriceCount(int type, Integer prid, Integer pid,
			int priceType, int areaid, String productName) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("PRICE_TYPE", priceType);
		par.put("PRODUCT_REGIONSID", prid);
		par.put("PRODUCT_ID", pid);
		par.put("TYPE", type);
		par.put("REGIONS_ID", areaid);
		par.put("PRODUCT_NAME", productName);
		return bspProductsregionspriceMapper.getProductRegionPriceCount(par);
	}

	// 区域价格(包括产品价格、运费、装卸费)
	public List<Map<String, Object>> getProductRegionPrice(int type,
			Integer prid, Integer pid, int priceType, int startNum,
			int limitNum, int areaid, String productName) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		par.put("PRICE_TYPE", priceType);
		par.put("TYPE", type);
		par.put("PRODUCT_REGIONSID", prid);
		par.put("PRODUCT_ID", pid);
		par.put("REGIONS_ID", areaid);
		par.put("PRODUCT_NAME", productName);
		return bspProductsregionspriceMapper.getProductRegionPrice(par);
	}

	// 判断ProductsRegionsPrice是否已存在
	public int getProductsRegionsPriceId(String priceType, double price,
			int prid) {
		BspProductsregionspriceExample e = new BspProductsregionspriceExample();
		e.createCriteria().andPricetypeEqualTo(priceType)
				.andProductregionsidEqualTo(prid);

		List<BspProductsregionsprice> list = bspProductsregionspriceMapper
				.selectByExample(e);
		if (list == null || list.size() == 0)
			return -1;
		else
			return list.get(0).getId();
	}

	// 获取价格列表（运费、装卸费）
	public List<BspProductsregionsprice> getProductsRegionsPrice(
			Integer productsRegionsId, String priceType) {
		BspProductsregionspriceExample bprre = new BspProductsregionspriceExample();
		bprre.createCriteria().andProductregionsidEqualTo(productsRegionsId)
				.andPricetypeEqualTo(priceType);
		return bspProductsregionspriceMapper.selectByExample(bprre);
	}

	/**
	 * 增加区域产品价格(单个)(包括价格、运费、装卸费)
	 * 
	 * @param priceInfor
	 * @return
	 */
	public int addProductsRegionsPrice(BspProductsregionsprice priceInfor) {
		return bspProductsregionspriceMapper.insertSelective(priceInfor);
	}

	// 插入区域下级的价格
	public void addProductsRegionsPriceChildren(Integer productRegionsId,
			Double price, String priceType) {
		BspProductsregions pr = bspProductsregionsMapper
				.selectByPrimaryKey(productRegionsId);
		int regionsId = pr.getRegionsid();
		int productId = pr.getProductid();

		List<BspRegions> list = regionsService
				.getResionsChildrenById(regionsId);
		for (BspRegions b : list) {
			// 判断是否已存在
			int prid = regionsProductService.getProductsRegionsId(productId,
					b.getRegionid());
			int priceId = getProductsRegionsPriceId(priceType, price, prid);
			if (prid > 0) {
				if (priceId < 0)
					bspProductsregionspriceMapper
							.insertSelective(new BspProductsregionsprice(prid,
									priceType, price));
			} else {
				// 插入BspProductsregions
				prid = regionsProductService.generateNewProductRegionId();
				String regionsName = regionsService.getResionsFullNameById(b
						.getRegionid());
				bspProductsregionsMapper
						.insertSelective(new BspProductsregions(prid,
								productId, b.getRegionid(), regionsName));

				// 插入BspProductsregionsPrice
				priceId = getProductsRegionsPriceId(priceType, price, prid);
				if (priceId < 0)
					bspProductsregionspriceMapper
							.insertSelective(new BspProductsregionsprice(prid,
									priceType, price));
			}

		}

	}

	// 增加区域产品价格(多个)
	@Transactional
	public void addProductsRegionsPrice(Integer productRegionsId, Double price,
			String priceType, Date planeffecttime, int effectnow) {

		// step 1: 保存新的价格信息到日志表，并插入job
		priceUpdateJobService.addProductsRegionsPriceLog(productRegionsId,
				price, priceType, planeffecttime);
		int pricelogId = priceUpdateJobService.last_insert_id(); // 价格log id

		// step 2: 立即生效，直接插入价格表
		if (effectnow == 1) {
			bspProductsregionspriceMapper
					.insertSelective(new BspProductsregionsprice(
							productRegionsId, priceType, price));
			// 更新价格日志表的priceId字段
			int priceId = priceUpdateJobService.last_insert_id(); // 价格id
			BspProductsregionspriceLog log = bspProductsregionspriceLogMapper
					.selectByPrimaryKey(pricelogId);
			log.setPriceid(priceId);
			bspProductsregionspriceLogMapper.updateByPrimaryKeySelective(log);

			// 插入区域下级的价格
			this.addProductsRegionsPriceChildren(productRegionsId, price,
					priceType);
		} else {
			priceUpdateJobService.insertJob(new BspJobLog(pricelogId));
		}
	}

	// 删除价格（运费、装卸费）
	public int deleteProductRegionsPrice(Integer proRegionId, String priceType) {
		BspProductsregionspriceExample bprre = new BspProductsregionspriceExample();
		bprre.createCriteria().andProductregionsidEqualTo(proRegionId)
				.andPricetypeEqualTo(priceType);
		return bspProductsregionspriceMapper.deleteByExample(bprre);
	}

	// 删除区域价格（单个）
	@Transactional
	public void deleteProductRegionsPrice(int id) {
		// 1.插入日志
		BspProductsregionsprice p = bspProductsregionspriceMapper
				.selectByPrimaryKey(id);

		Date now = new Date();
		BspProductsregionspriceLog record = BspProductsregionspriceLog
				.copyInfo(p);
		record.setPlaneffecttime(now);
		record.setEdittime(now);
		record.setEffecttime(now);
		priceUpdateJobService.insertPriceLog(record);

		// 2.删除价格
		bspProductsregionspriceMapper.deleteByPrimaryKey(id);

		BspRegionpriecedefunitsExample example = new BspRegionpriecedefunitsExample();
		example.createCriteria().andRegionpriceidEqualTo(id);
		bspRegionpriecedefunitsMapper.deleteByExample(example);
	}

	// 根据产品id批量删除价格（多个）
	public int deletePriceByProductId(int priceType, int pid) {
		// 1.插入日志
		List<BspProductsregionsprice> list = bspProductsregionspriceMapper
				.selectPriceByProductId(priceType, pid);
		for (BspProductsregionsprice p : list) {
			Date now = new Date();
			BspProductsregionspriceLog record = BspProductsregionspriceLog
					.copyInfo(p);
			record.setPlaneffecttime(now);
			record.setEdittime(now);
			record.setEffecttime(now);
			priceUpdateJobService.insertPriceLog(record);
		}
		// 2.删除价格
		return bspProductsregionspriceMapper.deletePriceByProductId(priceType,
				pid);
	}

	public void deleteProductsRegionsHasPid(String ids) {
		bspProductsregionspriceMapper.deleteProductsRegionsHasPid(ids);
	}

	// 删除区域价格（多个）
	public int priceBatchDelete(String ids) {
		// 先删除有产品关联的区域id
		this.deleteProductsRegionsHasPid(ids);
		BspProductsregionspriceExample bprpe = new BspProductsregionspriceExample();
		if (ids != null && !ids.trim().equals("")) {
			String[] idsArr = ids.split(",");
			List<Integer> idList = new ArrayList<Integer>();
			for (int i = 0; i < idsArr.length; i++) {
				String id = idsArr[i];
				// 1.插入日志
				BspProductsregionsprice p = bspProductsregionspriceMapper
						.selectByPrimaryKey(Integer.valueOf(id));
				Date now = new Date();
				BspProductsregionspriceLog record = BspProductsregionspriceLog
						.copyInfo(p);
				record.setPlaneffecttime(now);
				record.setEdittime(now);
				record.setEffecttime(now);
				priceUpdateJobService.insertPriceLog(record);

				idList.add(Integer.valueOf(id));
			}
			bprpe.createCriteria().andIdIn(idList);

			// 2.删除价格
			return bspProductsregionspriceMapper.deleteByExample(bprpe);
		} else {
			return 0;
		}
	}

	public int updateProductsRegionsPrice(BspProductsregionsprice record) {
		return bspProductsregionspriceMapper
				.updateByPrimaryKeySelective(record);
	}

	// 编辑区域产品价格(单个)
	@Transactional
	public void updateProductsRegionsPrice(Integer id, BigDecimal price,
			Date planeffecttime, int effectnow) {
		BspProductsregionsprice oldPrice = getProductsregionspriceById(id);
		// step 1: 保存原有的价格信息到日志表
		priceUpdateJobService.insertPriceLog(oldPrice, null);

		// step 2: 保存新的价格信息到日志表,同时插入job
		BspProductsregionsprice newPrice = oldPrice.clone();
		newPrice.setPrice(price);
		priceUpdateJobService.insertPriceLog(newPrice, planeffecttime);
		int pricelogid = priceUpdateJobService.last_insert_id();

		// step 3: 立即生效，直接更新价格表等相关表
		if (effectnow == 1) {
			bspProductsregionspriceMapper.updateByPrimaryKeySelective(newPrice);
		} else {
			priceUpdateJobService.insertJob(new BspJobLog(pricelogid));
		}

	}

	/**
	 * 编辑价格信息(包括运费、装卸费)
	 * 
	 * @param priceVo
	 * @param priceType
	 * @return
	 */
	@Transactional
	public boolean updateProductsRegionsPrice(RegionPriceVO priceVo,
			String priceType) {
		// 设置扩展价格的名字
		List<BspProregionextprice> oriextplist = priceVo.getExtPriceList();
		if (oriextplist != null) {
			for (int ei = 0; ei < oriextplist.size(); ei++) {
				BspProregionextprice extPrice = oriextplist.get(ei);
				if (extPrice.getType().equals("1")) {
					extPrice.setName(BspProregionextprice.STORE_PICK_NAME);
				} else if (extPrice.getType().equals("2")) {
					extPrice.setName(BspProregionextprice.FAC_PICK_NAME);
				} else if (extPrice.getType().equals("3")) {
					extPrice.setName(BspProregionextprice.SEND_HOME_NAME);
				}
			}
		}
		//
		List<BspProductsregions> proRegions = null;
		Integer productsRegionsId = priceVo.getPriid();
		if (productsRegionsId == null) {
			// 插入BspProductsregions
			productsRegionsId = regionsProductService
					.generateNewProductRegionId();
			String regionsName = regionsService.getResionsFullNameById(priceVo
					.getRegionId());
			BspProductsregionsExample bpe = new BspProductsregionsExample();
			bpe.createCriteria().andProductidEqualTo(priceVo.getPid())
					.andRegionsidEqualTo(priceVo.getRegionId());
			proRegions = bspProductsregionsMapper.selectByExample(bpe);
			if (proRegions == null || proRegions.isEmpty()) {
				bspProductsregionsMapper
						.insertSelective(new BspProductsregions(
								productsRegionsId, priceVo.getPid(), priceVo
										.getRegionId(), regionsName));
			} else {
				BspProductsregions proRegion = proRegions.get(0);
				productsRegionsId = proRegion.getId();
			}
		}

		Date planeffecttime = new Date();
		try {
			planeffecttime = sdf.parse(priceVo.getPlaneffecttime());
		} catch (ParseException e) {
			planeffecttime = new Date();
		}

		// step 1: 保存原有的价格信息到日志表
		List<BspProductsregionsprice> list = this.getProductsRegionsPrice(
				productsRegionsId, priceType);
		for (BspProductsregionsprice p : list)
			priceUpdateJobService.insertPriceLog(p, planeffecttime);

		List<BspProregionextprice> extList = this
				.getProregionExtPriceList(productsRegionsId);
		for (BspProregionextprice p : extList) {
			priceUpdateJobService.insertExtPriceLog(p, planeffecttime);
		}

		// step 2: 保存新的价格信息到日志表
		List<BspProductsregionsprice> priceList = priceVo.getPriceList();
		List<Integer> pricelogidList = new ArrayList<Integer>(priceList.size());
		for (BspProductsregionsprice p : priceList) {
			p.setProductregionsid(productsRegionsId);
			p.setPricetype(priceType);
			priceUpdateJobService.insertPriceLog(p, planeffecttime);
			// last_insert_id()
			int pricelogid = priceUpdateJobService.last_insert_id();
			pricelogidList.add(pricelogid);
		}

		List<BspProregionextprice> extpriceList = priceVo.getExtPriceList();
		List<Integer> extpricelogidList = new ArrayList<Integer>(
				extpriceList.size());
		for (BspProregionextprice p : extpriceList) {
			p.setProregionid(productsRegionsId);
			priceUpdateJobService.insertExtPriceLog(p, planeffecttime);
			// last_insert_id()
			int pricelogid = priceUpdateJobService.last_insert_id();
			extpricelogidList.add(pricelogid);
		}

		// step 3: 立即生效，直接更新价格表等相关表
		if (priceVo.getEffectnow() == 1) {

			regionsProductService.updateProductsRegions(productsRegionsId,
					priceVo.getPid(), priceVo.getRegionId(),
					priceVo.getRegionName(), priceVo.getStarthand(),
					priceVo.getStartship());

			this.deleteProductRegionsPrice(productsRegionsId, priceType);

			for (int pri = 0; pri < priceList.size(); pri++) {
				BspProductsregionsprice priceInfor = priceList.get(pri);
				priceInfor.setProductregionsid(productsRegionsId);
				priceInfor.setPricetype(priceType);
				this.addProductsRegionsPrice(priceInfor);
			}

			this.delPRDefUnits(productsRegionsId, priceType);

			List<BspRegionpriecedefunits> defUnitList = priceVo
					.getDefUnitList();
			for (int ui = 0; ui < defUnitList.size(); ui++) {
				BspRegionpriecedefunits defUnit = defUnitList.get(ui);
				if (defUnit.getUnitid() != null && defUnit.getUnitid() > 0) {
					defUnit.setRegionpriceid(productsRegionsId);
					defUnit.setPricetype(priceType);
					bspRegionpriecedefunitsMapper.insertSelective(defUnit);
				}
			}

			// 先删除产品区域关联的提货附加运费条目
			BspProregionextpriceExample bpee = new BspProregionextpriceExample();
			bpee.createCriteria().andProregionidEqualTo(productsRegionsId);
			bspProregionextpriceMapper.deleteByExample(bpee);
			// 插入新的产品区域关联的提货附加信息条目
			List<BspProregionextprice> extplist = priceVo.getExtPriceList();
			if (extplist != null) {
				for (int ei = 0; ei < extplist.size(); ei++) {
					bspProregionextpriceMapper
							.insertSelective(extplist.get(ei));
				}
			}

		} else {
			for (Integer pricelogid : pricelogidList)
				priceUpdateJobService.insertJob(new BspJobLog(pricelogid));
			for (Integer pricelogid : extpricelogidList)
				priceUpdateJobService.insertJob(new BspJobLog(pricelogid));
		}

		return true;
	}

	/**
	 * 删除产品区域的主键和价格类型关联的默认的区域单位列表
	 * 
	 * @param productsRegionsId
	 * @param priceType
	 * @return
	 */
	public boolean delPRDefUnits(Integer productsRegionsId, String priceType) {
		BspRegionpriecedefunitsExample brfe = new BspRegionpriecedefunitsExample();
		brfe.createCriteria().andRegionpriceidEqualTo(productsRegionsId)
				.andPricetypeEqualTo(priceType);
		bspRegionpriecedefunitsMapper.deleteByExample(brfe);
		return true;
	}

	// 批量更新区域价格(包括产品价格、运费、装卸费)
	public boolean updateProductRegionPriceList(
			List<BspProductsregionsprice> priceList, Date planeffecttime,
			int effectnow) {
		for (int i = 0; i < priceList.size(); i++) {
			BspProductsregionsprice p = (BspProductsregionsprice) priceList
					.get(i);
			updateProductsRegionsPrice(p.getId(), p.getPrice(), planeffecttime,
					effectnow);
			// bspProductsregionspriceMapper.updateByPrimaryKeySelective(record);
		}
		return true;
	}

	//
	public List<BspProregionextprice> getProregionExtPriceList(int regionid) {
		BspProregionextpriceExample bpee = new BspProregionextpriceExample();
		bpee.createCriteria().andProregionidEqualTo(regionid);
		return bspProregionextpriceMapper.selectByExample(bpee);
	}

}
