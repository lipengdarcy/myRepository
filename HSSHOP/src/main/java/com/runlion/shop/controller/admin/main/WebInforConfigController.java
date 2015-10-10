package com.runlion.shop.controller.admin.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.util.WebConfigHandler;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.vo.UpLoadConfVO;
import com.runlion.shop.vo.WebConfigVO;

/**
 * @Description NewsController控制器
 * @author 赵威
 * @date 2014/11/11 11:15:02
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/webinfor")
public class WebInforConfigController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@RequestMapping
	public String load(ModelMap m, HttpServletRequest request) {
		loggerinfo.info("==========[NewsController load] Start...");
		loggerinfo.info("==========[NewsController load] End...");
		return "admin/webinfor/update";
	}

	@RequestMapping(params = "method=toUpdate")
	public String toUpdate(HttpServletRequest request, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		m.put("topMenu", "编辑");
		m.put("getpost", "method=update");// actionInfo

		WebConfigVO configVo = new WebConfigVO();

		String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/config/shop.config");
		// String path =
		// "E:\\Porject\\Workspaces\\HSSNSHOPADMIN\\resources\\config\\shop.config";
		WebConfigHandler.getConfigInfor(configVo, path);
		m.put("webinfor", configVo);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/webinfor/update";
	}

	@RequestMapping(params = "method=toUpdateUpload")
	public String toUpdateUpload(HttpServletRequest request, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		m.put("topMenu", "编辑");
		m.put("getpost", "method=updateUpload");// actionInfo

		UpLoadConfVO configVo = new UpLoadConfVO();
		String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/config/shop.config");
		WebConfigHandler.getConfigInfor(configVo, path);
		m.put("uploadinfor", configVo);

		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/webinfor/updateUpload";
	}

	@RequestMapping(params = "method=update", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml update(@ModelAttribute("webConfigVO ") WebConfigVO u,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");

		String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/config/shop.config");
		boolean isOk = WebConfigHandler.updateXMLByObj(path, u);

		StatusHtml statusHtml = new StatusHtml();
		if (isOk) {
			// 更新缓存
			// 初始化--必须
			EHCacheUtil.initCacheManager();
			EHCacheUtil.put("webConfig", u);
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page6");
			statusHtml.setCallbackType("refreshCurrent");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=updateUpload", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml updateUpload(
			@ModelAttribute("uploadConfigVO ") UpLoadConfVO u,
			HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");

		String path = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/classes/config/shop.config");
		boolean isOk = WebConfigHandler.updateXMLByObj(path, u);

		StatusHtml statusHtml = new StatusHtml();
		if (isOk) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page13");
			statusHtml.setCallbackType("refreshCurrent");
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
