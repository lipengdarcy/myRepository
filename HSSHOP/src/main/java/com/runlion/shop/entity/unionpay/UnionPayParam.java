package com.runlion.shop.entity.unionpay;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * 银联支付参数对象
 * */

public class UnionPayParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7871501535019940712L;

	// 版本号
	private String version = "5.0.0";

	// 字符集编码 默认"UTF-8"
	private String encoding = "UTF-8";

	// 签名方法 01 RSA
	private String signMethod = "01";

	// 交易类型 01-消费
	private String txnType = "01";

	// 交易子类型 01:自助消费 02:订购 03:分期付款
	private String txnSubType = "01";

	// 业务类型
	private String bizType = "000201";

	// 渠道类型，07-PC，08-手机
	private String channelType = "07";

	// 前台通知地址 ，控件接入方式无作用
	private String frontUrl = "http://localhost:8080/HSSNSHOPADMIN/testpay/acp_front_url.do";

	// 后台通知地址
	private String backUrl = "http://localhost:8080/HSSNSHOPADMIN/testpay/acp_back_url.do";

	// 接入类型，商户接入填0 0：商户 ， 1： 收单， 2：平台商户
	private String accessType = "0";

	// 商户号码，请改成自己的商户号
	private String merId = "777290058117132";

	// 商户订单号，8-40位数字字母
	private String orderId = "12345678";

	// 订单发生时间
	private String txnTime = "201506121440391400";

	// 交易金额
	private String txnAmt = "1";

	// 交易币种
	private String currencyCode = "156";

	// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
	private String reqReserved = "透传信息";

	// 订单描述，可不上送，上送时控件中会显示该信息
	private String orderDesc = "订单描述";

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getTxnSubType() {
		return txnSubType;
	}

	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	public String getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getReqReserved() {
		return reqReserved;
	}

	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	@SuppressWarnings("rawtypes")
	public HashMap<String, String> generateMapValue(Object thisObj) {
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
							key = key.substring(0, 1).toLowerCase()
									+ key.substring(1);
							map.put(key, value);
						}
					} catch (Exception e) {
						// System.out.println("error:" + method);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public HashMap<String, String> generateMapValue2(Object object) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			// 获得对象的类型
			Class<?> classType = object.getClass();
			// 通过默认构造方法创建一个新的对象
			Object objectCopy = classType.getConstructor(new Class[] {})
					.newInstance(new Object[] {});

			// 获得对象的所有属性
			Field fields[] = classType.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fieldName = field.getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				// 获得和属性对应的getXXX()方法的名字
				String getMethodName = "get" + firstLetter
						+ fieldName.substring(1);
				// 获得和属性对应的getXXX()方法
				Method getMethod = classType.getMethod(getMethodName,
						new Class[] {});
				// 调用原对象的getXXX()方法
				String value = (String) getMethod.invoke(object,
						new Object[] {});
				map.put(fieldName, value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public Object copy(Object object) throws Exception {
		// 获得对象的类型
		Class<?> classType = object.getClass();
		System.out.println("Class:" + classType.getName());

		// 通过默认构造方法创建一个新的对象
		Object objectCopy = classType.getConstructor(new Class[] {})
				.newInstance(new Object[] {});

		// 获得对象的所有属性
		Field fields[] = classType.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			System.out.println("name=====" + fieldName);
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			// 获得和属性对应的getXXX()方法的名字
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			// 获得和属性对应的setXXX()方法的名字
			String setMethodName = "set" + firstLetter + fieldName.substring(1);

			// 获得和属性对应的getXXX()方法
			Method getMethod = classType.getMethod(getMethodName,
					new Class[] {});
			// 获得和属性对应的setXXX()方法
			Method setMethod = classType.getMethod(setMethodName,
					new Class[] { field.getType() });

			// 调用原对象的getXXX()方法
			Object value = getMethod.invoke(object, new Object[] {});
			System.out.println("value====" + value);
			System.out.println(fieldName + ":" + value);
			// 调用拷贝对象的setXXX()方法
			setMethod.invoke(objectCopy, new Object[] { value });
		}
		return objectCopy;
	}

	public static void main(String[] args) {
		UnionPayParam a = new UnionPayParam();

		System.out.println(a);
		HashMap<String, String> map = a.generateMapValue(a);
		System.out.println(map);
		HashMap<String, String> map2 = a.generateMapValue2(a);
		System.out.println(map2);
	}
}
