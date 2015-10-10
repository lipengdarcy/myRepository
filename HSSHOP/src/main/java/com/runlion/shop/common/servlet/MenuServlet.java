package com.runlion.shop.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
/**
 * 启动时加载菜单链接信息，并加入ServletContext
 */
public class MenuServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

		/*
		 * List<SysConfig> sys = BaseManagerFactory.getSysConfigDAO().findAll();
		 * for (SysConfig sysConfig : sys) {
		 * this.getServletContext().setAttribute(sysConfig.getKey(),
		 * sysConfig.getValue()); }
		 */
	}

}