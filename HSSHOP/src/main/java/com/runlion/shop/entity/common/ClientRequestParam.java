package com.runlion.shop.entity.common;

import com.github.pagehelper.Page;

public class ClientRequestParam {

	private String ordernum;// 订单编号
	private String productname;// 产品名称
	private Byte orderstate;// 订单状态
	private String beginTime; // 查询开始时间
	private String endTime;// 查询结束时间

	private Integer userid;// 用户id

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	private Page<Object> page;

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Byte getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(Byte orderstate) {
		this.orderstate = orderstate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
