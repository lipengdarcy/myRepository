package com.runlion.shop.controller;

import com.runlion.shop.common.util.Mail;

/**
 * 邮件服务类
 * @author songjinsheng
 * */
public class MailUtil {
	/**
	 * 发送邮件
	 * 
	 * @param toMail
	 *            收件人地址
	 * @param fromMail
	 *            发送人地址，即发送者的用户名
	 * @param fromMailPassWord
	 *            发送者的邮箱密码
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @return boolean 发送成功与否，true 成功
	 * */
	public static boolean  sendMail(String toMail, String fromMail,
			String fromMailPassWord, String subject, String content) {
		// 创建邮件
		// String toMail = "sjshdyx2@163.com";//
		Mail mail = new Mail();
		mail.setTo(toMail);
		mail.setFrom(fromMail);// 你的邮箱
		// sjshdyx@sina.com
		fromMail.indexOf("@");
		String host = "smtp."
				+ fromMail.substring(fromMail.indexOf("@") + 1,
						fromMail.length());
		mail.setHost(host);// 发送者的邮箱主机 "smtp.sina.com"
		mail.setUsername(fromMail);// 发件用户
		mail.setPassword(fromMailPassWord);// 发件人密码
		mail.setSubject("红狮商场帮你找回您的账户密码");
		mail.setContent(content);
		boolean send=mail.toSend();
		if (send) {
			System.out.println("您的申请已提交成功，请查看您邮箱:" + toMail);
		} else {
			System.out.println("操作失败，轻稍后重新尝试！");
		}
		return send;
	}
}
