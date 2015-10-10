package com.runlion.shop.common.util.uploadfile;

import com.runlion.shop.common.util.IEnum;

/**
 * Filename: FileUploadPathEnum.java <br>
 * Title: 文件上传枚举<br>
 * De.ion: TODO(路径)<br>
 * Copyright: Copyright (c) runlion.com,Inc.All Rights Reserved. <br>
 * Company: runlion<br>
 * Author: <a href="mailto:716517@qq.com">zhaowei</a> <br>
 * Computer: zhaowei <br>
 * Date: 2015年9月10日 <br>
 * Time: 上午9:36:20 <br>
 * Version: 1.0.0 <br>
 */
public enum UploadFilePathEnum implements IEnum {
	// 路径说明，考虑避免可能会有/ \路径在不同系统环境下出问题，暂时统一以根路径为准
	/**
	 * @Fields Common : TODO(默认路径 common)
	 */
	Common(0, "common"),
	/**
	 * @Fields Editor : TODO(编辑器editor)
	 */
	Editor(1, "editor"),
	/**
	 * @Fields Banner : TODO(banner)
	 */
	Banner(2, "banner"),
	/**
	 * @Fields Advert : TODO(广告advert)
	 */
	Advert(3, "advert"),
	/**
	 * @Fields News : TODO(新闻news)
	 */
	News(4, "news"),
	/**
	 * @Fields Product : TODO(产品product)
	 */
	Product(5, "product"),
	/**
	 * @Fields User : TODO(用户user)
	 */
	User(6, "user"),
	/**
	 * @Fields Admin : TODO(后台admin)
	 */
	Admin(7, "admin"),
	/**
	 * @Fields Order : TODO(订单order)
	 */
	Order(8, "order"),
	/**
	 * @Fields Html : TODO(html)
	 */
	Html(9, "html");

	/** 编码 */
	private int code;

	/** 路径 */
	private String desc;

	UploadFilePathEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return desc;
	}

}
