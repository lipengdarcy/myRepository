package com.runlion.shop.dao;

import com.runlion.shop.entity.BspMailverification;
import com.runlion.shop.entity.BspMailverificationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BspMailverificationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int countByExample(BspMailverificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int deleteByExample(BspMailverificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int deleteByPrimaryKey(Integer mid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int insert(BspMailverification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int insertSelective(BspMailverification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    List<BspMailverification> selectByExample(BspMailverificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    BspMailverification selectByPrimaryKey(Integer mid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int updateByExampleSelective(@Param("record") BspMailverification record, @Param("example") BspMailverificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int updateByExample(@Param("record") BspMailverification record, @Param("example") BspMailverificationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int updateByPrimaryKeySelective(BspMailverification record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_mailverification
     *
     * @mbggenerated Wed Jul 15 09:07:00 CST 2015
     */
    int updateByPrimaryKey(BspMailverification record);
}