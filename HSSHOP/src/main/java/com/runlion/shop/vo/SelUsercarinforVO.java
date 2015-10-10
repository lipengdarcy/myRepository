package com.runlion.shop.vo;

public class SelUsercarinforVO {

	/**
	 * 模糊匹配汽车编号或者发动机的编号的字段值
	 */
	private String carnumOrMotonum;

	private Integer uid;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCarnumOrMotonum() {
		return carnumOrMotonum;
	}

	public void setCarnumOrMotonum(String carnumOrMotonum) {
		this.carnumOrMotonum = carnumOrMotonum;
	}
}
