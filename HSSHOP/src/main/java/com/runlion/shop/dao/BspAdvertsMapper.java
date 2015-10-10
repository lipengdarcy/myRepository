package com.runlion.shop.dao;

import com.runlion.shop.entity.BspAdverts;
import com.runlion.shop.entity.BspAdvertsExample;
import com.runlion.shop.entity.BspAdvertsWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BspAdvertsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int countByExample(BspAdvertsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int deleteByExample(BspAdvertsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int deleteByPrimaryKey(Integer adid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int insert(BspAdvertsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int insertSelective(BspAdvertsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    List<BspAdvertsWithBLOBs> selectByExampleWithBLOBs(BspAdvertsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    List<BspAdverts> selectByExample(BspAdvertsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    BspAdvertsWithBLOBs selectByPrimaryKey(Integer adid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByExampleSelective(@Param("record") BspAdvertsWithBLOBs record, @Param("example") BspAdvertsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByExampleWithBLOBs(@Param("record") BspAdvertsWithBLOBs record, @Param("example") BspAdvertsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByExample(@Param("record") BspAdverts record, @Param("example") BspAdvertsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByPrimaryKeySelective(BspAdvertsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(BspAdvertsWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_adverts
     *
     * @mbggenerated Fri Jun 26 14:56:27 CST 2015
     */
    int updateByPrimaryKey(BspAdverts record);
}