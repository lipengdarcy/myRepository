package com.runlion.shop.controller.admin.main;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspNcenterinfor;
import com.runlion.shop.service.BspNcenterinforService;

@Controller
@RequestMapping("/admin/ncpro")
public class NCProInforController extends BaseController {
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspNcenterinforService bspNcenterinforService;

	// 获取允销目录的信息列表
	@RequestMapping("/ncprolookbacklist")
	public String resourcelist(ModelMap m, String keywords, Integer pageNum,
			Integer numPerPage) {
		if (keywords == null) {
			keywords = "";
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		if (numPerPage == null) {
			numPerPage = 20;
		}
		List<BspNcenterinfor> list = bspNcenterinforService.getBspNcProList(
				keywords, pageNum, numPerPage);
		Integer totalCount = bspNcenterinforService.countBspNcProList(keywords);
		m.put("list", list);
		m.put("keywords", keywords);
		m.put("pageNum", pageNum);
		m.put("numPerPage", numPerPage);
		m.put("totalCount", totalCount);
		return "admin/adminproduct/ncProLookbackList";
	}

}
