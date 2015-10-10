package com.runlion.shop.common.util;

import org.apache.log4j.Logger;

/**
 * @2015年7月7日 by linyj
 */
public class JstlFunction {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	/**
	 * 格式化类型显示 0:否，1:是
	 * 
	 * @param obj
	 * @return
	 */
	public static String getYesOrNoDesc(Object obj) {
		// loggerinfo.info("getAreaPriceType(" + obj + ")");
		if (obj == null)
			return "";
		String val = obj.toString();
		String s = val;
		if ("1".equals(val)) {
			s = "是";
		} else if ("0".equals(val)) {
			s = "否";
		} else {
			s = val;
		}
		return s;
	}

	/**
	 * 格式化类型显示 获取区域价格类型描述
	 * 
	 * @param obj
	 * @return
	 */
	public static String getAreaPriceType(Object obj) {
		// loggerinfo.info("getAreaPriceType(" + obj + ")");
		if (obj == null)
			return "";
		String val = obj.toString();
		String s = val;
		if ("1".equals(val)) {
			s = "大于1吨小于12吨";
		} else if ("2".equals(val)) {
			s = "大于12吨小于20吨";
		} else if ("3".equals(val)) {
			s = "大于20吨";
		}

		return s;
	}

	/**
	 * 格式化类型显示 获取工厂/门店类型描述
	 * 
	 * @param obj
	 * @return
	 */
	public static String getSaleaddressType(Object obj) {
		// loggerinfo.info("getSaleaddressType(" + obj + ")");
		if (obj == null)
			return "";
		String val = obj.toString();
		String s = val;
		if ("1".equals(val)) {
			s = "门店";
		} else if ("2".equals(val)) {
			s = "工厂";
		}

		return s;
	}

}
