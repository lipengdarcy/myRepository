package com.runlion.shop.vo;

import java.util.ArrayList;
import java.util.List;

import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.entity.BspWorktime;

public class SalerAddressVO {
	//
	private BspSaleaddress saleAddress;
	private List<BspWorktime> workTimeList = new ArrayList();
	private List<BspNcenterinfor> proList = new ArrayList();

	//
	public List<BspNcenterinfor> getProList() {
		return proList;
	}

	public void setProList(List<BspNcenterinfor> proList) {
		this.proList = proList;
	}

	public List<BspWorktime> getWorkTimeList() {
		return workTimeList;
	}

	public void setWorkTimeList(List<BspWorktime> workTimeList) {
		this.workTimeList = workTimeList;
	}

	public BspSaleaddress getSaleAddress() {
		return saleAddress;
	}

	public void setSaleAddress(BspSaleaddress saleAddress) {
		this.saleAddress = saleAddress;
	}
}
