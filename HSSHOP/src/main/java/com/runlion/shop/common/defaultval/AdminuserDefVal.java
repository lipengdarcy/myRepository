package com.runlion.shop.common.defaultval;

public class AdminuserDefVal {

	public static final String DEF_PASSWORD = "123456";

	public static final String TEL_USERID_PREFIX = "u:";

	public static final String TEL_NUM_HS_ELESHOP = "0579-88256171";

	/**
	 * 下单短信，下单成功是发给客户
	 */
	public static final String MSG_SAVE_ORDER = "尊敬的{0}，您好！您{1}在红狮水泥商城订购{2}水泥，订单号为：{3}，"
			+ "验证码：{4}，已提交成功，请在{5}时间内提货。超过{6}时间，订单号自动失效，请重新下单获取订单号,"
			+ "短信如有遗漏，后果自行承担。（水泥电商处：{7}）";
	/**
	 * 提货短息。提货货发给客户
	 */
	public static final String MSG_PICK_GOODS = "尊敬的客户，您好！"
			+ "{0}从{1}公司提货{2}，共计{3}元。" + "（水泥电商处：{4}）";
	// public static final String MSG_PICK_GOODS = "尊敬的客户，您好！"
	// + "{0}由{1}车从{2}公司提货{3}车{4}，共计{5}元。" + "（水泥电商处：{6}）";
	/**
	 * 乡镇门店提醒短信。
	 */
	public static final String MSG_STORE_ALERT = "尊敬的客户，您好！您所在区域在电商平台提货{0}。";
	/**
	 * 门店自提到店支付订单提醒短息
	 */
	public static final String MSG_SELF_PICK_ALERT = "尊敬的客户，您好！您所在区域{0}客户在红狮商城下单成功，订单号：{1}，"
			+ "该订单自行收款{2}元，于{3}来店提货，提货人联系电话：{4}。";
	/**
	 * 配送到家到店支付订单提醒短信
	 */
	public static final String MSG_HOME_DELIVERY_ALERT = "尊敬的客户，您好！您所在区域{0}客户在红狮商城下单成功，订单号：{1}，"
			+ "该订单自行收款{2}元，于{3}送货到家，收货人联系电话：{4}。";

	public static String[] payNames = { "到店支付", "到厂支付", "货到付款", "在线支付" };

}
