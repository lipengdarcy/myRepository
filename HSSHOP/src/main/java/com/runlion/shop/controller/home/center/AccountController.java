package com.runlion.shop.controller.home.center;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.common.util.EncryptUtil;
import com.runlion.shop.common.util.Mail;
import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.common.util.WebConfigHandler;
import com.runlion.shop.common.util.ehcache.EHCacheUtil;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspUsers;
import com.runlion.shop.service.UserService;
import com.runlion.shop.service.business.BusinessService;
import com.runlion.shop.vo.MailConfigVO;

/**
 * @2015年7月9日 by linyj
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private UserService userService;
	@Autowired
	private BusinessService businessService;

	/**
	 * 找回密码页面4
	 * 
	 * @return
	 */
	@RequestMapping("/findpwd4")
	public String findpwd4(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("--------[LoginController findpwd4()]");

		return "home/login/findpwd4";
	}

	// 找回密码操作3
	@ResponseBody
	@RequestMapping(params = "method=doFindpwd3")
	public Map<String, Object> doFindpwd3(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		int uid = integer("uid", request);
		String password = str("password", request);
		String verifyCode = str("verifyCode", request);
		String sessionYzm = getSessionAttr(ACCOUNT_SESSION_YZM, request);
		loggerinfo.info("-----[AccountController doFindpwd3]---uid:" + uid
				+ "password:" + password + ", verifyCode:" + verifyCode
				+ ",sessionYzm:" + sessionYzm);
		if (StringUtils.isEmpty(verifyCode)
				|| !verifyCode.equalsIgnoreCase(sessionYzm)) {
			result.put("state", "errorYzm");
			return result;
		}
		BspUsers user = userService.getUsersById(uid);
		if (user == null) {
			result.put("state", "noUser");
			return result;
		}
		String enPwd = EncryptUtil.encrypt(password);
		userService.changePwd(uid, enPwd);
		doUserLogin(request, uid, user.getUsername(), user.getNickname());// 登录信息
		result.put("state", "success");
		return result;
	}

	/**
	 * 找回密码页面3
	 * 
	 * @return
	 */
	@RequestMapping("/findpwd3")
	public String findpwd3(HttpServletRequest request, ModelMap m) {
		String token = str("token", request);
		loggerinfo.info("--------[LoginController findpwd3()] token:" + token);
		Map<String, Object> param = userService.queryUuidParams(token);
		if (param == null)
			return null;
		String paramJsonStr = str("paramstr", param);
		Map<String, Object> jsonParam = parseJsonStr(paramJsonStr);
		String time = str("time", jsonParam);
		if (!effctTime(time))
			return null;
		int uid = integer("uid", jsonParam);
		String userName = "", userid = "";
		BspUsers user = userService.getUsersById(uid);
		if (user != null) {
			userName = user.getUsername();
			userid = user.getUid() + "";
		}
		m.put("uid", userid);
		m.put("userName", userName);

		return "home/login/findpwd3";
	}

	// 判断验证邮箱链接是否在有效时间内
	private boolean effctTime(String time) {
		if (StringUtils.isEmpty(time))
			return false;
		Date d = parseFormatTime(time);
		Date now = Calendar.getInstance().getTime();
		long dL = d.getTime();
		long nowL = now.getTime();
		long c = EMAIL_EFFECT_HOUR * 60 * 60 * 1000;
		return dL + c >= nowL;
	}

	// 找回密码操作2
	@ResponseBody
	@RequestMapping(params = "method=doFindpwd2")
	public Map<String, Object> doFindpwd2(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		int uid = integer("uid", request);
		loggerinfo.info("-----[AccountController doFindpwd2]---uid:" + uid);
		BspUsers user = userService.getUsersById(uid);
		if (user != null && user.getVerifyemail() == 1) {

			String toEmail = user.getEmail();
			Mail mail = new Mail();
			MailConfigVO configVo = new MailConfigVO();
			String path = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/classes/config/email.config");
			WebConfigHandler.getConfigInfor(configVo, path);

			mail.setHost(configVo.getHost());
			mail.setUsername(configVo.getUserName());
			mail.setPassword(configVo.getPassword());
			mail.setFrom(configVo.getFrom());

			mail.setTo(toEmail);
			mail.setSubject("红狮水泥商城密码找回邮件");

			String uuid = getUUID();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uid", uid);
			params.put("time", getFormatTime());
			String paramStr = toJsonStr(params);

			userService.addUuidParams(uuid, "AccountControler.doFindpwd2()",
					paramStr);
			String token = "token=" + uuid;
			String findpwdUrl = getBasePath(request) + "/account/findpwd3.do?"
					+ token;
			String content = "尊敬的用户：<br/>";
			content += "&nbsp;&nbsp;请点击以下链接重新设置密码<a href=\"" + findpwdUrl
					+ "\" target=\"_blank\" >" + findpwdUrl + "</a>，"
					+ EMAIL_EFFECT_HOUR + "小时后失效.<br/><br/>";
			content += "&nbsp;&nbsp;红狮水泥商城";

			System.out.println("--------------findpwdUrl:" + findpwdUrl);
			mail.setContent(content);
			try {

				boolean b = mail.toSend();
				if (b) {
					result.put("state", "success");
				} else {
					result.put("state", "error");
				}
			} catch (Exception e) {
				loggererror.error(e);
				// TODO: handle exception
			}
		}
		return result;
	}

	/**
	 * 找回密码页面2
	 * 
	 * @return
	 */
	@RequestMapping("/findpwd2")
	public String findpwd2(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("--------[LoginController findpwd2()]");
		int uid = integer("uid", request);
		String email = "", userid = "";
		BspUsers user = userService.getUsersById(uid);
		if (user != null && user.getVerifyemail() == 1) {
			email = user.getEmail();
			userid = user.getUid() + "";
		}
		m.put("email", email);
		m.put("uid", userid);

		return "home/login/findpwd2";
	}

	// 找回密码操作1
	@ResponseBody
	@RequestMapping(params = "method=doFindpwd1")
	public Map<String, Object> doFindpwd1(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		String name = str("username", request);
		String verifyCode = str("verifyCode", request);
		String sessionYzm = getSessionAttr(ACCOUNT_SESSION_YZM, request);
		loggerinfo.info("-----[AccountController doFindpwd1]---name:" + name
				+ ",verifyCode:" + verifyCode + ",sessionYzm:" + sessionYzm);
		if (StringUtils.isEmpty(verifyCode)
				|| !verifyCode.equalsIgnoreCase(sessionYzm)) {
			result.put("state", "errorYzm");
			return result;
		}
		Map<String, Object> user = userService.queryUserByLoginname(name);
		if (user == null) {
			result.put("state", "noUser");
			return result;
		}
		String verifyemail = str("email", user);
		if (StringHandler.isEmpty(verifyemail)) {
			result.put("state", "noMail");
			return result;
		}
		int uid = integer("uid", user);
		result.put("uid", uid);
		result.put("state", "success");
		return result;
	}

	/**
	 * 找回密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/findpwd")
	public String findpwd(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("--------[LoginController findpwd()]");
		return "home/login/findpwd1";
	}

	// 注册操作
	@ResponseBody
	@RequestMapping(params = "method=doRegister")
	public Map<String, Object> doRegister(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		String name = str("username", request);
		String password = str("password", request);
		String mobile = str("mobile", request);
		String verifyCode = str("verifyCode", request);
		String sessionYzm = getSessionAttr(ACCOUNT_SESSION_YZM, request);
		loggerinfo.info("-----[AccountController doRegister]---name:" + name
				+ ", password:" + password + ", mobile:" + mobile
				+ ",verifyCode:" + verifyCode + ",sessionYzm:" + sessionYzm);
		if (StringUtils.isEmpty(verifyCode)
				|| !verifyCode.equalsIgnoreCase(sessionYzm)) {
			result.put("state", "full");
			result.put("msg", "验证码错误！");
		}
		if (userService.hasSameUsername(name)) {
			result.put("state", "full");
			result.put("msg", "用户名已存在！");
		} else if (!StringHandler.isEmpty(mobile)
				&& !StringHandler.isMobile(mobile)) {
			result.put("state", "full");
			result.put("msg", "请填写正确的手机号码！");
		} else {
			String enPwd = EncryptUtil.encrypt(password);
			String ip = getIp(request);
			int uid = userService.getUid();
			userService.addUser(uid, name, enPwd, mobile);
			userService.addUserDetails(uid, ip);
			doUserLogin(request, uid, name, name);// 存放用户登录信息
			result.put("state", "success");
		}
		return result;
	}

	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest request, ModelMap m) {
		loggerinfo.info("--------[LoginController register()]");
		return "home/login/register";
	}

	// 登出操作
	@ResponseBody
	@RequestMapping(params = "method=doLogout")
	public Map<String, Object> doLogout(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		doUserLogout(request);// 注销用户登录信息

		result.put("state", "success");

		return result;
	}

	// 登录操作
	@ResponseBody
	@RequestMapping(params = "method=doLogin")
	public Map<String, Object> doLogin(HttpServletRequest request, ModelMap m) {
		Map<String, Object> result = getResult();
		String name = str("username", request);
		String password = str("password", request);
		String verifyCode = str("verifyCode", request);
		String sessionYzm = getSessionAttr(ACCOUNT_SESSION_YZM, request);
		loggerinfo.info("-----[AccountController doLogin]---name:" + name
				+ ", password:" + password + ",verifyCode:" + verifyCode
				+ ",sessionYzm:" + sessionYzm);
		if (StringUtils.isEmpty(verifyCode)
				|| !verifyCode.equalsIgnoreCase(sessionYzm)) {
			result.put("state", "errorYzm");
			return result;
		}
		String enPwd = EncryptUtil.encrypt(password);
		Map<String, Object> user = userService.authUser(name, enPwd);
		if (user == null) {
			result.put("state", "noUser");
		} else {
			// 判断用户

			int uid = integer("uid", user);
			int userType = businessService.getUserType(uid);

			EHCacheUtil.initCacheManager();
			EHCacheUtil.put("userType", userType);

			String loginName = str("username", user);
			String nickName = str("nickname", user);
			doUserLogin(request, uid, loginName, nickName, userType);// 存放用户登录信息
			result.put("state", "success");
		}
		return result;
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, ModelMap m) {
		String returnUrl = str("return_url", request);
		loggerinfo.info("--------[LoginController login()] returnUrl:"
				+ returnUrl);
		m.put("returnUrl", returnUrl);
		return "home/login/login";
	}

	public String load(ModelMap m) {
		loggerinfo.info("--------[LoginController load()]");

		return null;
	}
}
