package com.runlion.shop.dao;

import com.runlion.shop.entity.BspProductsregionsbrand;
import com.runlion.shop.entity.BspProductsregionsbrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BspProductsregionsbrandMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int countByExample(BspProductsregionsbrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int deleteByExample(BspProductsregionsbrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int insert(BspProductsregionsbrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int insertSelective(BspProductsregionsbrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    List<BspProductsregionsbrand> selectByExample(BspProductsregionsbrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    BspProductsregionsbrand selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int updateByExampleSelective(@Param("record") BspProductsregionsbrand record, @Param("example") BspProductsregionsbrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int updateByExample(@Param("record") BspProductsregionsbrand record, @Param("example") BspProductsregionsbrandExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int updateByPrimaryKeySelective(BspProductsregionsbrand record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_productsregionsbrand
     *
     * @mbggenerated Fri Aug 07 17:10:39 CST 2015
     */
    int updateByPrimaryKey(BspProductsregionsbrand record);
}