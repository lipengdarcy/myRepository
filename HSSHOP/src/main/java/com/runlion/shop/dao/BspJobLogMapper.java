package com.runlion.shop.dao;

import com.runlion.shop.entity.BspJobLog;
import com.runlion.shop.entity.BspJobLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BspJobLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int countByExample(BspJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int deleteByExample(BspJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int insert(BspJobLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int insertSelective(BspJobLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    List<BspJobLog> selectByExample(BspJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    BspJobLog selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int updateByExampleSelective(@Param("record") BspJobLog record, @Param("example") BspJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int updateByExample(@Param("record") BspJobLog record, @Param("example") BspJobLogExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int updateByPrimaryKeySelective(BspJobLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bsp_job_log
     *
     * @mbggenerated Fri Aug 07 10:44:16 CST 2015
     */
    int updateByPrimaryKey(BspJobLog record);
}