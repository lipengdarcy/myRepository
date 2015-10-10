package com.runlion.shop.vo;

import java.math.BigDecimal;

/**
 * 购物车快照
 *
 */
public class CartSnapVO {
	// 产品名称
	private String name;
	// 销售价钱
	private String marketprice;
	// 图片
	private String showimg;
	// 购物车数量
	private int count;
	// 产品id
	private int pid;
	// 价格类型
	private int type;
	// 产品重量
	private float weight;
	// 物品运费
	private BigDecimal freight;
	// 物品装卸费
	private BigDecimal carry;
	private String url;
	// 物品装卸费
	private int quantityunitid;
	// 原价
	private String originalPrice;
	// 该条目要花费的总额
	private BigDecimal itemTotalMny;

	public BigDecimal getItemTotalMny() {
		return itemTotalMny;
	}

	public void setItemTotalMny(BigDecimal itemTotalMny) {
		this.itemTotalMny = itemTotalMny;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public int getQuantityunitid() {
		return quantityunitid;
	}

	public void setQuantityunitid(int quantityunitid) {
		this.quantityunitid = quantityunitid;
	}

	// 产品id
	private String productId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public BigDecimal getCarry() {
		return carry;
	}

	public void setCarry(BigDecimal carry) {
		this.carry = carry;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarketprice() {
		return marketprice;
	}

	public void setMarketprice(String marketprice) {
		this.marketprice = marketprice;
	}

	public String getShowimg() {
		return showimg;
	}

	public void setShowimg(String showimg) {
		this.showimg = showimg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}
