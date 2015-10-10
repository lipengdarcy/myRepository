package com.runlion.shop.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runlion.shop.dao.BspJobLogMapper;
import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspProductsregionspriceLogMapper;
import com.runlion.shop.dao.BspProductsregionspriceMapper;
import com.runlion.shop.dao.BspProregionextpriceMapper;
import com.runlion.shop.entity.BspJobLog;
import com.runlion.shop.entity.BspJobLogExample;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProductsregionspriceLog;
import com.runlion.shop.entity.BspProductsregionspriceLogExample;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.service.region.RegionsPriceService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.service.region.RegionsService;

/*
 价格更新时候，不直接更新价格表（BspProductsregionsprice），而是在价格更新的日志表（BspProductsregionspriceLog）
 里插入记录，并设定生效时间， 同时插入待办任务表（BspJobLog），  系统根据这个时间自动在正确的时间点更新价格
 （更新BspProductsregionspriceLog的状态，即生效状态；更新BspProductsregionsprice的价格）
 */
@Service
public class PriceUpdateJobService {

	@Autowired
	private RegionsPriceService regionsPriceService;

	@Autowired
	private BspJobLogMapper bspJobLogMapper;

	@Autowired
	private BspProductsregionspriceLogMapper bspProductsregionspriceLogMapper;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	private BspProductsregionsMapper bspProductsregionsMapper;

	@Autowired
	private BspProductsregionspriceMapper bspProductsregionspriceMapper;

	@Autowired
	private BspProregionextpriceMapper bspProregionextpriceMapper;

	// 获取所有的任务列表(未处理的)
	public List<BspJobLog> getJobList() {
		BspJobLogExample e = new BspJobLogExample();
		e.or().andIseffectEqualTo("1");
		List<BspJobLog> list = bspJobLogMapper.selectByExample(e);
		return list;
	}

	// 删除任务(已处理的),假删除，只更新状态
	public int deleteJob(BspJobLog record) {
		// return bspJobLogMapper.deleteByPrimaryKey(id);
		record.setIseffect("0");
		return bspJobLogMapper.updateByPrimaryKeySelective(record);
	}

	// 新增任务(未处理的)
	public int insertJob(BspJobLog record) {
		return bspJobLogMapper.insertSelective(record);
	}

	// 新增价格日志
	public int insertPriceLog(BspProductsregionspriceLog record) {
		return bspProductsregionspriceLogMapper.insertSelective(record);
	}

	// 新增价格日志
	public int insertPriceLog(BspProductsregionsprice price, Date plantime) {
		BspProductsregionspriceLog record = BspProductsregionspriceLog
				.copyInfo(price);
		record.setPlaneffecttime(plantime);
		return this.insertPriceLog(record);
	}

	// 新增扩展价格日志
	public int insertExtPriceLog(BspProregionextprice price, Date plantime) {
		BspProductsregionspriceLog record = BspProductsregionspriceLog
				.copyInfo(price);
		record.setPlaneffecttime(plantime);
		return this.insertPriceLog(record);
	}

	// 获取价格日志id
	public int getProductsregionspriceLogId(int priceId, Date plantime) {
		BspProductsregionspriceLogExample e = new BspProductsregionspriceLogExample();

		e.createCriteria().andPlaneffecttimeEqualTo(plantime)
				.andPriceidEqualTo(priceId);
		List<BspProductsregionspriceLog> list = bspProductsregionspriceLogMapper
				.selectByExample(e);
		if (list == null || list.size() == 0)
			return -1;
		return list.get(0).getId();
	}

	// 获取价格日志id // last_insert_id()
	public int last_insert_id() {
		return bspProductsregionspriceLogMapper.last_insert_id();
	}

	// 获取价格日志
	public BspProductsregionspriceLog getProductsregionspriceLog(int id) {
		return bspProductsregionspriceLogMapper.selectByPrimaryKey(id);
	}

	// 更新价格状态为有效
	@Transactional
	public int updateProductsregionspriceLog(BspProductsregionspriceLog log) {
		// 更新BspProductsregionspriceLog的状态，即生效状态
		int count1 = bspProductsregionspriceLogMapper
				.updateByPrimaryKeySelective(log);

		int count2 = 0;
		if (count1 > 0) {
			// 更新BspProductsregionsprice的价格
			// 新增的价格
			if (log.getPriceid() == null || log.getPriceid() < 0) {
				BspProductsregionsprice price = BspProductsregionspriceLog
						.copyInfo(log);

				count2 = bspProductsregionspriceMapper.insertSelective(price);
				int priceId = this.last_insert_id();
				// 更新价格日志表的priceId字段
				log.setPriceid(priceId);
				bspProductsregionspriceLogMapper
						.updateByPrimaryKeySelective(log);
			}
			// 编辑的价格
			else {
				BspProductsregionsprice price = bspProductsregionspriceMapper
						.selectByPrimaryKey(log.getPriceid());

				price.setPrice(log.getPrice());
				count2 = bspProductsregionspriceMapper
						.updateByPrimaryKeySelective(price);
			}
		}
		return count1 + count2;
	}

	// 更新扩展价格状态为有效
	@Transactional
	public int updateProductsregionsextpriceLog(BspProductsregionspriceLog log) {
		// 更新BspProductsregionspriceLog的状态，即生效状态
		int count1 = bspProductsregionspriceLogMapper
				.updateByPrimaryKeySelective(log);

		int count2 = 0;
		if (count1 > 0) {
			// 更新BspProductsregionsprice的价格
			// 新增的价格
			if (log.getPriceid() == null || log.getPriceid() < 0) {
				BspProregionextprice price = BspProductsregionspriceLog
						.copyExtInfo(log);

				count2 = bspProregionextpriceMapper.insertSelective(price);
				int priceId = price.getId();
				// 更新价格日志表的priceId字段
				log.setPriceid(priceId);
				bspProductsregionspriceLogMapper
						.updateByPrimaryKeySelective(log);
			}
			// 编辑的价格
			else {
				BspProregionextprice price = bspProregionextpriceMapper
						.selectByPrimaryKey(log.getPriceid());

				price.setValue(log.getPrice());
				count2 = bspProregionextpriceMapper
						.updateByPrimaryKeySelective(price);
			}
		}
		return count1 + count2;
	}

	// 新增产品区域价格日志
	@Transactional
	public void addProductsRegionsPriceLog(Integer productRegionsId,
			Double price, String priceType, Date plantime) {
		BspProductsregionspriceLog record = new BspProductsregionspriceLog(
				productRegionsId, priceType, price, plantime);
		this.insertPriceLog(record);
		// 插入区域下级的价格日志
		this.addProductsRegionsPriceLogChildren(productRegionsId, price,
				priceType, plantime);
	}

	// 插入区域下级的价格日志
	public void addProductsRegionsPriceLogChildren(Integer productRegionsId,
			Double price, String priceType, Date plantime) {
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
			int priceId = regionsPriceService.getProductsRegionsPriceId(
					priceType, price, prid);
			if (prid > 0) {
				if (priceId < 0) {
					BspProductsregionspriceLog record = new BspProductsregionspriceLog(
							productRegionsId, priceType, price, plantime);
					this.insertPriceLog(record);
				}
			} else {
				// 插入BspProductsregions
				prid = regionsProductService.generateNewProductRegionId();
				String regionsName = regionsService.getResionsFullNameById(b
						.getRegionid());
				bspProductsregionsMapper
						.insertSelective(new BspProductsregions(prid,
								productId, b.getRegionid(), regionsName));

				priceId = regionsPriceService.getProductsRegionsPriceId(
						priceType, price, prid);
				if (priceId < 0) {
					BspProductsregionspriceLog record = new BspProductsregionspriceLog(
							productRegionsId, priceType, price, plantime);
					this.insertPriceLog(record);
				}

			}

		}

	}
}
