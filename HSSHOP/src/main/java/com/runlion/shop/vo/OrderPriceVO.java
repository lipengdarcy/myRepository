package com.runlion.shop.vo;

public class OrderPriceVO {
	// 产品总价钱
	private String productPrice;
	// 运费
	private String shipFee;
	// 装卸费
	private String handlingCost;
	// 总数
	private int count;
	private int type;
	private double buyminquan;
	private String cost;

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public double getBuyminquan() {
		return buyminquan;
	}

	public void setBuyminquan(double buyminquan) {
		this.buyminquan = buyminquan;
	}

	// 总价钱
	private String totalPrice;

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getShipFee() {
		return shipFee;
	}

	public void setShipFee(String shipFee) {
		this.shipFee = shipFee;
	}

	public String getHandlingCost() {
		return handlingCost;
	}

	public void setHandlingCost(String handlingCost) {
		this.handlingCost = handlingCost;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

}
