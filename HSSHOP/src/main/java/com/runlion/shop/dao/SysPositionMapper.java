package com.runlion.shop.dao;

import com.runlion.shop.entity.SysPosition;
import com.runlion.shop.entity.SysPositionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysPositionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int countByExample(SysPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int deleteByExample(SysPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int insert(SysPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int insertSelective(SysPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    List<SysPosition> selectByExample(SysPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    SysPosition selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysPosition record, @Param("example") SysPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByExample(@Param("record") SysPosition record, @Param("example") SysPositionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByPrimaryKeySelective(SysPosition record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_POSITION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByPrimaryKey(SysPosition record);
}