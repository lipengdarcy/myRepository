package com.runlion.shop.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ProductLink implements Serializable {

	private BspAttributevalues attrValue; // 属性value
	private String path; // 产品筛选链接

	// private String regionId;

	private Map<Integer, List<ProductLink>> brand_factory; // 品牌 产地对应关系

	public Map<Integer, List<ProductLink>> getBrand_factory() {
		return brand_factory;
	}

	public void setBrand_factory(Map<Integer, List<ProductLink>> brand_factory) {
		this.brand_factory = brand_factory;
	}

	private String pathcenterStr;

	public String getPathcenterStr() {
		return pathcenterStr;
	}

	public void setPathcenterStr(String pathcenterStr) {
		this.pathcenterStr = pathcenterStr;
	}

	public BspAttributevalues getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(BspAttributevalues attrValue) {
		this.attrValue = attrValue;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}