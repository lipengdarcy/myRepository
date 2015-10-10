package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.runlion.shop.entity.BspMessage;

public interface MessageMapper {
	/**
	 * 保存 但不保存订单号字段
	 * 
	 * @param cart
	 * @return
	 */
	public int insertMessage(BspMessage bspMessage);

	/**
	 * 保存所有字段
	 * 
	 * @param bspMessage
	 * @return
	 */
	public int insertMessageAll(BspMessage bspMessage);

	/**
	 * 查询所有短信
	 * 
	 * @return
	 */
	public List<BspMessage> getMessageList(Map<String, Object> par);

	/**
	 * 查询所有短信总数
	 * 
	 * @return
	 */
	public int countMessage(Map<String, Object> par);

	/**
	 * 查询所有短信
	 * 
	 * @return
	 */
	public BspMessage getMessageValidity(
			@Param(value = "VerificationCode") String VerificationCode);

	int updateMessageValidity(@Param("VerificationCode") String VerificationCode);

	/**
	 * 获取某个用户的验证码信息
	 * 
	 * @param par
	 * @return
	 */
	public List<BspMessage> getUserMessageValidity(Map<String, Object> par);

	public List<Map<String, Object>> countMessagebyphone(Map<String, Object> par);

	public List<Map<String, Object>> countMessagebyphone1(
			Map<String, Object> par);

	/**
	 * 使用主键更新验证信息的状态
	 * 
	 * @param par
	 */
	public void updateMessageState(Map<String, Object> par);
}
