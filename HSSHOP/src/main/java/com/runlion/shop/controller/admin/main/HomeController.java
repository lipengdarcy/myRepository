package com.runlion.shop.controller.admin.main;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页controller
 * */
@Controller
@RequestMapping("/admin/home")
public class HomeController {

	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@RequestMapping
	public String load(ModelMap m) {
		loggerinfo.info("==========[HomeController load] Start...");
		// DataConnectionSql<MenuInfo> menulist = getList(request);
		// m.put("menulist", menulist.getList());
		// m.put("totalCount", menulist.getTotalcount());
		// m.put("currentPage", menulist.getPageIndex());
		// m.put("numPerPage", menulist.getPageSize());
		loggerinfo.info("==========[HomeController load] End...");
		return "admin/index";
	}
}
