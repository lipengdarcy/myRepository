package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.runlion.shop.entity.BspComment;
import com.runlion.shop.entity.BspCommentExample;
import com.runlion.shop.entity.CommentDTO;
import com.runlion.shop.vo.CommentVO;

public interface BspCommentMapper {

	List<CommentDTO> getList(Map<String, Object> map);

	List<CommentDTO> getListByMiddle(Map<String, Object> map);

	List<CommentDTO> getListByGood(Map<String, Object> map);

	List<CommentDTO> getListByBad(Map<String, Object> map);

	List<Map<String, Object>> selectByExamplePrice(Map<String, Object> map);

	int getCommentCount(Map<String, Object> map);

	BspComment selectByPrimaryKey(int cid);

	int countBybad(Integer oid);

	int countBygood(Integer oid);

	int countBymiddle(Integer oid);

	/**
	 * 获取评论列表
	 * 
	 * @param map
	 * @return
	 */
	List<CommentVO> getVOList(Map<String, Object> map);

	/**
	 * 获取评论列表的数量
	 * 
	 * @param map
	 * @return
	 */
	int countVOList(Map<String, Object> map);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int countByExample(BspCommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int deleteByExample(BspCommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int deleteByPrimaryKey(Integer cid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int insert(BspComment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int insertSelective(BspComment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	List<BspComment> selectByExample(BspCommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	BspComment selectByPrimaryKey(Integer cid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int updateByExampleSelective(@Param("record") BspComment record,
			@Param("example") BspCommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int updateByExample(@Param("record") BspComment record,
			@Param("example") BspCommentExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int updateByPrimaryKeySelective(BspComment record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table bsp_comment
	 *
	 * @mbggenerated Thu Sep 17 13:50:25 CST 2015
	 */
	int updateByPrimaryKey(BspComment record);
}