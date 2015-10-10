package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.runlion.shop.entity.BspHelps;
import com.runlion.shop.entity.BspHelpsExample;

public interface BspHelpsMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int countByExample(BspHelpsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int deleteByExample(BspHelpsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int insert(BspHelps record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int insertSelective(BspHelps record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	List<BspHelps> selectByExampleWithBLOBs(BspHelpsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	List<BspHelps> selectByExample(BspHelpsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByExampleSelective(@Param("record") BspHelps record,
			@Param("example") BspHelpsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByExampleWithBLOBs(@Param("record") BspHelps record,
			@Param("example") BspHelpsExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_helps
	 *
	 * @mbggenerated Fri Jun 26 14:56:27 CST 2015
	 */
	int updateByExample(@Param("record") BspHelps record,
			@Param("example") BspHelpsExample example);

	public List<Map<String, Object>> selectHelpsLimit(Map<String, Object> par);

	public void deleteHelpsById(Map<String, Object> par);

	public int countHelps(Map<String, Object> par);

	public Map<String, Object> selectHlepsById(Map<String, Object> par);

	public void updateHelpsById(Map<String, Object> par);

	public void insertHelps(Map<String, Object> par);
}