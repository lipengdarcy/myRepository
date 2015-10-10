package com.runlion.shop.vo;

import com.runlion.shop.entity.BspOrders;

public class OrdersStateVO implements Cloneable {
	private BspOrders bspOrders;

	public BspOrders getBspOrders() {
		return bspOrders;
	}

	public void setBspOrders(BspOrders bspOrders) {
		this.bspOrders = bspOrders;
	}

	private String statename;

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

}
