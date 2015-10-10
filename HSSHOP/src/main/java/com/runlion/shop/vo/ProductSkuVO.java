package com.runlion.shop.vo;

import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspProductskus;
import com.runlion.shop.entity.BspSkugroup;

/**
 * 产品及sku的复合对象
 * 
 * @author lwt
 *
 */
public class ProductSkuVO {

	private BspProducts product = new BspProducts();

	private BspSkugroup skuGroup = new BspSkugroup();

	public BspSkugroup getSkuGroup() {
		return skuGroup;
	}

	public void setSkuGroup(BspSkugroup skuGroup) {
		this.skuGroup = skuGroup;
	}

	private List<BspProductskus> skuList = new ArrayList();

	public BspProducts getProduct() {
		return product;
	}

	public void setProduct(BspProducts product) {
		this.product = product;
	}

	public List<BspProductskus> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<BspProductskus> skuList) {
		this.skuList = skuList;
	}

}
