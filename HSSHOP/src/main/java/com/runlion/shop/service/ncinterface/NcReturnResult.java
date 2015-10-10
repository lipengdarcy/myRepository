package com.runlion.shop.service.ncinterface;

//nc交互返回结果
public class NcReturnResult {

	private String state = "success"; // 状态，成功：success
	private String msg; // 到账时间

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
