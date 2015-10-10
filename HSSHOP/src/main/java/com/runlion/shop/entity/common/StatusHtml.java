package com.runlion.shop.entity.common;

public class StatusHtml {
	public static void main(String[] args) {
		StatusHtml statusHtml = new StatusHtml();
		statusHtml.setStatusCode("200");
		statusHtml.setMessage("操作成功");
		statusHtml.setNavTabId("");
		statusHtml.setRel("");
		statusHtml.setCallbackType("");
		statusHtml.setForwardUrl("");
		statusHtml.setConfirmMsg("");
		// System.out.println(JSONObject.fromObject(statusHtml));

	}

	private String statusCode = "200";
	private String message = "操作成功";
	private String navTabId;
	private String rel;
	private String callbackType;
	private String forwardUrl;
	private String confirmMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getConfirmMsg() {
		return confirmMsg;
	}

	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

}
