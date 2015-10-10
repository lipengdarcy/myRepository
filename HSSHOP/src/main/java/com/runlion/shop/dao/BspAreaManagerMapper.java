package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.runlion.shop.entity.BspSaleaddress;

/**
 * @2015年7月3日 by linyj
 */
public interface BspAreaManagerMapper {

	// 批量更新区域产品价格
	public int priceBatchEdit(Map<String, Object> par);

	public int existsProductRegions(Map<String, Object> par);

	List<Map<String, Object>> getProductsregionsbrandplace(
			@Param("regionsId") int regionsId, @Param("brandId") int brandId);

	public void deleteProductRegionsBrandPlace(Map<String, Object> par);

	public void insertProductRegionsBrandPlace(Map<String, Object> par);

	public int hasProductRegionsBrandPlace(Map<String, Object> par);

	public List<Map<String, Object>> selectBrandByRegionsid(
			Map<String, Object> par);

	public int countProductRegionsBrandPlace(Map<String, Object> par);

	public int countProductRegionsPlace(Map<String, Object> par);

	public int countProductBrandPlace(Map<String, Object> par);

	public int hasIdRegionsPlaceOfZT(Map<String, Object> par);

	public List<Map<String, Object>> selectProductRegionsBrandPlace(
			Map<String, Object> par);

	public List<Map<String, Object>> getBrandPlace(Map<String, Object> par);

	public int countBrandPlace(Map<String, Object> par);

	public List<Map<String, Object>> selectProductBrandPlace(
			Map<String, Object> par);

	public List<Map<String, Object>> selectProductRegionsPlace(
			Map<String, Object> par);

	public void deleteProductRegionsBrand(Map<String, Object> par);

	// 根据区域id和产品id获取区域产品id
	public Integer getProductRegionsId(Map<String, Object> par);

	public int hasProductRegionsBrand(Map<String, Object> par);

	public int hasRegionsPlaceOfZT(Map<String, Object> par);

	public void insertProductRegionsBrand(Map<String, Object> par);

	public List<Map<String, Object>> selectAttrListById(Map<String, Object> par);

	public int countProductRegionsBrand(Map<String, Object> par);

	public List<Map<String, Object>> selectProductRegionsBrand(
			Map<String, Object> par);

	public Map<String, Object> selectProductRegionsByid(Map<String, Object> par);

	public void updateSaleaddress(Map<String, Object> par);

	public Map<String, Object> selectSaleaddressByid(Map<String, Object> par);

	public void deleteSaleaddress(Map<String, Object> par);

	public int getSaleaddressId();

	public void insertSaleaddress(Map<String, Object> par);

	public int countSaleaddress(Map<String, Object> par);

	public List<Map<String, Object>> selectSaleaddressLimit(
			Map<String, Object> par);

	public Map<String, Object> selectProductByid(Map<String, Object> par);

	public Map<String, Object> selectUnitByid(Map<String, Object> par);

	public void updateProductsRegionsSaleaddress(Map<String, Object> par);

	public void updateProductsRegions(Map<String, Object> par);

	public List<Map<String, Object>> selectProductRegionsInfo(
			Map<String, Object> par);

	public void deleteProductRegions(Map<String, Object> par);

	public void deleteProductRegionsPrice(Map<String, Object> par);

	public void deleteProductRegionsPriceById(int id);

	public List<Map<String, Object>> selectSaleaddress(Map<String, Object> par);

	public void cleanSealaddProregRef(Map<String, Object> par);

	public void deleteProductsRegionsSaleAddress(Map<String, Object> par);

	public int countSealaddProRegionRef(Map<String, Object> par);

	public void insertProductsRegionsSaleAddress(Map<String, Object> par);

	public void insertProductsRegionsPrice(Map<String, Object> par);

	public void insertProductsRegions(Map<String, Object> par);

	public Integer getProductRegionId();

	public Integer getProductRegionPriceId();

	public List<BspSaleaddress> selSaleaddressByAreaid(int areaid);

	public Map<String, Object> selProRegionsBrand(Map<String, Object> par);

	public void upProductRegionsBrandById(Map<String, Object> par);

	public int hasProductRegionsBrandWithId(Map<String, Object> par);

	public Map<String, Object> selBrandPlaceById(Map<String, Object> par);

	public int hasProductRegionsBrandPlaceWithId(Map<String, Object> par);

	public void upProductRegionsBrandPlace(Map<String, Object> par);

	public List<Integer> selSealidByProRegionId(Integer proRegionId);

	public List<Integer> selGCSealidByProRegionId(Map<String, Object> par);

	public List<Integer> selProidProRegionId(Map<String, Object> par);

	public void delSaleAddrProRegionsRef(Map<String, Object> par);

	public int getNcPkcorpCount(Map<String, Object> par);

	public List<BspSaleaddress> selSaleAddressByBrandid(Map<String, Object> par);

}
