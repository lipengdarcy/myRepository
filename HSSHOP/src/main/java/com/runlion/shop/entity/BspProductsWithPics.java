package com.runlion.shop.entity;

import java.util.List;

public class BspProductsWithPics implements Cloneable {

	private List<BspProductimages> picList;

	private BspProducts product;

	public List<BspProductimages> getPicList() {
		return picList;
	}

	public void setPicList(List<BspProductimages> picList) {
		this.picList = picList;
	}

	public BspProducts getProduct() {
		return product;
	}

	public void setProduct(BspProducts product) {
		this.product = product;
	}

}