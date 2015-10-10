package com.runlion.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.runlion.shop.dao.BspAreaManagerMapper;
import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspProductsregionsbrandMapper;
import com.runlion.shop.dao.BspProductsregionsbrandplaceMapper;
import com.runlion.shop.dao.BspProductsregionspriceMapper;
import com.runlion.shop.dao.BspProductsregionssaleaddressMapper;
import com.runlion.shop.dao.BspProregionextpriceMapper;
import com.runlion.shop.dao.BspRegionpriecedefunitsMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.dao.BspWorktimeMapper;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsExample;
import com.runlion.shop.entity.BspProductsregionsprice;
import com.runlion.shop.entity.BspProductsregionspriceExample;
import com.runlion.shop.entity.BspProregionextprice;
import com.runlion.shop.entity.BspProregionextpriceExample;
import com.runlion.shop.entity.BspRegionpriecedefunits;
import com.runlion.shop.entity.BspRegionpriecedefunitsExample;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspSaleaddressExample;
import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.entity.BspWorktimeExample;
import com.runlion.shop.service.region.RegionsBrandService;
import com.runlion.shop.service.region.RegionsPriceService;
import com.runlion.shop.service.region.RegionsProductService;
import com.runlion.shop.service.region.RegionsService;

/**
 * @2015年7月3日 by linyj
 */
@Service
public class BspAreaManagerService {

	@Autowired
	private RegionsProductService regionsProductService;

	@Autowired
	private RegionsPriceService regionsPriceService;

	@Autowired
	private RegionsBrandService regionsBrandService;

	@Autowired
	private BspAreaManagerMapper bspAreaManagerMapper;

	@Autowired
	private BspAttributevaluesMapper bspAttributevaluesMapper;

	@Autowired
	private BspWorktimeMapper bspWorktimeMapper;

	@Autowired
	BspSaleaddressMapper bspSaleaddressMapper;

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
	private BspRegionsMapper bspRegionsMapper;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	BspRegionpriecedefunitsMapper bspRegionpriecedefunitsMapper;

	@Autowired
	BspProregionextpriceMapper bspProregionextpriceMapper;

	public boolean existsProductRegions(int productId, int regionsId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("PRODUCT_ID", productId);
		par.put("REGIONS_ID", regionsId);
		int c = bspAreaManagerMapper.existsProductRegions(par);
		return c > 0;
	}

	public void removeProductRegionsBrandPlace(int id, int productRegionsId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		par.put("PRODUCT_REGIONS_ID", productRegionsId);
		bspAreaManagerMapper.deleteProductRegionsBrandPlace(par);
	}

	public void upProductRegionsBrandPlace(Integer id, Integer brandId,
			Integer productsRegionsId, Integer placeId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("BRAND_ID", brandId);
		par.put("PRODUCT_REGIONS_ID", productsRegionsId);
		par.put("PLACE_ID", placeId);
		par.put("ID", id);
		bspAreaManagerMapper.upProductRegionsBrandPlace(par);
	}

	public List<Map<String, Object>> queryBrandListByRegionsid(int regionsId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("REGIONS_ID", regionsId);
		par.put("ATTR_ID", 52);// 品牌：52
		return bspAreaManagerMapper.selectBrandByRegionsid(par);
	}

	public List<Map<String, Object>> queryPlaceList() {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ATTR_ID", 51);// 产地：51
		return bspAreaManagerMapper.selectAttrListById(par);
	}

	public int countProductRegionsBrandPlace(int areaId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("REGIONS_ID", areaId);
		return bspAreaManagerMapper.countProductRegionsPlace(par);
	}

	public int countProductRegionsPlace() {
		Map<String, Object> par = new HashMap<String, Object>();
		return bspAreaManagerMapper.countProductBrandPlace(par);
	}

	public int countProductBrandPlace(int areaid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("REGIONS_ID", areaid);
		return bspAreaManagerMapper.countProductRegionsBrandPlace(par);
	}

	public List<Map<String, Object>> queryProductRegionsBrandPlace(int areaId,
			int startNum, int limitNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("REGIONS_ID", areaId);
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		return bspAreaManagerMapper.selectProductRegionsBrandPlace(par);
	}

	// 获取品牌产地关系
	public List<Map<String, Object>> getBrandPlace(int startNum, int limitNum,
			Integer brandId, Integer placeId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);

		par.put("BRAND_ID", brandId);
		par.put("PLACE_ID", placeId);
		return bspAreaManagerMapper.getBrandPlace(par);
	}

	public int countBrandPlace(Integer brandId, Integer placeId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("BRAND_ID", brandId);
		par.put("PLACE_ID", placeId);
		return bspAreaManagerMapper.countBrandPlace(par);
	}

	public List<Map<String, Object>> queryProductRegionsPlace(int startNum,
			int limitNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		return bspAreaManagerMapper.selectProductRegionsPlace(par);
	}

	public List<Map<String, Object>> queryProductBrandPlace(int areaId,
			int startNum, int limitNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("REGIONS_ID", areaId);
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		return bspAreaManagerMapper.selectProductBrandPlace(par);
	}

	public boolean hasProductRegionsBrandPlaceWithId(Integer id,
			Integer brandId, Integer regionsId, Integer placeId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("BRAND_ID", brandId);
		par.put("REGIONS_ID", regionsId);
		par.put("PLACE_ID", placeId);
		par.put("ID", id);
		int r = bspAreaManagerMapper.hasProductRegionsBrandPlaceWithId(par);
		return r > 0;
	}

	public List<Map<String, Object>> queryBrandList() {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ATTR_ID", 52);// 品牌：52
		return bspAreaManagerMapper.selectAttrListById(par);
	}

	public int countProductRegionsBrand(int areaid, Integer brandId) {
		Map<String, Object> par = new HashMap<String, Object>();
		if (areaid != -1) {
			par.put("REGIONS_ID", areaid);
		}
		par.put("BRAND_ID", brandId);
		return bspAreaManagerMapper.countProductRegionsBrand(par);
	}

	public List<Map<String, Object>> queryProductRegionsBrand(int startNum,
			int limitNum, int areaid, Integer brandId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);

		par.put("REGIONS_ID", areaid);

		par.put("BRAND_ID", brandId);

		return bspAreaManagerMapper.selectProductRegionsBrand(par);
	}

	public Map<String, Object> queryProductRegionsByid(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		return bspAreaManagerMapper.selectProductRegionsByid(par);
	}

	public Map<String, Object> querySaleaddressByid(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		return bspAreaManagerMapper.selectSaleaddressByid(par);
	}

	public void removeSaleaddress(int saleid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("SALE_ADDRESS_ID", saleid);
		bspAreaManagerMapper.deleteProductsRegionsSaleAddress(par);
		// bspAreaManagerMapper.deleteProductRegions(par);
		bspAreaManagerMapper.deleteSaleaddress(par);
		BspWorktimeExample bwe = new BspWorktimeExample();
		bwe.createCriteria().andWtpickpointidEqualTo(saleid);
		bspWorktimeMapper.deleteByExample(bwe);
	}

	public int getSaleaddressId() {
		Integer i = bspAreaManagerMapper.getSaleaddressId();
		if (i == null)
			i = 0;
		return i + 1;
	}

	public int countSaleaddress(Integer type, String keyWord,
			String companyName, String companyAddr, String companyCont,
			String ncNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("TYPE", type);
		par.put("KEYWORD", keyWord);

		par.put("COMPANY_NAME", companyName);
		par.put("COMPANY_ADDR", companyAddr);
		par.put("COMPANY_CONT", companyCont);
		par.put("ncNum", ncNum);

		return bspAreaManagerMapper.countSaleaddress(par);
	}

	public List<Map<String, Object>> querySaleaddressLimit(int startNum,
			int limitNum, Integer type, String keyWord, String companyName,
			String companyAddr, String companyCont, String ncNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		par.put("TYPE", type);
		par.put("KEYWORD", keyWord);

		par.put("COMPANY_NAME", companyName);
		par.put("COMPANY_ADDR", companyAddr);
		par.put("COMPANY_CONT", companyCont);
		par.put("ncNum", ncNum);
		return bspAreaManagerMapper.selectSaleaddressLimit(par);
	}

	public Map<String, Object> queryUnitByid(int unitid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("UNITID", unitid);
		return bspAreaManagerMapper.selectUnitByid(par);
	}

	public void removeProductRegionsPrice(int productRegionsId, String priceType) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("PRODUCT_REGIONS_ID", productRegionsId);
		par.put("PRICE_TYPE", priceType);
		bspAreaManagerMapper.deleteProductRegionsPrice(par);
	}

	public void modifyProductsRegionsSaleaddress(int saleAddressId,
			int productsRegionsId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("SALE_ADDRESS_ID", saleAddressId);
		par.put("PRODUCT_REGIONS_ID", productsRegionsId);
		bspAreaManagerMapper.updateProductsRegionsSaleaddress(par);
	}

	public List<Map<String, Object>> queryProductRegionsInfo(
			int productRegionsId, String priceType) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("PRODUCT_REGIONS_ID", productRegionsId);
		par.put("PRICE_TYPE", priceType);
		return bspAreaManagerMapper.selectProductRegionsInfo(par);
	}

	public void addProductsRegionsSaleaddress(int saleAddressId,
			int productRegionsId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("SALE_ADDRESS_ID", saleAddressId);
		par.put("PRODUCT_REGIONS_ID", productRegionsId);
		bspAreaManagerMapper.insertProductsRegionsSaleAddress(par);
	}

	public List<Map<String, Object>> querySaleaddress() {
		Map<String, Object> par = new HashMap<String, Object>();
		List<Map<String, Object>> list = bspAreaManagerMapper
				.selectSaleaddress(par);
		return list;
	}

	public List<BspProductsregionsprice> proRegionPriceList(
			Integer proRegionId, String priceType) {
		BspProductsregionspriceExample bppre = new BspProductsregionspriceExample();
		bppre.createCriteria().andProductregionsidEqualTo(proRegionId)
				.andPricetypeEqualTo(priceType);
		return bspProductsregionspriceMapper.selectByExample(bppre);
	}

	/**
	 * 通过产品区域的主键和价格类型选择默认的区域单位列表
	 * 
	 * @param productsRegionsId
	 * @param priceType
	 * @return
	 */
	public List<BspRegionpriecedefunits> getPRDefUnits(
			Integer productsRegionsId, String priceType) {
		BspRegionpriecedefunitsExample brfe = new BspRegionpriecedefunitsExample();
		brfe.createCriteria().andRegionpriceidEqualTo(productsRegionsId)
				.andPricetypeEqualTo(priceType);
		return bspRegionpriecedefunitsMapper.selectByExample(brfe);

	}

	public boolean hasRegionsPlaceOfZT(Integer placeid, Integer regionsid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("PLACE_ID", placeid);
		par.put("REGIONS_ID", regionsid);
		int c = bspAreaManagerMapper.hasRegionsPlaceOfZT(par);
		return c > 0;
	}

	public boolean hasIdRegionsPlaceOfZT(Integer id, Integer placeid,
			Integer regionsid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		par.put("PLACE_ID", placeid);
		par.put("REGIONS_ID", regionsid);
		int c = bspAreaManagerMapper.hasIdRegionsPlaceOfZT(par);
		return c > 0;
	}

	public boolean addNewSealAdress(BspSaleaddress u,
			List<BspWorktime> worktimeList) {

		int productsRegionsId = regionsProductService
				.generateNewProductRegionId();
		regionsProductService.addProductsRegions(productsRegionsId, null,
				u.getRegionid(), u.getRegionname(), null, null);

		int saleaddressId = this.getSaleaddressId();
		bspSaleaddressMapper.insertSelective(u);

		this.addProductsRegionsSaleaddress(saleaddressId, productsRegionsId);
		for (BspWorktime wt : worktimeList) {
			wt.setWtpickpointid(saleaddressId);
			bspWorktimeMapper.insertSelective(wt);
		}

		return true;

	}

	public boolean addSealAdressOnly(BspSaleaddress u,
			List<BspWorktime> worktimeList) {
		int saleaddressId = this.getSaleaddressId();

		bspSaleaddressMapper.insertSelective(u);

		for (BspWorktime wt : worktimeList) {
			wt.setWtpickpointid(saleaddressId);
			bspWorktimeMapper.insertSelective(wt);
		}

		return true;

	}

	public boolean updateSealAdress(Integer prid, BspSaleaddress u,
			List<BspWorktime> worktimeList) {
		regionsProductService.updateProductsRegions(prid, null,
				u.getRegionid(), u.getRegionname(), null, null);

		BspSaleaddressExample e = new BspSaleaddressExample();
		e.createCriteria().andIdEqualTo(u.getId());

		int result = bspSaleaddressMapper.updateByExampleSelective(u, e);
		if (result <= 0) {
			return false;
		}

		BspWorktimeExample bwe = new BspWorktimeExample();
		bwe.createCriteria().andWtpickpointidEqualTo(u.getId());
		bspWorktimeMapper.deleteByExample(bwe);

		for (BspWorktime wt : worktimeList) {
			wt.setWtpickpointid(u.getId());
			bspWorktimeMapper.insertSelective(wt);
		}

		return true;

	}

	public boolean updateSealAdressOnly(BspSaleaddress u,
			List<BspWorktime> worktimeList) {
		BspSaleaddressExample e = new BspSaleaddressExample();
		e.createCriteria().andIdEqualTo(u.getId());

		int result = bspSaleaddressMapper.updateByExampleSelective(u, e);
		if (result <= 0) {
			return false;
		}

		BspWorktimeExample bwe = new BspWorktimeExample();
		bwe.createCriteria().andWtpickpointidEqualTo(u.getId());
		bspWorktimeMapper.deleteByExample(bwe);

		for (BspWorktime wt : worktimeList) {
			wt.setWtpickpointid(u.getId());
			bspWorktimeMapper.insertSelective(wt);
		}

		return true;

	}

	public boolean updateSalerefregions(Integer id, String[] regions,
			String[] regionNames) {

		if (regionNames != null && regions != null) {
			if (regionNames.length != regions.length) {
				return false;
			}
		}

		// this.cleanSealaddProregRef(id);

		if (regionNames != null && regions != null) {
			for (int i = 0; i < regions.length; i++) {
				String region = regions[i];
				String regionName = regionNames[i];
				this.updateSalerefregion(id, region, regionName);

				// lipeng 同时插入区域的下级
				int regionsId = Integer.valueOf(region);
				List<BspRegions> list = regionsService
						.getResionsChildrenById(regionsId);
				for (BspRegions b : list) {
					// 判断是否已存在
					int prid = regionsProductService.getProductsRegionsId(-1,
							b.getRegionid());
					regionName = regionsService.getResionsFullNameById(b
							.getRegionid());
					if (!regionsProductService
							.isProductsRegionsSaleaddressExists(id, prid)) {
						this.updateSalerefregion(id,
								b.getRegionid().toString(), regionName);
					}

				}
			}
		}
		return true;
	}

	// 更新工厂门店的覆盖区域， lipeng
	public boolean updateSalerefregion(Integer id, String regions,
			String regionName) {

		int iregion = Integer.valueOf(regions);
		int proRegionid = regionsProductService.getProductsRegionsId(-1,
				iregion);
		if (proRegionid <= 0) {

			proRegionid = regionsProductService.generateNewProductRegionId();
			regionsProductService.addProductsRegions(proRegionid, -1, iregion,
					regionName, null, null);
		}

		if (this.hasSealaddProRegionRef(id, proRegionid)) {
			return false;
		}

		this.addProductsRegionsSaleaddress(id, proRegionid);

		return true;
	}

	public void cleanSealaddProregRef(Integer sealid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("SALE_ADD_ID", sealid);
		bspAreaManagerMapper.cleanSealaddProregRef(par);
	}

	// <!-- 获取工厂、门店的覆盖区域 -->
	public List<BspRegions> getSaleAddressRegions(Integer saleaddressId,
			int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<BspRegions> list = bspRegionsMapper
				.getSaleAddressRegions(saleaddressId);
		return list;
	}

	public List<BspProductsregions> getProRegions(List<Integer> idList) {
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andIdIn(idList);
		return bspProductsregionsMapper.selectByExample(bpre);
	}

	public boolean hasSealaddProRegionRef(Integer sealid, Integer proRegid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("SALE_ADD_ID", sealid);
		par.put("PRO_REGION_ID", proRegid);
		int count = bspAreaManagerMapper.countSealaddProRegionRef(par);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<BspWorktime> selWtListByPickpointId(Integer pointid) {
		BspWorktimeExample bwe = new BspWorktimeExample();
		bwe.createCriteria().andWtpickpointidEqualTo(pointid);
		return bspWorktimeMapper.selectByExample(bwe);
	}

	// 批量更新区域产品价格
	public int priceBatchEdit(int batchType, double price, int layer,
			int REGIONS_ID, int PRODUCT_ID) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("batchType", batchType);
		par.put("price", price);
		par.put("layer", layer);
		par.put("REGIONS_ID", REGIONS_ID);
		par.put("PRODUCT_ID", PRODUCT_ID);
		return bspAreaManagerMapper.priceBatchEdit(par);

	}

	public BspProductsregions getBspProductsregions(int id) {
		return bspProductsregionsMapper.selectByPrimaryKey(id);
	}

	public List<BspProductsregions> getBspProductsregionsByProduct(int pid) {
		BspProductsregionsExample e = new BspProductsregionsExample();
		e.or().andProductidEqualTo(pid);
		return bspProductsregionsMapper.selectByExample(e);
	}

	public List<BspSaleaddress> selSaleaddressByAreaid(int areaid) {
		return bspAreaManagerMapper.selSaleaddressByAreaid(areaid);

	}

	public BspProductsregions selBspProductsregions(Integer proRegionId) {
		return bspProductsregionsMapper.selectByPrimaryKey(proRegionId);
	}

	public Map<String, Object> selProRegionsBrand(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("PROD_REGIONS_BRABDID", id);
		return bspAreaManagerMapper.selProRegionsBrand(par);
	}

	public void upProductRegionsBrandBy(int id, int brandid, int proregionid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("BRAND_ID", brandid);
		par.put("REGIONS_ID", proregionid);
		par.put("ID", id);
		bspAreaManagerMapper.upProductRegionsBrandById(par);
	}

	public boolean hasProductRegionsBrandWithId(int id, int brandid,
			int proregionid) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("BRAND_ID", brandid);
		par.put("REGIONS_ID", proregionid);
		par.put("ID", id);
		int ri = bspAreaManagerMapper.hasProductRegionsBrandWithId(par);
		if (ri > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Map<String, Object> selBrandPlaceById(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		return bspAreaManagerMapper.selBrandPlaceById(par);
	}

	public boolean delSaleAddrProRegionsRef(Integer saleAddrId,
			Integer proRegionId) {
		Map<String, Object> par = new HashMap();
		par.put("SALE_ADDRESS_ID", saleAddrId);
		par.put("SALE_REGION_ID", proRegionId);
		bspAreaManagerMapper.delSaleAddrProRegionsRef(par);
		return true;
	}

	/**
	 * 获取产品id小于等于0的产品区域
	 * 
	 * @param regionid
	 * @return
	 */
	public List<BspProductsregions> getNoProidProregions(Integer regionid) {
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andRegionsidEqualTo(regionid)
				.andProductidLessThanOrEqualTo(0);
		return bspProductsregionsMapper.selectByExample(bpre);
	}

	/**
	 * 根据产品id和区域
	 * 
	 * @param regionid
	 * @return
	 */
	public BspProductsregions getYesProidProregions(Integer regionid,
			Integer pid) {
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andRegionsidEqualTo(regionid)
				.andProductidEqualTo(pid);
		return bspProductsregionsMapper.selectByExampleEntity(bpre);
	}

	// 区域装卸费
	public List<BspProductsregionsprice> getRegionsLoadPrice(int regionsId) {
		List<BspProductsregionsprice> list = bspProductsregionspriceMapper
				.getRegionsLoadPrice(regionsId);
		if (list == null || list.size() == 0) {
			for (Integer regId : regionsService.getParentRegionList(regionsId)) {
				list = bspProductsregionspriceMapper.getRegionsLoadPrice(regId);
				if (list == null || list.size() == 0)
					continue;
				else
					return list;
			}

		}
		return list;
	}

	/**
	 * 获取产品id小于等于0的产品区域
	 * 
	 * @param regionid
	 * @return
	 */
	public List<BspProductsregions> getNoProidProregionsWithUp(Integer regionid) {
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andRegionsidEqualTo(regionid)
				.andProductidLessThanOrEqualTo(0);
		List<BspProductsregions> regionList = bspProductsregionsMapper
				.selectByExample(bpre);
		if (regionList == null || regionList.size() == 0) {
			for (Integer regId : regionsService.getParentRegionList(regionid)) {
				bpre.createCriteria().andRegionsidEqualTo(regId)
						.andProductidLessThanOrEqualTo(0);
				regionList = bspProductsregionsMapper.selectByExample(bpre);
				if (regionList == null || regionList.size() == 0)
					continue;
				else
					return regionList;
			}
		}
		return regionList;
	}

	/**
	 * 根据产品id和区域，如果不存在则将区域向上追溯
	 * 
	 * @param regionid
	 * @return
	 */
	public BspProductsregions getYesProidProregionsWithUp(Integer regionid,
			Integer pid) {
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andRegionsidEqualTo(regionid)
				.andProductidEqualTo(pid);
		BspProductsregions region = bspProductsregionsMapper
				.selectByExampleEntity(bpre);
		if (region == null) {
			for (Integer regId : regionsService.getParentRegionList(regionid)) {
				bpre.createCriteria().andRegionsidEqualTo(regId)
						.andProductidLessThanOrEqualTo(pid);
				region = bspProductsregionsMapper.selectByExampleEntity(bpre);
				if (region == null)
					continue;
				else
					return region;
			}
		}
		return region;
	}

	/**
	 * 根据产品id和区域，如果不存在则不向上追溯
	 * 
	 * @param regionid
	 * @return
	 */
	public BspProductsregions getYesProidProregionsWithNotUp(Integer regionid,
			Integer pid) {
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andRegionsidEqualTo(regionid)
				.andProductidEqualTo(pid);
		BspProductsregions region = bspProductsregionsMapper
				.selectByExampleEntity(bpre);

		return region;
	}

	/**
	 * 获取某个产品区域id下的某个类型的额外费用
	 * 
	 * @param proregid
	 * @param type
	 * @return
	 */
	public BspProregionextprice getBspProregionextprice(Integer proregid,
			Integer type) {
		BspProregionextprice extprice = null;
		// ///
		BspProregionextpriceExample example = new BspProregionextpriceExample();
		example.createCriteria().andTypeEqualTo(type + "")
				.andProregionidEqualTo(proregid);
		// ///
		List<BspProregionextprice> extList = bspProregionextpriceMapper
				.selectByExample(example);
		if (extList != null && !extList.isEmpty()) {
			extprice = extList.get(0);
		}
		return extprice;
	}

	/**
	 * 获取某个产品区域id下的所有额外费用
	 * 
	 * @param proregid
	 * @return
	 */
	public List<BspProregionextprice> getBspProregionextpriceList(
			Integer proregid) {
		// ///
		BspProregionextpriceExample example = new BspProregionextpriceExample();
		example.createCriteria().andProregionidEqualTo(proregid);
		// ///
		List<BspProregionextprice> extList = bspProregionextpriceMapper
				.selectByExample(example);

		return extList;
	}

	/**
	 * 测试给定的pkCorp是否在门店/工厂表中已经存在
	 * 
	 * @param pkcorp
	 * @return 已经存在返回true 否则为false
	 */
	public boolean hasNcPkCorp(String pkcorp, Integer sealId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("pkcorp", pkcorp);
		par.put("sealId", sealId);
		int count = bspAreaManagerMapper.getNcPkcorpCount(par);
		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}
}
