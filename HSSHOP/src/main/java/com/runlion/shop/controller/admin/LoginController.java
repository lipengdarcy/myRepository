package com.runlion.shop.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.runlion.shop.common.util.EncryptUtil;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.entity.BspAdminuser;
import com.runlion.shop.service.BspAdminuserService;
import com.runlion.shop.vo.WebConfigVO;

/**
 * @Description TODO
 * @author 赵威
 * @date 2014-9-23 下午4:03:34
 * @version V1.0
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");
	@Autowired
	BspAdminuserService bspAdminuserService;

	/**
	 * 用户登录页
	 */
	@RequestMapping("/login")
	public String load(ModelMap m) {
		WebConfigVO configVo = (WebConfigVO) EHCacheUtil.get("webConfig");
		m.put("webinfor", configVo);

		return "admin/login";
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, BspAdminuser m) {

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		loggerinfo.info("==========[LoginController login] Start...");
		loggerinfo.info("登录名:" + m.getUsername() + ",登录时间:" + dateString);
		BspAdminuser bspAdminuser = bspAdminuserService
				.selectByNameAndPassword(m.getUsername(),
						EncryptUtil.encrypt(m.getPassword()));
		loggerinfo.info("==========[LoginController login] End...");
		if (bspAdminuser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("adminuser", bspAdminuser);
			return "redirect:/admin/home.do";
		} else {
			request.setAttribute("loginErr", "登录名或密码错误");
			return "admin/login";
		}

	}

	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		loggerinfo.info("==========[LoginController logout] Start...");
		HttpSession session = request.getSession();
		session.removeAttribute("adminuser");
		loggerinfo.info("==========[LoginController logout] End...");
		return "redirect:/admin/login.do";
	}
}
