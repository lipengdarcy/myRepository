package com.runlion.shop.common.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.runlion.shop.entity.enums.OrderStateEnum;

/**
 * Filename: EnumUtitlTest.java <br>
 * Title: 枚举测试工具类<br>
 * De.ion: TODO(这里用一句话描述这个类的作用)<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月7日 <br>
 * Time: 上午10:38:57 <br>
 * Version: 1.0.0 <br>
 */
public class EnumUtitlTest {
	@Test
	public void test_valueOf() {

		OrderStateEnum orderState = EnumsUtil.valueOf(OrderStateEnum.class, (byte)2);

		Assert.assertTrue(orderState == OrderStateEnum.PreVerify);

	}

	@Test
	public void test_valueOfNull() {

		OrderStateEnum orderState = EnumsUtil.valueOf(OrderStateEnum.class, (byte)0);

		Assert.assertTrue(orderState == null);

	}

	@Test
	public void test_valueoflist() {
		OrderStateEnum orderState = EnumsUtil.valueOf(OrderStateEnum.class, (byte)0);
		System.out.println(orderState.getDesc());

		List<OrderStateEnum> u = EnumsUtil.valueOfList(OrderStateEnum.class);
		for (OrderStateEnum o : u) {
			System.out.println(o.getCode() + "-" + o.getDesc());
		}
	}
}
