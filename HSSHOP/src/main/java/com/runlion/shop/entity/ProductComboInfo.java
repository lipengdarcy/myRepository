package com.runlion.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ProductComboInfo implements Serializable {
	private Short attrid; // 属性ID
	private String attrname; // 属性名称
	private List<ProductLink> productlinklist;

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

	public List<ProductLink> getProductlinklist() {
		return productlinklist;
	}

	public void setProductlinklist(List<ProductLink> productlinklist) {
		this.productlinklist = productlinklist;
	}

}