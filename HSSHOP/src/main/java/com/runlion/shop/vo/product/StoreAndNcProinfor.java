package com.runlion.shop.vo.product;

import java.util.Date;

public class StoreAndNcProinfor {
	/**
	 * 经销商门店的主键
	 */
	private Integer sid;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 联系人
	 */
	private String contacts;

	/**
	 * bsp_ncenterinfor的主键
	 */
	private Integer id;

	/**
	 * 经销商的NC编号
	 */
	private String eid;

	/**
	 * 经销商名称
	 */
	private String ename;

	/**
	 * 工厂编号
	 */
	private String pkcorp;

	/**
	 * 工厂名称
	 */
	private String corpname;

	/**
	 * 产品的NC编码
	 */
	private String pid;

	/**
	 * 产品名称
	 */
	private String pname;

	/**
	 * 单价
	 */
	private String price;

	/**
	 * 状态
	 */
	private Integer state;

	/**
	 * 最后更新时间
	 */
	private Date lastuptime;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getPkcorp() {
		return pkcorp;
	}

	public void setPkcorp(String pkcorp) {
		this.pkcorp = pkcorp;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getLastuptime() {
		return lastuptime;
	}

	public void setLastuptime(Date lastuptime) {
		this.lastuptime = lastuptime;
	}
}
