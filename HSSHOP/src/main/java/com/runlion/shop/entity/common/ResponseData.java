package com.runlion.shop.entity.common;

public class ResponseData {
	private String state;
	private String content;

	public ResponseData(String statusCode, String content) {
		this.state = statusCode;
		this.content = content;
	}

	public ResponseData(String content) {
		this.state = "success";
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
