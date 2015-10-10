package com.runlion.shop.common.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 邮件类
 * 
 * @author songjinsheng
 * */
public class Mail {
	String to = ""; // 收件人 :xxx@163.com
	String from = ""; // 发件人 :yyyy@sina.com
	String host = ""; // smtp主机 如163为：smtp.163.com
	String username = ""; // 发信人的用户名
	String password = ""; // 发信人的密码
	String subject = ""; // 邮件主题
	String content = ""; // 邮件正文

	public Mail() {
	}

	public Mail(String to, String from, String host, String username,
			String password, String subject, String content) {
		this.to = to;
		this.from = from;
		this.host = host;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.content = content;
	}

	/**
	 * 把主题转换为中文
	 * 
	 * @param strText
	 * @return String
	 */
	public String transferChinese(String strText) {

		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(),
					"UTF-8"), "UTF-8", "B"); // GB2312
		} catch (Exception e) {
			e.printStackTrace();
		}

		return strText;
	}

	/**
	 * 发送邮件
	 * 
	 * @return 成功返回true，失败返回false
	 */
	public boolean toSend() {
		// 构造mail session
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {
			// 构造MimeMessage并设定基本的值，创建消息对象
			MimeMessage msg = new MimeMessage(session);
			// 设置消息内容
			msg.setFrom(new InternetAddress(from));
			// System.out.println(from);
			// 把邮件地址映射到Internet地址上
			InternetAddress[] address = { new InternetAddress(to) };
			/**
			 * setRecipient（Message.RecipientType type, Address
			 * address），用于设置邮件的接收者。<br>
			 * 有两个参数，第一个参数是接收者的类型，第二个参数是接收者。<br>
			 * 接收者类型可以是Message.RecipientType .TO，Message
			 * .RecipientType.CC和Message.RecipientType.BCC，TO表示主要接收人，CC表示抄送人
			 * ，BCC表示秘密抄送人。接收者与发送者一样，通常使用InternetAddress的对象。
			 */
			msg.setRecipients(Message.RecipientType.TO, address);
			// 设置邮件的标题
			// subject = transferChinese(subject);
			msg.setSubject(subject);
			// 构造Multipart
			Multipart mp = new MimeMultipart();

			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			// 设置邮件内容(纯文本格式)
			// mbpContent.setText(content);
			// 设置邮件内容(HTML格式)
			mbpContent.setContent(content, "text/html;charset=utf-8");
			// 向MimeMessage添加（Multipart代表正文）
			mp.addBodyPart(mbpContent);
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			// 设置邮件发送的时间。
			msg.setSentDate(new Date());
			// 发送邮件
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	/**
	 * 发送邮件
	 * 
	 * @param toMail
	 *            收件人地址
	 * @param fromMail
	 *            发送人地址，即发送者的用户名
	 * @param fromMailPassWord
	 *            发送者的邮箱密码
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件内容
	 * @return boolean 发送成功与否，true 成功
	 * */
	public static boolean sendMail(String toMail, String fromMail,
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
		boolean send = mail.toSend();
		if (send) {
			System.out.println("您的申请已提交成功，请查看您邮箱:" + toMail);
		} else {
			System.out.println("操作失败，轻稍后重新尝试！");
		}
		return send;
	}

	public static void main(String[] args) {
		Mail.sendMail("sjshdyx@163.com", "sjshdyx@sina.com", "wmxchzh",
				"2323232", "hdfdfdfdfdfdf");
	}
}
