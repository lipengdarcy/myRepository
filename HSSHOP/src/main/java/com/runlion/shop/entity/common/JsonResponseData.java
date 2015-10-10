package com.runlion.shop.entity.common;

/**
 * Filename: JsonResponseData.java <br>
 * Title: 返回数据实体<br>
 * De.ion: 统一返回数据格式<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月2日 <br>
 * Time: 上午10:57:18 <br>
 * Version: 1.0.0 <br>
 */
public class JsonResponseData<T> {
	private boolean Success;

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

	public T getResult() {
		return Result;
	}

	public void setResult(T result) {
		Result = result;
	}

	public String getErrorNo() {
		return ErrorNo;
	}

	public void setErrorNo(String errorNo) {
		ErrorNo = errorNo;
	}

	private String ErrorMsg;
	private T Result;
	private String ErrorNo;
}
