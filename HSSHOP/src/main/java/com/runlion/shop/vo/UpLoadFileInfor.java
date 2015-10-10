package com.runlion.shop.vo;

import com.runlion.shop.common.util.uploadfile.UploadState;

public class UpLoadFileInfor {

	private String urlDateFileName;
	private String xeDateFileName;
	private UploadState uploadState;

	public UploadState getUploadState() {
		return uploadState;
	}

	public void setUploadState(UploadState uploadState) {
		this.uploadState = uploadState;
	}

	public String getUrlDateFileName() {
		return urlDateFileName;
	}

	public void setUrlDateFileName(String urlDateFileName) {
		this.urlDateFileName = urlDateFileName;
	}

	public String getXeDateFileName() {
		return xeDateFileName;
	}

	public void setXeDateFileName(String xeDateFileName) {
		this.xeDateFileName = xeDateFileName;
	}

}
