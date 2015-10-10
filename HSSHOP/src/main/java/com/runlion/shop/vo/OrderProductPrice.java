package com.runlion.shop.vo;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductPrice {
	// 订单总价钱
	private BigDecimal totalOrderPrice = new BigDecimal(0);
	// 产品总价钱
	private BigDecimal totalProductPrice = new BigDecimal(0);
	// 运费
	private BigDecimal shipFee = new BigDecimal(0);
	// 装卸费
	private BigDecimal handlingCost = new BigDecimal(0);
	// 总数量
	private int totalCount;
	// 总重量
	private float totalWeight;
	// 购物车
	private List<CartSnapVO> cartSnapList;
	private int regionsId;
	// 物品运费(订单)
	private String freight;
	// 物品装卸费(订单)
	private String carry;
	// 原价
	private String originalPrice;

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getCarry() {
		return carry;
	}

	public void setCarry(String carry) {
		this.carry = carry;
	}

	public String getFreight() {
		return freight;
	}

	public String getPrice() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public OrderProductPrice(List<CartSnapVO> cartSnapList, int regionsId) {
		super();
		this.cartSnapList = cartSnapList;
		this.regionsId = regionsId;
	}

	public int getRegionsId() {
		return regionsId;
	}

	public void setRegionsId(int regionsId) {
		this.regionsId = regionsId;
	}

	public BigDecimal getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(BigDecimal totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public BigDecimal getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(BigDecimal totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	public BigDecimal getShipFee() {
		return shipFee;
	}

	public void setShipFee(BigDecimal shipFee) {
		this.shipFee = shipFee;
	}

	public BigDecimal getHandlingCost() {
		return handlingCost;
	}

	public void setHandlingCost(BigDecimal handlingCost) {
		this.handlingCost = handlingCost;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public float getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(float totalWeight) {
		this.totalWeight = totalWeight;
	}

	public List<CartSnapVO> getCartSnapList() {
		return cartSnapList;
	}

	public void setCartSnapList(List<CartSnapVO> cartSnapList) {
		this.cartSnapList = cartSnapList;
	}

}
