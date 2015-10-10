package com.runlion.shop.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import com.runlion.shop.common.util.Mail;

public class SendTest {
	public static void sendMail() {
		// 创建邮件
		String toMail = "sjshdyx2@163.com";//
		Mail mail = new Mail();
		mail.setTo(toMail);
		mail.setFrom("sjshdyx@sina.com");// 你的邮箱
		mail.setHost("smtp.sina.com");
		mail.setUsername("sjshdyx@sina.com");// 发件用户
		mail.setPassword("wmxchzh");// 发件人密码
		mail.setSubject("红狮商场帮你找回您的账户密码");
		// 生成生效日期
		long starttime = System.currentTimeMillis();
		mail.setContent("<a href='http://192.168.158.81:8080/HSSNSHOP/business/valideLink.do?code="
				+ starttime + "&&user=test+" + "'>点击找回密码，本邮件超过30分钟后将失效！</a>");
		if (mail.toSend()) {
			System.out.println("您的申请已提交成功，请查看您邮箱:" + toMail);
		} else {
			System.out.println("操作失败，轻稍后重新尝试！");
		}

	}

	

	public static void main(String[] args) throws UnsupportedEncodingException {
		//sendMail();
		long starttime = System.currentTimeMillis();
		String bytes = Arrays.toString("红狮商场帮你找回您的账户密码".getBytes());
		String content="<a href='http://192.168.158.81:8080/HSSNSHOP/business/valideLink.do?code="
				+ starttime + "&&user=test+" + "'>点击找回密码，本邮件超过30分钟后将失效！names="+bytes+"</a>";
		Mail.sendMail("sjshdyx2@163.com", "sjshdyx@sina.com", "wmxchzh", "红狮商场帮你找回您的账户密码", content);
	
	}
}
