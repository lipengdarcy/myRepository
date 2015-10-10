package com.runlion.shop.controller.home.news;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.runlion.shop.common.util.date.DateStyle;
import com.runlion.shop.common.util.date.DateUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspNews;
import com.runlion.shop.service.BspNewsService;
import com.runlion.shop.service.BspNewstypesService;

/**
 * 新闻controller
 * */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspNewsService bspNewsService;

	@Autowired
	private BspNewstypesService bspNewstypesService;

	// 新闻列表list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, ModelMap m) {
		int newstypeId = integer("newstypeid", request);
		loggerinfo.info("==========[NewsController list] newstypeid:"
				+ newstypeId);

		m.put("newstypeId", newstypeId);
		m.put("newstypesList", bspNewstypesService.queryNewstypeList());
		m.put("newsList", bspNewsService.queryNewsByType(newstypeId, 1, 20));

		return "home/news/list";
	}

	// 新闻详细detail
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam(defaultValue = "0") Integer id,
			ModelMap m) {
		loggerinfo.info("==========[NewsController detail] Start..." + id);
		BspNews entity = bspNewsService.selectByPrimaryKey(id);
		if (entity == null) {
			m.put("msg", "该信息不存在，请重新访问！");
			return "error/noSecurity";
		}
		m.put("entity", entity);
		m.put("addtime", DateUtil.DateToString(entity.getAddtime(),
				DateStyle.YYYY_MM_DD_HH_MM_SS));

		m.put("newstypesList", bspNewstypesService.queryNewstypeList());

		// HashMap<String, Object> mapinfo = new HashMap<String, Object>();
		// mapinfo.put("state", "success");
		// mapinfo.put("content", entity);
		// m.put("json", JSON.toJSONString(mapinfo));

		// m.put("totalCount", menulist.getTotalcount());
		// m.put("currentPage", menulist.getPageIndex());
		// m.put("numPerPage", menulist.getPageSize());
		loggerinfo.info("==========[NewsController detail] End..." + id);
		return "home/news/detail";
	}
}
