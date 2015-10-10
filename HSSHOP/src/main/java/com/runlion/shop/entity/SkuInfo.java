package com.runlion.shop.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SkuInfo implements Comparable<SkuInfo>, Serializable {
	private Short attrid; // 属性ID
	private String attrname; // 属性名称
	private List<BspProductskusRTM> skuList;

	// 按pid排序
	@Override
	public int compareTo(SkuInfo arg0) {
		// return this.getAttrid().compareTo(arg0.getAttrid());
		return arg0.getAttrid().compareTo(this.getAttrid());
	}

	private Map<Integer, List<BspProductskusRTM>> brand_factory; // 品牌 产地对应关系

	public Map<Integer, List<BspProductskusRTM>> getBrand_factory() {
		return brand_factory;
	}

	public void setBrand_factory(
			Map<Integer, List<BspProductskusRTM>> brand_factory) {
		this.brand_factory = brand_factory;
	}

	public Short getAttrid() {
		return attrid;
	}

	public void setAttrid(Short attrid) {
		this.attrid = attrid;
	}

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public List<BspProductskusRTM> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<BspProductskusRTM> skuList) {
		this.skuList = skuList;
	}

}