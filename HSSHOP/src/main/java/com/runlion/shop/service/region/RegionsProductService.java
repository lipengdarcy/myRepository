package com.runlion.shop.service.region;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspProductsregionsMapper;
import com.runlion.shop.dao.BspProductsregionssaleaddressMapper;
import com.runlion.shop.dao.BspRegionsMapper;
import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsExample;
import com.runlion.shop.entity.BspProductsregionssaleaddress;
import com.runlion.shop.entity.BspProductsregionssaleaddressExample;

@Service
public class RegionsProductService {
	@Autowired
	private BspRegionsMapper regionsMapper;

	@Autowired
	private BspProductsregionsMapper bspProductsregionsMapper;

	@Autowired
	private RegionsService regionsService;

	@Autowired
	private BspProductsregionssaleaddressMapper bspProductsregionssaleaddressMapper;

	// 产品区域id（最大值加1）
	public int generateNewProductRegionId() {
		Integer i = bspProductsregionsMapper.getProductRegionId();
		if (i == null)
			return 1;
		return i + 1;
	}

	// 获取区域产品
	public BspProductsregions getProductsRegionsById(Integer id) {
		return bspProductsregionsMapper.selectByPrimaryKey(id);
	}

	// 获取区域产品
	public BspProductsregions getProductsRegions(Integer productId,
			Integer regionsId) {
		BspProductsregionsExample bpre = new BspProductsregionsExample();
		bpre.createCriteria().andRegionsidEqualTo(regionsId)
				.andProductidEqualTo(productId);
		BspProductsregions p = bspProductsregionsMapper
				.selectByExampleEntity(bpre);
		if (p != null)
			return p;

		bpre = new BspProductsregionsExample();
		bpre.createCriteria().andRegionsidEqualTo(regionsId)
				.andProductidEqualTo(-1);
		p = bspProductsregionsMapper.selectByExampleEntity(bpre);

		if (p == null) {
			for (Integer regId : regionsService.getParentRegionList(regionsId)) {
				bpre = new BspProductsregionsExample();
				bpre.createCriteria().andRegionsidEqualTo(regId)
						.andProductidEqualTo(productId);
				p = bspProductsregionsMapper.selectByExampleEntity(bpre);
				if (p != null)
					return p;
			}
		}
		if (p == null) {
			for (Integer regId : regionsService.getParentRegionList(regionsId)) {
				bpre = new BspProductsregionsExample();
				bpre.createCriteria().andRegionsidEqualTo(regId)
						.andProductidEqualTo(-1);
				p = bspProductsregionsMapper.selectByExampleEntity(bpre);
				if (p != null)
					return p;
			}
		}

		return p;
	}

	// 增加区域产品(单个)
	public void addProductsRegions(Integer id, Integer productId,
			Integer regionsId, String regionsName) {
		BspProductsregions record = new BspProductsregions(id, productId,
				regionsId, regionsName);
		bspProductsregionsMapper.insertSelective(record);
	}

	// 增加区域产品(单个)
	public void addProductsRegions(Integer id, Integer productId,
			Integer regionsId, String regionsName, BigDecimal starthand,
			BigDecimal startship) {
		BspProductsregions record = new BspProductsregions(id, productId,
				regionsId, regionsName, starthand, startship);
		bspProductsregionsMapper.insertSelective(record);
	}

	// 修改区域产品(单个)
	public int updateProductsRegions(int productsRegionsId, Integer productId,
			int regionsId, String regionsName, BigDecimal starthand,
			BigDecimal startship) {
		BspProductsregions record = new BspProductsregions(productsRegionsId,
				productId, regionsId, regionsName, starthand, startship);
		return bspProductsregionsMapper.updateByPrimaryKeySelective(record);
	}

	// 判断ProductsRegions是否已存在
	public int getProductsRegionsId(int pid, int rid) {
		BspProductsregionsExample e = new BspProductsregionsExample();
		e.createCriteria().andProductidEqualTo(pid).andRegionsidEqualTo(rid);
		List<BspProductsregions> list = bspProductsregionsMapper
				.selectByExample(e);
		if (list == null || list.size() == 0)
			return -1;
		else
			return list.get(0).getId();
	}

	// 判断bsp_productsregionssaleaddress是否已存在
	public boolean isProductsRegionsSaleaddressExists(int saleId, int prid) {
		BspProductsregionssaleaddressExample e = new BspProductsregionssaleaddressExample();
		e.createCriteria().andProductregionsidEqualTo(prid)
				.andSaleaddressidEqualTo(saleId);

		List<BspProductsregionssaleaddress> list = bspProductsregionssaleaddressMapper
				.selectByExample(e);
		if (list == null || list.size() == 0)
			return false;
		else
			return true;
	}

}
