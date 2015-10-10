package com.runlion.shop.dao;

import com.runlion.shop.entity.BspAttributes;
import com.runlion.shop.entity.BspAttributesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BspAttributesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int countByExample(BspAttributesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int deleteByExample(BspAttributesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int deleteByPrimaryKey(Short attrid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int insert(BspAttributes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int insertSelective(BspAttributes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    List<BspAttributes> selectByExample(BspAttributesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    BspAttributes selectByPrimaryKey(Short attrid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByExampleSelective(@Param("record") BspAttributes record, @Param("example") BspAttributesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByExample(@Param("record") BspAttributes record, @Param("example") BspAttributesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByPrimaryKeySelective(BspAttributes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_attributes
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByPrimaryKey(BspAttributes record);

	List getAttributelist(Short cateid);
}