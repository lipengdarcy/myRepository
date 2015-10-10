package com.runlion.shop.vo;

public class MailConfigVO {
	/**
	 * 主机地址
	 */
	private String Host;
	/**
	 * 端口
	 */
	private String Port;
	/**
	 * 邮箱账号
	 */
	private String UserName;
	/**
	 * 邮箱密码
	 */
	private String Password;
	/**
	 * 发送邮箱
	 */
	private String From;
	/**
	 * 发送人姓名
	 */
	private String FromName;
	/**
	 * 找回密码内容
	 */
	private String FindPwdBody;
	/**
	 * 验证邮箱内容
	 */
	private String SCVerifyBody;
	/**
	 * 更新邮箱内容
	 */
	private String SCUpdateBody;
	/**
	 * 欢迎内容
	 */
	private String WebcomeBody;

	public String getHost() {
		return Host;
	}

	public void setHost(String host) {
		Host = host;
	}

	public String getPort() {
		return Port;
	}

	public void setPort(String port) {
		Port = port;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}

	public String getFromName() {
		return FromName;
	}

	public void setFromName(String fromName) {
		FromName = fromName;
	}

	public String getFindPwdBody() {
		return FindPwdBody;
	}

	public void setFindPwdBody(String findPwdBody) {
		FindPwdBody = findPwdBody;
	}

	public String getSCVerifyBody() {
		return SCVerifyBody;
	}

	public void setSCVerifyBody(String sCVerifyBody) {
		SCVerifyBody = sCVerifyBody;
	}

	public String getSCUpdateBody() {
		return SCUpdateBody;
	}

	public void setSCUpdateBody(String sCUpdateBody) {
		SCUpdateBody = sCUpdateBody;
	}

	public String getWebcomeBody() {
		return WebcomeBody;
	}

	public void setWebcomeBody(String webcomeBody) {
		WebcomeBody = webcomeBody;
	}

}
