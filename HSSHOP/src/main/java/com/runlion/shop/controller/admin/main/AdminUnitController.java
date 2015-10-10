package com.runlion.shop.controller.admin.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.entity.BspUnit;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspUnitService;

/**
 * @Description NewsController控制器
 * @author 赵威
 * @date 2014/11/11 11:15:02
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/adminunit")
public class AdminUnitController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	BspUnitService bspUnitService;

	@RequestMapping
	public String load(ModelMap m, HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		String keyWord = request.getParameter("keywords");
		if (keyWord == null) {
			keyWord = "";
		}

		m.put("keywords", keyWord);

		List<BspUnit> userList = bspUnitService.searchUnit(keyWord);
		request.setAttribute("actionlist", userList);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/adminunit/list";
	}

	@RequestMapping(params = "method=toAdd")
	public String toAdd(ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		m.put("topMenu", "添加");
		m.put("getpost", "method=add");
		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminunit/add";
	}

	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml add(@ModelAttribute("bspUnit") BspUnit u)
			throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");

		StatusHtml statusHtml = new StatusHtml();
		if (bspUnitService.insertUnit(u)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page14");
			statusHtml.setCallbackType("closeCurrent");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=delete", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml delete(@RequestParam("uid") Integer id) throws Exception {
		loggerinfo.info("==========[NewsController delete] Start...");
		StatusHtml statusHtml = new StatusHtml();
		if (bspUnitService.deleteUnit(id)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page14");

		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController delete] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toUpdate")
	public String toUpdate(@RequestParam("uid") Integer id, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		m.put("topMenu", "编辑");
		m.put("getpost", "method=update");// actionInfo
		BspUnit entity = bspUnitService.selUnitById(id);
		m.put("actionInfo", entity);
		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminunit/add";
	}

	@RequestMapping(params = "method=update", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml update(@ModelAttribute("bspUnit") BspUnit u)
			throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");

		StatusHtml statusHtml = new StatusHtml();
		if (bspUnitService.upUnit(u)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page14");
			statusHtml.setCallbackType("closeCurrent");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	// @InitBinder
	// protected void initBinder(HttpServletRequest request,
	// ServletRequestDataBinder binder) throws Exception {
	// // DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	// CustomDateEditor dateEditor = new CustomDateEditor(format, true);
	// binder.registerCustomEditor(Date.class, dateEditor);
	// super.initBinder(request, binder);
	// }
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 忽略字段绑定异常
		// binder.setIgnoreInvalidFields(true);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "insertTime",
				new CustomDateEditor(format, true));
		binder.registerCustomEditor(Date.class, "modifyTime",
				new CustomDateEditor(format, true));
	}
}
