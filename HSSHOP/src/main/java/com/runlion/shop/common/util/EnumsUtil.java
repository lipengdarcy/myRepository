package com.runlion.shop.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Filename: EnumsUtil.java <br>
 * Title: 枚举工具类<br>
 * De.ion: TODO(查询枚举信息)<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月7日 <br>
 * Time: 上午10:39:27 <br>
 * Version: 1.0.0 <br>
 */
public class EnumsUtil {

	/**
	 * 获取枚举
	 * 
	 * @param clazz
	 * @param code
	 * @return
	 */
	public static <T extends IEnum> T valueOf(Class<T> clazz, Byte code) {

		// 得到values
		T[] enums = values(clazz);

		if (enums == null || enums.length == 0) {
			return null;
		}

		for (T t : enums) {
			if (t.getCode() == code) {
				return t;
			}
		}
		return null;
	}

	/**
	 * 获取枚举列表
	 * 
	 * @param clazz
	 * @param code
	 * @param ignoreCase
	 * @return
	 */
	public static <T extends IEnum> List<T> valueOfList(Class<T> clazz) {

		// 得到values
		T[] enums = values(clazz);

		if (enums == null || enums.length == 0) {
			return null;
		}
		List<T> sd = new ArrayList<T>();
		for (T t : enums) {
			sd.add(t);

		}
		return sd;
	}

	/**
	 * 获取枚举集合
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T extends IEnum> T[] values(Class<T> clazz) {
		if (!clazz.isEnum()) {
			throw new IllegalArgumentException("Class[" + clazz + "]不是枚举类型");
		}
		// 得到values
		return clazz.getEnumConstants();
	}

}
