package com.runlion.shop.vo;

import java.math.BigDecimal;

public class ProductExtPriceVO {

	private Integer pid;
	private BigDecimal storeExtPrice;
	private BigDecimal facExtPrice;
	private BigDecimal shiptoExtPrice;

	//
	public BigDecimal getStoreExtPrice() {
		return storeExtPrice;
	}

	public void setStoreExtPrice(BigDecimal storeExtPrice) {
		this.storeExtPrice = storeExtPrice;
	}

	public BigDecimal getFacExtPrice() {
		return facExtPrice;
	}

	public void setFacExtPrice(BigDecimal facExtPrice) {
		this.facExtPrice = facExtPrice;
	}

	public BigDecimal getShiptoExtPrice() {
		return shiptoExtPrice;
	}

	public void setShiptoExtPrice(BigDecimal shiptoExtPrice) {
		this.shiptoExtPrice = shiptoExtPrice;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
