package com.runlion.shop.controller.home.center.help;

import java.util.Properties;

public class Mailmodel {
	private String mailServerIP;//邮件服务器ip
	private String mailServerPort;//邮件服务器端口
	private String fromAddress;//发件人邮箱地址
	private String toAddress;//收件人邮箱地址
	private String mailServerUserName;//登录邮件发送服务器的用户名
	private String mailServerPassword;//密码
	private boolean validate = false;//是否需要身份验证
	private String subject;//邮件主题
	private String content;//邮件内容
	private String[] attachFileNames;//附件的文件名
	private String ip;//邮箱返回地址
	public Properties getProperties(){
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerIP);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", validate ? "true" : "false");
		return p;
	}
	public String getMailServerIP() {
		return mailServerIP;
	}

	public void setMailServerIP(String mailServerIP) {
		this.mailServerIP = mailServerIP;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getMailServerUserName() {
		return mailServerUserName;
	}

	public void setMailServerUserName(String mailServerUserName) {
		this.mailServerUserName = mailServerUserName;
	}

	public String getMailServerPassword() {
		return mailServerPassword;
	}

	public void setMailServerPassword(String mailServerPassword) {
		this.mailServerPassword = mailServerPassword;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
