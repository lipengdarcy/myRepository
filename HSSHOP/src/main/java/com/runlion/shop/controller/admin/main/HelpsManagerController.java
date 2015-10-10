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
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.controller.BaseController;
import com.runlion.shop.dao.BspHelpsTypesMapper;
import com.runlion.shop.service.BspHelpsService;

@Controller
@RequestMapping("/admin/helps")
public class HelpsManagerController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspHelpsService bspHelpsService;

	@Autowired
	private BspHelpsTypesMapper bspHelpsTypesMapper;

	// 帮助修改保存
	@ResponseBody
	@RequestMapping(params = "method=helpsEdit")
	public Map<String, Object> newsEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		int pid = integer("pid", request);
		int displayorder = integer("displayorder", request);
		String title = str("title", request);
		String url = str("url", request);
		String description = str("description", request);

		loggerinfo.info("==[NewsManagerController newsEdit]---newsid:" + id);
		bspHelpsService.modifyHelpsById(id, pid, displayorder, title, url,
				description);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	// 帮助修改
	@RequestMapping(params = "method=toHelpsEdit")
	public String toNewsEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("===[NewsManagerController toNewsEdit] -- id:" + id);
		m.addAttribute("type", bspHelpsTypesMapper.selecthelpstype());
		m.put("helps", bspHelpsService.queryHelpsById(id));
		return "admin/news/helps-edit";
	}

	// 帮助删除
	@ResponseBody
	@RequestMapping(params = "method=helpsDelete")
	public Map<String, Object> newsDelete(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("==[NewsManagerController newsDelete]---id:" + id);
		bspHelpsService.removeHelpsById(id);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	// 帮助类型管理新增操作
	@ResponseBody
	@RequestMapping(params = "method=helpsAdd")
	public Map<String, Object> newsAdd(HttpServletRequest request, ModelMap m) {
		int pid = integer("pid", request);
		int displayorder = integer("displayorder", request);
		String title = str("title", request);
		String url = str("url", request);
		String description = str("description", request);

		loggerinfo.info("==[NewsManagerController newsAdd]---title:" + title);
		bspHelpsService.addHelps(pid, displayorder, title, url, description);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	@RequestMapping(params = "method=toHelpsAdd")
	public String toNewsAdd(ModelMap m) {
		m.addAttribute("type", bspHelpsTypesMapper.selecthelpstype());
		return "admin/news/helps-add";
	}

	// 帮助列表
	@RequestMapping(value = "/helpslist")
	public String news(HttpServletRequest request, ModelMap m) {
		int newstypeId = integer("helpstype_id", request);
		String title = request.getParameter("title");
		loggerinfo.info("==========[NewsManagerController news]...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		List<Map<String, Object>> dataList = bspHelpsService.queryHelpsLimit(
				newstypeId, title, startNum, pageSize);
		int cnt = bspHelpsService.countNews(newstypeId, title);
		m.addAttribute("type", bspHelpsTypesMapper.selecthelpstype());
		m.put("status", "success");
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("newstypeId", newstypeId);
		return "admin/news/helps-list";
	}

	// 帮助类型列表
	@RequestMapping(value = "/helpstypelist")
	public String helpstypelist(HttpServletRequest request, ModelMap m) {
		int newstypeId = integer("helpstype_id", request);
		String title = request.getParameter("title");
		loggerinfo.info("==========[NewsManagerController news]...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;
		List<Map<String, Object>> dataList = bspHelpsTypesMapper
				.selecthelpstype();
		int cnt = bspHelpsTypesMapper.count();
		m.put("status", "success");
		m.put("dataList", dataList);
		m.put("totalCount", cnt);
		m.put("currentPage", pageNo);
		m.put("numPerPage", pageSize);
		m.put("newstypeId", newstypeId);
		return "admin/news/helpstype-list";
	}

	// 帮助删除
	@ResponseBody
	@RequestMapping(params = "method=helpstypeDelete")
	public Map<String, Object> helpstypeDelete(HttpServletRequest request,
			ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("==[NewsManagerController newsDelete]---id:" + id);
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		bspHelpsTypesMapper.delete(par);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	// 帮助修改
	@RequestMapping(params = "method=toHelpstypeEdit")
	public String toHelpstypeEdit(HttpServletRequest request, ModelMap m) {
		int id = integer("id", request);
		loggerinfo.info("===[NewsManagerController toNewsEdit] -- id:" + id);
		m.put("type", bspHelpsTypesMapper.selectbyid(id));
		return "admin/news/helpstype-edit";
	}

	// 帮助修改保存
	@ResponseBody
	@RequestMapping(params = "method=helpstypeEdit")
	public Map<String, Object> helpstypeEdit(HttpServletRequest request,
			ModelMap m) {
		int id = integer("id", request);
		int displayorder = integer("displayorder", request);
		String name = str("name", request);

		loggerinfo.info("==[NewsManagerController newsEdit]---newsid:" + id);
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("helpstypeid", id);
		par.put("displayorder", displayorder);
		par.put("name", name);
		bspHelpsTypesMapper.update(par);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}

	@RequestMapping(params = "method=toHelpstypeAdd")
	public String toHelpstypeAdd(ModelMap m) {
		return "admin/news/helpstype-add";
	}

	// 帮助类型管理新增操作
	@ResponseBody
	@RequestMapping(params = "method=helpstypeAdd")
	public Map<String, Object> helpstypeAdd(HttpServletRequest request,
			ModelMap m) {
		int displayorder = integer("displayorder", request);
		String name = str("name", request);

		loggerinfo.info("==[NewsManagerController newsAdd]---title:" + name);
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("name", name);
		par.put("displayorder", displayorder);
		bspHelpsTypesMapper.insert(par);
		Map<String, Object> result = getResult();
		result.put("state", "success");
		return result;
	}
}
