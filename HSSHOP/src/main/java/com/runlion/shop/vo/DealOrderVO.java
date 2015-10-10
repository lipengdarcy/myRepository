package com.runlion.shop.vo;

import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspOrderext;
import com.runlion.shop.entity.BspOrderpricemny;
import com.runlion.shop.entity.BspOrderproducts;
import com.runlion.shop.entity.BspOrders;

public class DealOrderVO {
	private BspOrders order;

	private List<BspOrderproducts> productList = new ArrayList<BspOrderproducts>();

	private List<BspOrderext> orderExtList = new ArrayList<BspOrderext>();

	private BspOrderpricemny orderPricemny;

	// private BspInvoice invoice;

	// public BspInvoice getInvoice() {
	// return invoice;
	// }
	//
	// public void setInvoice(BspInvoice invoice) {
	// this.invoice = invoice;
	// }
	//
	public List<BspOrderext> getOrderExtList() {
		return orderExtList;
	}

	public void setOrderExtList(List<BspOrderext> orderExtList) {
		this.orderExtList = orderExtList;
	}

	public BspOrders getOrder() {
		return order;
	}

	public void setOrder(BspOrders order) {
		this.order = order;
	}

	public List<BspOrderproducts> getProductList() {
		return productList;
	}

	public void setProductList(List<BspOrderproducts> productList) {
		this.productList = productList;
	}

	public BspOrderpricemny getOrderPricemny() {
		return orderPricemny;
	}

	public void setOrderPricemny(BspOrderpricemny orderPricemny) {
		this.orderPricemny = orderPricemny;
	}

}
