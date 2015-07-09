package org.darcy.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie处理相关工具 第一步，登陆的时候一旦选择了[自动登录]的选项，则需要在登陆成功后，附加下面的代码
 * 
 * @author songjinsheng
 * */
public class CookieHanler {
	public final static String SESSION_LOGIN_USERNAME = "SESSION_LOGIN_USERNAME";
	public final static String SESSION_LOGIN_PASSWORD = "SESSION_LOGIN_PASSWORD";

	/**
	 * 保存cookie，实现下次自动登录
	 * */
	public static void saveCookie(HttpServletRequest request, String username,
			String password, HttpServletResponse response) {
		String host = request.getServerName();
		/*
		 * String username="test"; String password="1";
		 */
		Cookie cookie = new Cookie(SESSION_LOGIN_USERNAME, username);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);// 1天
		response.addCookie(cookie);
		cookie = new Cookie(SESSION_LOGIN_PASSWORD, password);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 24);// 1天
		response.addCookie(cookie);
	}

	/**
	 * 移除cookie，保证用户的安全
	 * */
	public static void removeCookie(HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getServerName();
		/*
		 * String username="test"; String password="1";
		 */
		Cookie cookie = new Cookie("SESSION_LOGIN_USERNAME", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);// 1天
		response.addCookie(cookie);
		cookie = new Cookie("SESSION_LOGIN_PASSWORD", null);
		cookie.setPath("/");
		cookie.setMaxAge(0);// 1天
		response.addCookie(cookie);
	}

	/**
	 * 得到cookie信息
	 * 
	 * @param Cookie
	 *            [] cks
	 * @return Map
	 * */
	public static Map<String, String> getCookieInfo(Cookie[] cks) {
		// Cookie[] cks=request.getCookies();
		Map<String, String> map = new HashMap<String, String>();
		if (cks != null) {
			for (Cookie c : cks) {
				if ("SESSION_LOGIN_USERNAME".equals(c.getName())) {
					map.put("SESSION_LOGIN_USERNAME", c.getValue());
				}
				if ("SESSION_LOGIN_PASSWORD".equals(c.getName())) {
					map.put("SESSION_LOGIN_PASSWORD", c.getValue());
				}
			}

		}
		return map;
	}

	public static String getLastAreaFromCookie(HttpServletRequest request) {
		String cookieName = "lastarea";
		String deFaultValue = "192224";// 浙江省
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName()))
				return cookie.getValue();
		}
		return deFaultValue;

	}

}
