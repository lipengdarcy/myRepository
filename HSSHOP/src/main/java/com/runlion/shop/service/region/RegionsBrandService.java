package com.runlion.shop.service.region;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspProductsregionsbrandMapper;
import com.runlion.shop.dao.BspProductsregionsbrandplaceMapper;
import com.runlion.shop.dao.BspProductsregionspriceMapper;
import com.runlion.shop.dao.BspProductsregionssaleaddressMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsbrand;
import com.runlion.shop.entity.BspProductsregionsbrandExample;
import com.runlion.shop.entity.BspProductsregionsbrandplace;
import com.runlion.shop.entity.BspProductsregionsbrandplaceExample;
import com.runlion.shop.entity.BspRegions;

@Service
public class RegionsBrandService {
	@Autowired
	private BspRegionsMapper regionsMapper;

	@Autowired
	private RegionsService regionsService;

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

	public int deleteProductRegionsBrand(int id) {
		return bspProductsregionsbrandMapper.deleteByPrimaryKey(id);
	}

	// 1.增加区域品牌(多个)
	public void addProductRegionsBrand(int brandId, int productRegionsId) {
		BspProductsregionsbrand record = new BspProductsregionsbrand(brandId,
				productRegionsId);
		bspProductsregionsbrandMapper.insertSelective(record);

		// lipeng 同时插入区域的下级
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
			int prBrandid = getProductsRegionsBrandId(brandId, prid);
			if (prid > 0) {
				if (prBrandid < 0)
					// 插入BspProductsregionsBrand
					bspProductsregionsbrandMapper
							.insertSelective(new BspProductsregionsbrand(
									brandId, prid));

			} else {
				// 插入BspProductsregions
				prid = regionsProductService.generateNewProductRegionId();
				String regionsName = regionsService.getResionsFullNameById(b
						.getRegionid());
				regionsProductService.addProductsRegions(prid, productId,
						b.getRegionid(), regionsName);

				// 插入BspProductsregionsBrand
				prBrandid = getProductsRegionsBrandId(brandId, prid);
				if (prBrandid < 0)
					bspProductsregionsbrandMapper
							.insertSelective(new BspProductsregionsbrand(
									brandId, prid));

			}

		}

	}

	// 2.增加区域产地(多个)
	public void addProductRegionsBrandPlace(Integer brandId,
			Integer productRegionsId, Integer placeId) {
		BspProductsregionsbrandplace record = new BspProductsregionsbrandplace(
				brandId, productRegionsId, placeId);
		bspProductsregionsbrandplaceMapper.insertSelective(record);

		// lipeng 同时插入区域的下级
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
			int prPlaceid = getProductsRegionsBrandPlaceId(brandId, prid,
					placeId);
			if (prid > 0) {
				if (prPlaceid < 0)
					bspProductsregionsbrandplaceMapper
							.insertSelective(new BspProductsregionsbrandplace(
									brandId, prid, placeId));

			} else {
				// 插入BspProductsregions
				prid = regionsProductService.generateNewProductRegionId();
				String regionsName = regionsService.getResionsFullNameById(b
						.getRegionid());
				regionsProductService.addProductsRegions(prid, productId,
						b.getRegionid(), regionsName);

				// 插入BspProductsregionsBrandPlace
				prPlaceid = getProductsRegionsBrandPlaceId(brandId, prid,
						placeId);
				if (prPlaceid < 0)
					bspProductsregionsbrandplaceMapper
							.insertSelective(new BspProductsregionsbrandplace(
									brandId, prid, placeId));

			}

		}

	}

	// 判断ProductsRegionsBrand是否已存在
	public int getProductsRegionsBrandId(int brandId, int prid) {
		BspProductsregionsbrandExample e = new BspProductsregionsbrandExample();
		e.createCriteria().andBrandidEqualTo(brandId)
				.andProductregionsidEqualTo(prid);
		List<BspProductsregionsbrand> list = bspProductsregionsbrandMapper
				.selectByExample(e);
		if (list == null || list.size() == 0)
			return -1;
		else
			return list.get(0).getId();
	}

	// 判断ProductsRegionsBrandPlace是否已存在
	public int getProductsRegionsBrandPlaceId(int brandId, int prid, int placeId) {
		BspProductsregionsbrandplaceExample e = new BspProductsregionsbrandplaceExample();
		e.createCriteria().andBrandidEqualTo(brandId)
				.andProductregionsidEqualTo(prid).andPlaceidEqualTo(placeId);
		List<BspProductsregionsbrandplace> list = bspProductsregionsbrandplaceMapper
				.selectByExample(e);
		if (list == null || list.size() == 0)
			return -1;
		else
			return list.get(0).getId();
	}

	// 根据品牌id获取区域id
	public List<BspProductsregionsbrand> getRegionsIdList(int brandId) {
		BspProductsregionsbrandExample e = new BspProductsregionsbrandExample();
		e.or().andBrandidEqualTo(brandId);
		List<BspProductsregionsbrand> list = bspProductsregionsbrandMapper
				.selectByExample(e);
		return list;
	}

	// 根据品牌id获取产地列表
	public List<BspProductsregionsbrandplace> getRegionsPlaceList(int brandId,
			int prid) {
		BspProductsregionsbrandplaceExample e = new BspProductsregionsbrandplaceExample();
		e.createCriteria().andBrandidEqualTo(brandId)
				.andProductregionsidEqualTo(prid);
		List<BspProductsregionsbrandplace> list = bspProductsregionsbrandplaceMapper
				.selectByExample(e);
		return list;
	}

	// 新增区域产地记录
	public int addRegionPlace(int brandId, int placeId) {
		return bspProductsregionsbrandplaceMapper.insertByBrand(brandId,
				placeId);
	}

	// 删除区域产地记录
	public int deleteRegionPlace(int brandId, int placeId) {
		int count = 0;
		BspProductsregionsbrandplaceExample e = new BspProductsregionsbrandplaceExample();
		e.createCriteria().andBrandidEqualTo(brandId)
				.andPlaceidEqualTo(placeId);
		count = bspProductsregionsbrandplaceMapper.deleteByExample(e);
		return count;
	}

}
