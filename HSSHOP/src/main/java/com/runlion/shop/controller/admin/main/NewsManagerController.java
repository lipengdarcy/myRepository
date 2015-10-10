package com.runlion.shop.controller.admin.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.runlion.shop.common.util.date.DateStyle;
import com.runlion.shop.common.util.date.DateUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspNews;
import com.runlion.shop.entity.BspNewstypes;
import com.runlion.shop.service.BspNewsService;
import com.runlion.shop.service.BspNewstypesService;

/**
 * 新闻controller
 * */
@Controller
@RequestMapping("/admin/newsMng")
public class NewsManagerController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspNewsService bspNewsService;

	@Autowired
	private BspNewstypesService bspNewstypesService;

	// 新闻类型管理新增操作
	@ResponseBody
	@RequestMapping(params = "method=newsEdit")
	public Map<String, Object> newsEdit(HttpServletRequest request, ModelMap m) {
		int newsid = integer("news_id", request);
		int newstypeId = integer("newstype_id", request);
		int isShow = integer("is_show", request);
		int isTop = integer("is_top", request);
		int isHome = integer("is_home", request);
		int displayorder = integer("displayorder", request);
		String title = str("title", request);
		String body = str("body", request);

		loggerinfo
				.info("==[NewsManagerController newsEdit]---newsid:" + newsid);
		bspNewsService.modifyNewsById(newsid, newstypeId, isShow, isTop,
				isHome, displayorder, title, body);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	@RequestMapping(params = "method=toNewsEdit")
	public String toNewsEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("===[NewsManagerController toNewsEdit] -- id:" + id);
		m.put("newstypeList", bspNewstypesService.queryNewstypeList());
		m.put("news", bspNewsService.queryNewsById(id));
		return "admin/news/news-edit";
	}

	// 新闻类型管理新增操作
	@ResponseBody
	@RequestMapping(params = "method=newsDelete")
	public Map<String, Object> newsDelete(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("==[NewsManagerController newsDelete]---id:" + id);
		bspNewsService.removeNewsById(id);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	// 新闻类型管理新增操作
	@ResponseBody
	@RequestMapping(params = "method=newsAdd")
	public Map<String, Object> newsAdd(HttpServletRequest request, ModelMap m) {
		int newstypeId = integer("newstype_id", request);
		int isShow = integer("is_show", request);
		int isTop = integer("is_top", request);
		int isHome = integer("is_home", request);
		int displayorder = integer("displayorder", request);
		String title = str("title", request);
		String url = "";
		String body = str("body", request);

		loggerinfo.info("==[NewsManagerController newsAdd]---title:" + title);
		bspNewsService.addNews(newstypeId, isShow, isTop, isHome, displayorder,
				title, url, body);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	@RequestMapping(params = "method=toNewsAdd")
	public String toNewsAdd(ModelMap m) {
		loggerinfo.info("==========[NewsManagerController toNewsAdd]");
		m.put("newstypeList", bspNewstypesService.queryNewstypeList());

		return "admin/news/news-add";
	}

	// 新闻列表
	@RequestMapping(value = "/news")
	public String news(HttpServletRequest request, ModelMap m) {
		int newstypeId = integer("newstype_id", request);
		loggerinfo.info("==========[NewsManagerController news]...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		List<Map<String, Object>> dataList = bspNewsService.queryNewsLimit(
				newstypeId, startNum, pageSize);
		int cnt = bspNewsService.countNews(newstypeId);
		m.put("status", "success");
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("newstypeId", newstypeId);
		return "admin/news/news-list";
	}

	// 新闻类型管理修改操作
	@ResponseBody
	@RequestMapping(params = "method=newstypeDelete")
	public Map<String, Object> newstypeDelete(HttpServletRequest request,
			ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("==[NewsManagerController newstypeDelete]---id:" + id);
		bspNewstypesService.removeNewstypeById(id);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	// 新闻类型管理修改操作
	@ResponseBody
	@RequestMapping(params = "method=newstypeEdit")
	public Map<String, Object> newstypeEdit(HttpServletRequest request,
			ModelMap m) {
		int id = integer("id", request);
		String name = str("name", request);
		int order = integer("order", request);
		loggerinfo.info("==[NewsManagerController newstypeEdit]---id:" + id
				+ ",name:" + name + ",order:" + order);
		bspNewstypesService.modifyNewstypeById(id, name, order);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	@RequestMapping(params = "method=toNewstypeEdit")
	public String toNewstypeEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("==========[NewsManagerController toNewstypeEdit] id:"
				+ id);
		Map<String, Object> newstype = bspNewstypesService
				.queryNewstypeById(id);
		m.put("s", newstype);
		return "admin/news/newstype-edit";
	}

	// 新闻类型管理新增操作
	@ResponseBody
	@RequestMapping(params = "method=newstypeAdd")
	public Map<String, Object> newstypeAdd(HttpServletRequest request,
			ModelMap m) {
		String name = str("name", request);
		int order = integer("order", request);
		loggerinfo.info("==[NewsManagerController newstypeAdd]---name:" + name
				+ ",order:" + order);
		bspNewstypesService.addNewstype(name, order);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	@RequestMapping(params = "method=toNewstypeAdd")
	public String toNewstypeAdd(ModelMap m) {
		loggerinfo.info("==========[NewsManagerController toNewstypeAdd]");
		return "admin/news/newstype-add";
	}

	// 新闻类型列表
	@RequestMapping(value = "/newstype")
	public String newstype(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("==========[NewsManagerController newstype]...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		List<Map<String, Object>> dataList = bspNewstypesService
				.queryNewstypeLimit(startNum, pageSize);
		int cnt = bspNewstypesService.countNewstype();
		m.put("status", "success");
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		return "admin/news/newstype-list";
	}

	@RequestMapping
	public String load(ModelMap m) {
		loggerinfo.info("==========[NewsManagerController load] Start...");

		m.put("newstypeList", bspNewstypesService.queryNewstypeList());
		loggerinfo.info("==========[NewsManagerController load] End...");
		return "admin/newstype/list";
	}

	@ResponseBody
	@RequestMapping(params = "method=queryNewstypeList", method = RequestMethod.POST)
	public List<BspNewstypes> queryNewstypeList() {
		loggerinfo
				.info("==========[NewstypeController queryNewstypeList] Start...");

		List<BspNewstypes> list = bspNewstypesService.queryNewstypeList();
		loggerinfo
				.info("==========[NewstypeController queryNewstypeList] End...");
		return list;
	}

	// 新闻列表list
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap m) {
		loggerinfo.info("==========[NewsController load] Start...");

		// 分类
		BspNewstypes newstypesentity = bspNewstypesService
				.selectByPrimaryKey(0);
		m.put("newstypeslist", newstypesentity);

		loggerinfo.info("==========[NewsController load] End...");
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

		HashMap<String, Object> mapinfo = new HashMap<String, Object>();
		mapinfo.put("state", "success");
		mapinfo.put("content", entity);
		m.put("json", JSON.toJSONString(mapinfo));

		// m.put("totalCount", menulist.getTotalcount());
		// m.put("currentPage", menulist.getPageIndex());
		// m.put("numPerPage", menulist.getPageSize());
		loggerinfo.info("==========[NewsController detail] End..." + id);
		return "home/news/detail";
	}
}
