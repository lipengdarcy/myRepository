package com.runlion.shop.entity.unionpay;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 银联支付参数对象
 * */

public class UnionPayParamStatic {

	// 版本号
	public static String version = "5.0.0";

	// 字符集编码 默认"UTF-8"
	public static String encoding = "UTF-8";

	// 签名方法 01 RSA
	public static String signMethod = "01";

	// 交易类型 01-消费
	public static String txnType = "01";

	// 交易子类型 01:自助消费 02:订购 03:分期付款
	public static String txnSubType = "01";

	// 业务类型
	public static String bizType = "000201";

	// 渠道类型，07-PC，08-手机
	public static String channelType = "07";

	// 前台通知地址 ，控件接入方式无作用
	public static String frontUrl = "http://localhost:8080/HSSNSHOPADMIN/testpay/acp_front_url.do";

	// 后台通知地址
	public static String backUrl = "http://localhost:8080/HSSNSHOPADMIN/testpay/acp_back_url.do";

	// 接入类型，商户接入填0 0：商户 ， 1： 收单， 2：平台商户
	public static String accessType = "0";

	// 商户号码，请改成自己的商户号
	public static String merId = "777290058117132";

	// 商户订单号，8-40位数字字母
	public static String orderId = "12345678";

	// 订单发生时间
	public static String txnTime = "201506121440391400";

	// 交易金额
	public static String txnAmt = "1";

	// 交易币种
	public static String currencyCode = "156";

	// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
	public static String reqReserved = "透传信息";

	// 订单描述，可不上送，上送时控件中会显示该信息
	public static String orderDesc = "订单描述";

	public static HashMap<String, String> getValue(Object thisObj) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Class c = Class.forName(thisObj.getClass().getName());
			Method[] m = c.getMethods();
			for (int i = 0; i < m.length; i++) {
				String method = m[i].getName();
				if (method.startsWith("get")) {
					try {
						String value = (String) m[i].invoke(thisObj);
						if (value != null) {
							String key = method.substring(3);
							key = key.substring(0, 1).toUpperCase()
									+ key.substring(1);
							map.put(method, value);
						}
					} catch (Exception e) {
						System.out.println("error:" + method);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static void main(String[] args) {
		UnionPayParamStatic a = new UnionPayParamStatic();
		HashMap<String, String> map = getValue(a);
		System.out.println(map);
	}
}
