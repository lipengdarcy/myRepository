package com.runlion.shop.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单生产器
 * 
 * @author
 *
 */
public class OrderNoGenerator {
	private static long orderNum = 0l;
	private static String date;

	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static synchronized String getOrderNo() {
		Random rand = new Random();
		int num = 0;
		String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		num = (int) (rand.nextDouble() * (100000 - 10000) + 10000);
		String orderNo = str + num;
		return orderNo;
	}

	/**
	 * 生成5位随机码
	 * 
	 * @return
	 */
	public static synchronized String getVerificationCode() {
		Random rand = new Random();
		int num = 0;
		num = (int) (rand.nextDouble() * (100000 - 10000) + 10000);
		String orderNo = Integer.toString(num);
		return orderNo;
	}
}
