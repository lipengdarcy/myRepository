package com.runlion.shop.entity.zwzcd;

/**
 * Filename: PayKeyLoginEntity.java <br>
 * Title: 第三方支付登录验证实体<br>
 * De.ion: TODO(这里用一句话描述这个类的作用)<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月22日 <br>
 * Time: 下午4:34:38 <br>
 * Version: 1.0.0 <br>
 */
public class PayKeyLoginEntity {
	private String cid;// form来自电商的cid、uid、time
	private String uid;// form来自电商的cid、uid、time
	private String time;// form来自电商的cid、uid、time
	private String sign;// key密钥 来自电商发送的key，可以是由第三方支付提供的key
	private String vcode;// md5值

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
}
