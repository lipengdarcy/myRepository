package com.runlion.shop.common.util.uploadfile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.runlion.shop.common.util.EnumsUtil;

/**
 * Filename: UploadFileParameter.java <br>
 * Title: 上传文件参数<br>
 * De.ion: TODO(这里用一句话描述这个类的作用)<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月11日 <br>
 * Time: 下午4:27:34 <br>
 * Version: 1.0.0 <br>
 */
public class UploadFileParameter {
	/**
	 * @Fields request : TODO(request)
	 */
	private HttpServletRequest request;
	/**
	 * @Fields multipartFile : TODO(multipartFile)
	 */
	private MultipartFile multipartFile;
	/**
	 * @Fields fileName : TODO(文件名称)
	 */
	private String fileName;
	/**
	 * @Fields originalFilename : TODO(MultipartFile文件名)
	 */
	private String originalFilename;
	/**
	 * @Fields pathSource : TODO(路径pathSource)
	 */
	private String pathSource;
	/**
	 * @Fields pathWater : TODO(路径pathWater)
	 */
	private String pathWater;
	/**
	 * @Fields pathThumb : TODO(路径pathThumb)
	 */
	private String pathThumb;
	/**
	 * @Fields pathType : TODO(枚举路径)
	 */
	private Integer pathType;
	/**
	 * @Fields pathDate : TODO(根据时间创建年月日多级文件夹)
	 */
	private String pathDate;
	/**
	 * @Fields pathDateFileName : TODO(根据时间创建年月日多级文件夹带文件名称)
	 */
	private String pathDateFileName;
	/**
	 * @Fields urlDateFileName : TODO(根据时间创建年月日多级文件夹带文件名称返回输出)
	 */
	private String urlDateFileName;
	/**
	 * @Fields urlDateFileName : TODO(根据时间创建年月日多级文件夹带文件名称返回输出)
	 */
	private String xeDateFileName;
	/**
	 * @Fields file : TODO(文件)
	 */
	private File file;
	/**
	 * @Fields savePath : TODO(保存路径)
	 */
	private String savePath;
	/**
	 * @Fields allowTypes : TODO(类型、后缀数组)
	 */
	private String[] allowTypes;
	/**
	 * @Fields flag : TODO(是否获得大小写)
	 */
	private boolean flag;
	/**
	 * @Fields suffix : TODO(后缀名)
	 */
	private String suffix;
	/**
	 * @Fields nullSuffix : TODO(为没有后缀的文件所添加的后缀;eg:txt)
	 */
	private String nullSuffix;
	/**
	 * @Fields width : TODO(缩略图width)
	 */
	private float width;
	/**
	 * @Fields height : TODO(缩略图height)
	 */
	private float height;
	/**
	 * @Fields thumbWhether : TODO(是否生成缩略图)
	 */
	private boolean thumbWhether;

	public boolean getThumbWhether() {
		return thumbWhether;
	}

	public void setThumbWhether(boolean thumbWhether) {
		this.thumbWhether = thumbWhether;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * TODO(原图)
	 * 
	 * @return String 返回类型
	 */
	public String getPathSource() {
		// 路径
		UploadFilePathEnum orderState = EnumsUtil.valueOf(
				UploadFilePathEnum.class, pathType.byteValue());
		pathSource = FileUpload.getUploadFilePath(orderState) + "source"
				+ FileUpload.FILE_PATH_SEP;
		return pathSource;
	}

	/**
	 * TODO(水印图)
	 * 
	 * @return String 返回类型
	 */
	public String getPathWater() {
		// 路径
		UploadFilePathEnum orderState = EnumsUtil.valueOf(
				UploadFilePathEnum.class, pathType.byteValue());
		pathWater = FileUpload.getUploadFilePath(orderState) + "water"
				+ FileUpload.FILE_PATH_SEP;
		return pathWater;
	}

	/**
	 * TODO(缩略图)
	 * 
	 * @return String 返回类型
	 */
	public String getPathThumb() {
		// 路径
		UploadFilePathEnum orderState = EnumsUtil.valueOf(
				UploadFilePathEnum.class, pathType.byteValue());
		pathThumb = FileUpload.getUploadFilePath(orderState) + "thumb"
				+ FileUpload.FILE_PATH_SEP;
		return pathThumb;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String[] getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String[] allowTypes) {
		this.allowTypes = allowTypes;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getNullSuffix() {
		return nullSuffix;
	}

	public void setNullSuffix(String nullSuffix) {
		this.nullSuffix = nullSuffix;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Integer getPathType() {
		return pathType;
	}

	public void setPathType(Integer pathType) {
		this.pathType = pathType;
	}

	public String getPathDate() {
		// 当前日期
		Date date = new Date();
		// 格式化并转换String类型
		pathDate = new SimpleDateFormat("yyyy" + FileUpload.FILE_PATH_SEP
				+ "MM" + FileUpload.FILE_PATH_SEP + "dd").format(date)
				+ FileUpload.FILE_PATH_SEP;
		return pathDate;
	}

	public String getPathDateFileName() {
		pathDateFileName = pathDate + fileName;
		return pathDateFileName;
	}

	public String getUrlDateFileName() {
		urlDateFileName = UploadFileUtils.getDoPath(pathDate + fileName);
		return urlDateFileName;
	}

	public String getXeDateFileName() {
		xeDateFileName = UploadFileUtils.getDoPath(pathSource + pathDate
				+ fileName);
		return xeDateFileName;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}
