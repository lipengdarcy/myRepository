package com.runlion.shop.vo;

import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspProductattributes;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductstocks;

public class ProductAttrVO {

	private BspProducts product = new BspProducts();

	private List<BspProductattributes> skuList = new ArrayList();

	BspProductstocks productstocks = new BspProductstocks();

	public BspProducts getProduct() {
		return product;
	}

	public void setProduct(BspProducts product) {
		this.product = product;
	}

	public List<BspProductattributes> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<BspProductattributes> skuList) {
		this.skuList = skuList;
	}

	public BspProductstocks getProductstocks() {
		return productstocks;
	}

	public void setProductstocks(BspProductstocks productstocks) {
		this.productstocks = productstocks;
	}

}
