package com.runlion.shop.dao;

import com.runlion.shop.entity.SysStaff;
import com.runlion.shop.entity.SysStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysStaffMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int countByExample(SysStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int deleteByExample(SysStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int insert(SysStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int insertSelective(SysStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    List<SysStaff> selectByExample(SysStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    SysStaff selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysStaff record, @Param("example") SysStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByExample(@Param("record") SysStaff record, @Param("example") SysStaffExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByPrimaryKeySelective(SysStaff record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_STAFF
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByPrimaryKey(SysStaff record);
}