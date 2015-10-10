package com.runlion.shop.controller.home.center.help;

import java.util.ResourceBundle;

import com.runlion.shop.vo.MailConfigVO;

public class SendMail {
	public void sendMail(String verification,String mail,MailConfigVO configVo){
		// 设置邮件服务器信息
//		ResourceBundle bundle = ResourceBundle.getBundle("mail");
		Mailmodel mailInfo = new Mailmodel();
		mailInfo.setMailServerIP(configVo.getHost());
		mailInfo.setMailServerPort(configVo.getPort());
		mailInfo.setMailServerUserName(configVo.getFromName());
		mailInfo.setFromAddress(configVo.getFrom());
		mailInfo.setMailServerPassword(configVo.getPassword());
//		mailInfo.setMailServerIP(bundle.getString("mailServerIP"));
//		mailInfo.setMailServerPort(bundle.getString("mailServerPort"));
		mailInfo.setValidate(true);
		// 邮箱用户名
//		mailInfo.setMailServerUserName(bundle.getString("mailServerUserName"));
//		// 邮箱密码
//		mailInfo.setMailServerPassword(bundle.getString("mailServerPassword"));
//		// 发件人邮箱
//		mailInfo.setFromAddress(bundle.getString("fromAddress"));
		// 收件人邮箱
		mailInfo.setToAddress(mail);
		// 邮件标题
		mailInfo.setSubject("红狮集团邮箱绑定邮件");
		mailInfo.setValidate(true);
		// 邮件内容
		StringBuffer buffer = new StringBuffer();
		buffer.append("<br/><span>红狮水泥商城</span><br/>");
		buffer.append("<br/><span>这封信是由红狮集团发送的</span><br/>");
		buffer.append("<span>您收到这封邮件，是用于红狮水泥商城邮箱身份认证的，如果您并不需要认证，请忽略这封邮件。</span><br/>");
		buffer.append("<br/><br/>");
		buffer.append("<span>-------------------------------------------------<span><br/>");
		buffer.append("<span>邮件说明<span><br/>");
		buffer.append("<span>-------------------------------------------------<span><br/><br/>");
		buffer.append("<span>本次认证的验证码是："+verification+"<span><br/>");
		buffer.append("<span>感谢您的访问，祝您使用愉快！<span/><br/>");
		buffer.append("<span>此致<span/><br/>");
		mailInfo.setContent(buffer.toString());

		// 发送邮件
		//SimpleMailSender sms = new SimpleMailSender();
		// 发送文体格式
		//sms.sendTextMail(mailInfo);
		// 发送html格式
		SimpleMailSender.sendHtmlMail(mailInfo);
	}
}
