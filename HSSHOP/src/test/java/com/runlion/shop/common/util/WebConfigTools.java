package com.runlion.shop.common.util;

import org.junit.Test;

import com.runlion.shop.vo.WebConfigVO;

public class WebConfigTools {

	@Test
	public void test() {
		WebConfigVO configVo = new WebConfigVO();
		// String path = Thread.currentThread().getContextClassLoader()
		// .getResource("resources").getPath();
		String path = "E:\\Project\\Workspaces\\HSSNSHOPADMIN\\resources\\config\\shop.config";
		WebConfigHandler.getConfigInfor(configVo, path);

		// WebConfigHandler.updateXML(path, "ShopName", "红狮水泥商城");// Script
		// WebConfigHandler.updateXML(path, "Script", "红狮水泥商城");

		configVo.setScript("new xcript");
		configVo.setSEOKeyword("红狮水泥商城 seo");

		WebConfigHandler.updateXMLByObj(path, configVo);

		System.out.println(path);
	}
}
