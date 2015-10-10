package com.runlion.shop.dao;

import com.runlion.shop.entity.SysOrganization;
import com.runlion.shop.entity.SysOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysOrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int countByExample(SysOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int deleteByExample(SysOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int insert(SysOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int insertSelective(SysOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    List<SysOrganization> selectByExample(SysOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    SysOrganization selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByExampleSelective(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByExample(@Param("record") SysOrganization record, @Param("example") SysOrganizationExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByPrimaryKeySelective(SysOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_ORGANIZATION
     *
     * @mbggenerated Tue Sep 01 13:29:07 CST 2015
     */
    int updateByPrimaryKey(SysOrganization record);
}