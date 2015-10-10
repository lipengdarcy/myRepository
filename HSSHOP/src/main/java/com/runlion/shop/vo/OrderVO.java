package com.runlion.shop.vo;

import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspInvoice;
import com.runlion.shop.entity.BspOrderext;

public class OrderVO {
	// 订单中产品id
	private String productids;
	// 收货地址id
	private Integer saId;
	// 收货地区id
	private Integer regionId;
	// 配送方式
	private String shipType;
	// 配送时间
	private String date;
	// 订单备注
	private String buyerRemark;

	private String ip;

	private String beginDate;

	private String endDate;

	private String factoryAddress;

	private String storeAddress;

	private int salerid;

	private String needinv;

	private List<BspOrderext> orderExtList = new ArrayList<BspOrderext>();

	private BspInvoice invoice;

	public BspInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(BspInvoice invoice) {
		this.invoice = invoice;
	}

	public String getNeedinv() {
		return needinv;
	}

	public void setNeedinv(String needinv) {
		this.needinv = needinv;
	}

	public List<BspOrderext> getOrderExtList() {
		return orderExtList;
	}

	public void setOrderExtList(List<BspOrderext> orderExtList) {
		this.orderExtList = orderExtList;
	}

	public int getSalerid() {
		return salerid;
	}

	public void setSalerid(int salerid) {
		this.salerid = salerid;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProductids() {
		return productids;
	}

	public void setProductids(String productids) {
		this.productids = productids;
	}

	public Integer getSaId() {
		return saId;
	}

	public void setSaId(Integer saId) {
		this.saId = saId;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public String getShipType() {
		return shipType;
	}

	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBuyerRemark() {
		return buyerRemark;
	}

	public void setBuyerRemark(String buyerRemark) {
		this.buyerRemark = buyerRemark;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "OrderVO [productids=" + productids + ", saId=" + saId
				+ ", regionId=" + regionId + ", shipType=" + shipType
				+ ", date=" + date + ", buyerRemark=" + buyerRemark + "]";
	}

}
