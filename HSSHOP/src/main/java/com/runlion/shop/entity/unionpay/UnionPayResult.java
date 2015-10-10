package com.runlion.shop.entity.unionpay;

import java.lang.reflect.Method;
import java.util.Map;

/*
 txnType(txnType)	01
 respCode(respCode)	00
 currencyCode(currencyCode)	156
 merId(merId)	777290058117132
 settleDate(settleDate)	0825
 txnSubType(txnSubType)	01
 version(version)	5.0.0
 txnAmt(txnAmt)	1125
 signMethod(signMethod)	01
 accNo(accNo)	6216***********0018
 certId(certId)	3474813271258769001041842579301293446
 settleAmt(settleAmt)	1125
 traceTime(traceTime)	0821173843
 settleCurrencyCode(settleCurrencyCode)	156
 encoding(encoding)	UTF-8
 bizType(bizType)	000201
 reqReserved(reqReserved)	͸����Ϣ
 respMsg(respMsg)	success
 traceNo(traceNo)	761728
 queryId(queryId)	201508211738437617288
 signature(signature)	YxKgZOlbNazThGedEbNMhxVe194z/5IG7VvuI34NngZ5oS3TEwQlcFHGKSJq0JD+tkpXAt4ISqBw2qFmOjlab3eSJbk0Iy8ct6Q/vfzfe7XID3bPOJRy9beqGQFtxSChCQVNIijP+xmm3wzn6spL0SCx6raSvxJMF2uzDXJ/eMcunDVOoSdF5UqTSaV3UZ4m0Ty6xdvs4aUxkfOl/frcgsHwuc/jD43DtZV7dbLFR5LPURfQX+4LnGKQCrv9UWDXC6vqeNyobyT03TM6OFrnbkVQjtBfDI5klpnL8Mi25GKZpVb3tOGfyvP5wLStZPbSI02um7ee2rdk1VFOTmxRXQ==
 orderId(orderId)	150821173813114
 txnTime(txnTime)	20150821173843
 accessType(accessType)	0
 */
public class UnionPayResult {

	private Integer id;

	private String respCode;

	private String origRespMsg;

	private String origRespCode;

	private String txnSubType;

	private String version;

	private String txnAmt;

	private String signMethod;

	private String settleAmt;

	private String encoding;

	private String traceTime;

	// / <summary>
	// / 成功[0000000]
	// / </summary>
	private String respMsg;

	private String queryId;

	private String signature;

	private String orderId;

	private String txnType;

	private String currencyCode;

	private String merId;

	private String settleDate;

	private String accNo;

	private String certId;

	private String settleCurrencyCode;

	// / <summary>
	// / 透传信息
	// / </summary>
	private String reqReserved;

	private String bizType;

	private String traceNo;

	private String issuerIdentifyMode;

	private String accessType;

	private String txnTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getOrigRespMsg() {
		return origRespMsg;
	}

	public void setOrigRespMsg(String origRespMsg) {
		this.origRespMsg = origRespMsg;
	}

	public String getOrigRespCode() {
		return origRespCode;
	}

	public void setOrigRespCode(String origRespCode) {
		this.origRespCode = origRespCode;
	}

	public String getTxnSubType() {
		return txnSubType;
	}

	public void setTxnSubType(String txnSubType) {
		this.txnSubType = txnSubType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTxnAmt() {
		return txnAmt;
	}

	public void setTxnAmt(String txnAmt) {
		this.txnAmt = txnAmt;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getTraceTime() {
		return traceTime;
	}

	public void setTraceTime(String traceTime) {
		this.traceTime = traceTime;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public String getSettleCurrencyCode() {
		return settleCurrencyCode;
	}

	public void setSettleCurrencyCode(String settleCurrencyCode) {
		this.settleCurrencyCode = settleCurrencyCode;
	}

	public String getReqReserved() {
		return reqReserved;
	}

	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(String traceNo) {
		this.traceNo = traceNo;
	}

	public String getIssuerIdentifyMode() {
		return issuerIdentifyMode;
	}

	public void setIssuerIdentifyMode(String issuerIdentifyMode) {
		this.issuerIdentifyMode = issuerIdentifyMode;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(String txnTime) {
		this.txnTime = txnTime;
	}

	@SuppressWarnings("rawtypes")
	public Object mapToObject(UnionPayResult thisObj, Map map) {

		try {
			Class c = Class.forName(thisObj.getClass().getName());
			Method[] m = c.getMethods();
			for (int i = 0; i < m.length; i++) {
				String method = m[i].getName();
				if (method.startsWith("set")) {
					try {

						String key = method.substring(3);
						key = key.substring(0, 1).toLowerCase()
								+ key.substring(1);
						// 取值
						String value = (String) map.get(key);
						if (value == null)
							continue;
						// 赋值
						m[i].invoke(thisObj, value);
					} catch (Exception e) {
						return thisObj;
						// System.out.println("error:" + method);
					}
				}
			}
		} catch (Exception e) {
			return thisObj;
		}
		return thisObj;
	}

}
