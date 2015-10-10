package com.runlion.shop.dao;

import org.apache.ibatis.annotations.Param;

import com.runlion.shop.entity.BsporderVerification;

public interface BsporderVerificationMapper {
	/**
	 * 保存
	 * 
	 * @param cart
	 * @return
	 */
	public int insertOrderVerification(BsporderVerification bsporderVerification);

	/**
	 * 查询订单验证信息
	 *
	 * @return
	 */
	public BsporderVerification getOrderVerification(
			@Param(value = "orderNumber") String orderNumber,
			@Param(value = "VerificationCode") String VerificationCode);

	int updateOrderVerification(
			@Param("VerificationCode") String VerificationCode);

	int updateOrderVerificationByOrder(
			@Param(value = "orderNumber") String orderNumber,
			@Param(value = "VerificationCode") String VerificationCode);
}
