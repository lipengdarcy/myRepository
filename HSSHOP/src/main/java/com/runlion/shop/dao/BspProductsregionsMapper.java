package com.runlion.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.runlion.shop.entity.BspProductsregions;
import com.runlion.shop.entity.BspProductsregionsExample;

public interface BspProductsregionsMapper {

	int getProductRegionId();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int countByExample(BspProductsregionsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int deleteByExample(BspProductsregionsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int insert(BspProductsregions record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int insertSelective(BspProductsregions record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	List<BspProductsregions> selectByExample(BspProductsregionsExample example);

	/**
	 * 选择对应的条件数据
	 * 
	 * @param example
	 * @return BspProductsregions 返回类型
	 */
	BspProductsregions selectByExampleEntity(BspProductsregionsExample example);

	/**
	 * 选取工厂/门店的产品区域信息
	 * 
	 * @param saleaddressId
	 *            工厂或者门店的id
	 * @return
	 */
	List<BspProductsregions> getSaleAddressProRegions(Integer saleaddressId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	BspProductsregions selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int updateByExampleSelective(@Param("record") BspProductsregions record,
			@Param("example") BspProductsregionsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int updateByExample(@Param("record") BspProductsregions record,
			@Param("example") BspProductsregionsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int updateByPrimaryKeySelective(BspProductsregions record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_productsregions
	 *
	 * @mbggenerated Thu Jul 02 13:19:05 CST 2015
	 */
	int updateByPrimaryKey(BspProductsregions record);
}