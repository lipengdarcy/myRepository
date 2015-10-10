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

import com.runlion.shop.common.defaultval.AdminuserDefVal;
import com.runlion.shop.common.util.EncryptUtil;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspAdminuserService;

/**
 * @Description NewsController控制器
 * @author 赵威
 * @date 2014/11/11 11:15:02
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/adminuser")
public class AdminuserController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	BspAdminuserService bspAdminuserService;

	@RequestMapping
	public String load(ModelMap m, HttpServletRequest request) throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");

		String keyWord = request.getParameter("keywords");
		String method = request.getMethod();

		if (keyWord == null) {
			keyWord = "";
		} else {
			if ("GET".equalsIgnoreCase(method)) {
				keyWord = keyWord;
			}
		}
		m.put("keywords", keyWord);

		List<BspAdminuser> userList = bspAdminuserService
				.selectByKeyWord(keyWord);
		request.setAttribute("actionlist", userList);

		loggerinfo.info("==========[NewsController load] End...");
		return "admin/adminuser/list";
	}

	@RequestMapping(params = "method=toAdd")
	public String toAdd(ModelMap m) throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		m.put("topMenu", "添加");
		m.put("getpost", "method=add");
		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminuser/add";
	}

	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml add(@ModelAttribute("bspAdminuser") BspAdminuser u)
			throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		String enPassword = EncryptUtil.encrypt(AdminuserDefVal.DEF_PASSWORD);
		u.setPassword(enPassword);
		StatusHtml statusHtml = new StatusHtml();
		if (bspAdminuserService.insertSelective(u) > 0) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page5");
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
		if (bspAdminuserService.deleteByPrimaryKey(id) > 0) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page5");

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
		BspAdminuser entity = bspAdminuserService.selectByPrimaryKey(id);
		m.put("actionInfo", entity);
		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminuser/add";
	}

	@RequestMapping(params = "method=update", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml update(@ModelAttribute("bspAdminuser") BspAdminuser u)
			throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		int i = bspAdminuserService.updateByPrimaryKeySelective(u);
		StatusHtml statusHtml = new StatusHtml();
		if (i > 0) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page5");
			statusHtml.setCallbackType("closeCurrent");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@RequestMapping(params = "method=toUpPassword")
	public String toUpPassword(@RequestParam("uid") Integer id, ModelMap m)
			throws Exception {
		loggerinfo.info("==========[NewsController toAdd] Start...");
		/*
		 * ActionInfo actionInfo = ActionBLL.GetByPKID(id); m.put("actionInfo",
		 * actionInfo);
		 */
		m.put("topMenu", "编辑");
		m.put("getpost", "method=upPassword");// actionInfo
		BspAdminuser entity = bspAdminuserService.selectByPrimaryKey(id);
		entity.setPassword("");
		m.put("actionInfo", entity);
		loggerinfo.info("==========[NewsController toAdd] End...");
		return "admin/adminuser/upPassword";
	}

	@RequestMapping(params = "method=upPassword", method = RequestMethod.POST)
	@ResponseBody
	public StatusHtml upPassword(@ModelAttribute("bspAdminuser") BspAdminuser u)
			throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");
		String password = u.getPassword();
		StatusHtml statusHtml = new StatusHtml();
		if (password != null && !password.trim().equals("")) {
			String enPassword = EncryptUtil.encrypt(password);
			u.setPassword(enPassword);
			int i = bspAdminuserService.updateByPrimaryKeySelective(u);

			if (i > 0) {
				statusHtml.setStatusCode("200");
				statusHtml.setMessage("操作成功");
				statusHtml.setNavTabId("page5");
				statusHtml.setCallbackType("closeCurrent");
			} else {
				statusHtml.setStatusCode("300");
				statusHtml.setMessage("操作错误");
			}
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("不允许空密码");
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
