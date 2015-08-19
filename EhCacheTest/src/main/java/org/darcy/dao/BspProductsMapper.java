package org.darcy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.darcy.entity.BspProducts;
import org.darcy.entity.BspProductsExample;
import org.springframework.cache.annotation.Cacheable;

public interface BspProductsMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int countByExample(BspProductsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int deleteByExample(BspProductsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int deleteByPrimaryKey(Integer pid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int insert(BspProducts record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int insertSelective(BspProducts record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	List<BspProducts> selectByExampleWithBLOBs(BspProductsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	@Cacheable(value = "myCache")
	List<BspProducts> selectByExample(BspProductsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	BspProducts selectByPrimaryKey(Integer pid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByExampleSelective(@Param("record") BspProducts record,
			@Param("example") BspProductsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByExampleWithBLOBs(@Param("record") BspProducts record,
			@Param("example") BspProductsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByExample(@Param("record") BspProducts record,
			@Param("example") BspProductsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByPrimaryKeySelective(BspProducts record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByPrimaryKeyWithBLOBs(BspProducts record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_products
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByPrimaryKey(BspProducts record);
}