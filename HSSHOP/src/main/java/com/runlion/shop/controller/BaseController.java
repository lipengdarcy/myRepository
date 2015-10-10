package com.runlion.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.runlion.shop.entity.BspRegions;
import com.runlion.shop.service.business.BusinessService;

/**
 * @2015年7月7日 by linyj
 */
public class BaseController {
	@Autowired
	private BusinessService businessService;

	public static final int EMAIL_EFFECT_HOUR = 1;// 验证邮箱链接的有效时间，单位：小时
	public static final String ACCOUNT_SESSION_UID = "account_session_id";// 用户登录后存放session中的uid
	public static final String ACCOUNT_SESSION_UserType = "account_session_usertype";// 用户类型
	public static final String ACCOUNT_SESSION_LOGINNAME = "account_login_name";// 用户登录后存放session中的登录帐号
	public static final String ACCOUNT_SESSION_NICKNAME = "account_nick_name";// 用户登录后存放session中的昵称
	public static final String ACCOUNT_SESSION_YZM = "account_session_yzm";// 验证码
	public static final String ACCOUNT_SESSION_ISFACUSER = "account_session_isfacuser";
	public static final String ACCOUNT_SESSION_ISSTOREUSER = "account_session_isstoreuser";

	public static final int UCENTER_PAGE_SIZE = 10;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	// json字符串转换成map对象
	public static Map<String, Object> parseJsonStr(String jsonStr) {
		if (StringUtils.isEmpty(jsonStr))
			return null;
		Object obj = JSON.parse(jsonStr);
		return obj == null ? null : (Map<String, Object>) obj;
	}

	// 参数转换成json字符串
	public static String toJsonStr(Map<String, Object> par) {
		if (par == null)
			return "";
		String jsonStr = JSON.toJSONString(par);
		return jsonStr;
	}

	// 获取UUID
	public String getUUID() {
		UUID u = UUID.randomUUID();
		return u == null ? System.currentTimeMillis() + "" : u.toString()
				.replaceAll("-", "");
	}

	public static String getFormatTime() {
		Date date = Calendar.getInstance().getTime();
		return sdf.format(date);
	}

	public static Date parseFormatTime(String time) {
		try {
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			Date d = Calendar.getInstance().getTime();
			d.setTime(0);
			return d;
		}
	}

	protected void doUserLogin(HttpServletRequest request, int uid,
			String userName, String nickName) {
		HttpSession session = request.getSession();
		session.setAttribute(ACCOUNT_SESSION_UID, uid);
		session.setAttribute(ACCOUNT_SESSION_LOGINNAME, userName);
		session.setAttribute(ACCOUNT_SESSION_NICKNAME, nickName);
	}

	protected void doUserLogin(HttpServletRequest request, int uid,
			String userName, String nickName, int userType) {
		HttpSession session = request.getSession();
		session.setAttribute(ACCOUNT_SESSION_UID, uid);
		session.setAttribute(ACCOUNT_SESSION_LOGINNAME, userName);
		session.setAttribute(ACCOUNT_SESSION_NICKNAME, nickName);
		session.setAttribute(ACCOUNT_SESSION_UserType, userType);
		// 拥有工厂角色
		if (businessService.isFactoryUser(userType)) {
			session.setAttribute(ACCOUNT_SESSION_ISFACUSER, 1);
		}
		// 拥有经销商角色
		if (businessService.isStoreUser(userType)) {
			session.setAttribute(ACCOUNT_SESSION_ISSTOREUSER, 1);
		}

	}

	protected void doUserLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute(ACCOUNT_SESSION_UID, null);
		session.setAttribute(ACCOUNT_SESSION_LOGINNAME, null);
		session.setAttribute(ACCOUNT_SESSION_NICKNAME, null);
		session.setAttribute(ACCOUNT_SESSION_ISFACUSER, null);
		session.setAttribute(ACCOUNT_SESSION_ISSTOREUSER, null);
		session.invalidate();
		session = null;
	}

	protected String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		return basePath;
	}

	protected String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	protected String getSessionAttr(String key, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null)
			return "";
		Object obj = session.getAttribute(key);
		return obj == null ? "" : obj.toString();
	}

	protected Map<String, Object> getResult() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "success");
		return map;
	}

	public static String getCid(Map<String, Object> m) {
		String s = "192224-212131-214785-0-0";
		if (m != null) {
			int layer = integer("layer", m);
			int regionid = integer("regionid", m);
			int parentid = integer("parentid", m);
			int provid = integer("provinceid", m);
			int cityid = integer("cityid", m);
			int countyid = integer("countyid", m);
			int streetid = integer("streetid", m);
			if (layer == 1) {
				s = regionid + "-0-0-0-0";
			} else if (layer == 2) {
				s = parentid + "-" + regionid + "-0-0-0";
			} else if (layer == 3) {
				s = provid + "-" + cityid + "-" + regionid + "-0-0";
			} else if (layer == 4) {
				s = provid + "-" + cityid + "-" + countyid + "-" + regionid
						+ "-0";
			} else if (layer == 5) {
				s = provid + "-" + cityid + "-" + countyid + "-" + streetid
						+ "-" + regionid;
			}
		}
		return s;
	}

	public static String getCid(BspRegions br, String regionid) {
		String s = "192224-212131-214785-0-0";
		int layer = br.getLayer();
		if (layer == 1) {
			s = regionid + "-0-0-0-0";
		} else if (layer == 2) {
			s = br.getParentid() + "-" + regionid + "-0-0-0";
		} else if (layer == 3) {
			s = br.getProvinceid() + "-" + br.getCityid() + "-" + regionid
					+ "-0-0";
		} else if (layer == 4) {
			s = br.getProvinceid() + "-" + br.getCityid() + "-"
					+ br.getCountyid() + "-" + regionid + "-0";
		} else if (layer == 5) {
			s = br.getProvinceid() + "-" + br.getCityid() + "-"
					+ br.getCountyid() + "-" + br.getStreetid() + "-"
					+ regionid;
		}
		return s;
	}

	protected static String str(String key, Map<String, Object> m) {
		if (m == null)
			return "";
		Object obj = m.get(key);
		return obj == null ? "" : obj.toString();
	}

	protected String str(String key, HttpServletRequest r) {
		String s = r.getParameter(key);
		return s == null ? "" : s;
	}

	protected static int integer(String key, Map<String, Object> m) {
		String val = str(key, m);
		int r = -1;
		if (StringUtils.isNotEmpty(val)) {
			try {
				r = Integer.parseInt(val, 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	protected int integer(String key, HttpServletRequest req) {
		String val = str(key, req);
		int r = -1;
		if (StringUtils.isNotEmpty(val)) {
			try {
				r = Integer.parseInt(val, 10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	protected static double dbl(String key, Map<String, Object> m) {
		String val = str(key, m);
		double r = -1.0;
		if (StringUtils.isNotEmpty(val)) {
			try {
				r = Double.parseDouble(val);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	protected double dbl(String key, HttpServletRequest req) {
		String val = str(key, req);
		double r = -1.0;
		if (StringUtils.isNotEmpty(val)) {
			try {
				r = Double.parseDouble(val);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return r;
	}
}
