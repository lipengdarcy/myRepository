package com.runlion.shop.common;
//分支测试dev 2015.10.20
/**
 * 红狮水泥商城的常量列表
 */
public class Constant {
	/**
	 * 属性表 产地id
	 */
	public static final int factoryId = 51;
	/**
	 * 属性表 品牌id
	 */
	public static final int brandId = 52;
	/**
	 * 属性表 强度id
	 */
	public static final int strengthId = 49;
	/**
	 * 属性表 品种id
	 */
	public static final int typeId = 48;
	/**
	 * 产品区域价格
	 */
	public static final int priceType_product = 1;
	/**
	 * 区域运费
	 */
	public static final int priceType_ship = 2;
	/**
	 * 区域装卸费
	 */
	public static final int priceType_load = 3;

	public static final String NavTabId_region_ship = "page41";// 运费
	public static final String NavTabId_region_load = "page42";// 装卸费
	public static final String NavTabId_region_price = "page43";// 产品价格
	public static final String NavTabId_region_brand = "page45";// 品牌
	public static final String NavTabId_brand_factory = "page46";// 品牌产地
	public static final String NavTabId_region_factory = "page47";// 区域产地
	public static final String NavTabId_region_factoryaddress = "page49";// 工厂管理
	public static final String NavTabId_region_factoryregions = "page50";// 工厂区域管理
	public static final String NavTabId_region_storeaddress = "page51";// 门店管理
	public static final String NavTabId_region_storeregions = "page52";// 门店区域管理

	public static final String Order_Not_Exists = "OrderNotExists";// 订单不存在

	// 订单状态：0 待确认，1 已确认，2 备货中，3 已发货，4 已完成，5 锁定，6 已取消',
	public static final int OrderState_uncertain = 0;
	public static final int OrderState_certained = 1;
	public static final int OrderState_sending = 2;
	public static final int OrderState_sented = 3;
	public static final int OrderState_finished = 4;
	public static final int OrderState_locked = 5;
	public static final int OrderState_cancled = 6;

	public static final int OrderPayState_unfinished = 0;
	public static final int OrderPayState_finished = 1;

	// 门店自提在线支付短信通知模板
	public static final String MessageTemplate_store = "尊敬的客户，您好！"
			+ "您所在区域{0}客户在红狮商城下单成功，订单号：{1}，该订单已付款，于{2}年{3}月{4}日来店提货，提货人联系电话：{5}。";
	// 工厂自提在线支付短信通知模板
	public static final String MessageTemplate_factory = "尊敬的客户，您好！"
			+ "您所在区域{0}客户在红狮商城下单成功，订单号：{1}，该订单已付款，于{2}年{3}月{4}日来厂提货，提货人联系电话：{5}。";
	// 配送到家在线支付短信通知模板
	public static final String MessageTemplate_home = "尊敬的客户，您好！"
			+ "您所在区域{0}客户在红狮商城下单成功，订单号：{1}，该订单已付款，于{2}年{3}月{4}日送货到家，收货人联系电话：{5}。";

	// 1：普通用户，2：门店用户，3：工厂用户
	public static final int UserType_client = 1;
	public static final int UserType_store = 2;
	public static final int UserType_factory = 3;

	// 0：申请中， 1：申请审核通过，2：申请审核失败
	public static final int ApplyStatus_applying = 0;
	public static final int ApplyStatus_approved = 1;
	public static final int ApplyStatus_rejected = 2;

}
