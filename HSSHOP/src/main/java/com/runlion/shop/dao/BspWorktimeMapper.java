package com.runlion.shop.dao;

import com.runlion.shop.entity.BspWorktime;
import com.runlion.shop.entity.BspWorktimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BspWorktimeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int countByExample(BspWorktimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int deleteByExample(BspWorktimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int deleteByPrimaryKey(Integer wtid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int insert(BspWorktime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int insertSelective(BspWorktime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    List<BspWorktime> selectByExample(BspWorktimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    BspWorktime selectByPrimaryKey(Integer wtid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int updateByExampleSelective(@Param("record") BspWorktime record, @Param("example") BspWorktimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int updateByExample(@Param("record") BspWorktime record, @Param("example") BspWorktimeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int updateByPrimaryKeySelective(BspWorktime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_worktime
     *
     * @mbggenerated Mon Jul 27 16:49:40 CST 2015
     */
    int updateByPrimaryKey(BspWorktime record);
    
    List<BspWorktime> selectByWorkTime(@Param("wtpickpointid") int wtpickpointid,@Param("wttype") int wttype);

}