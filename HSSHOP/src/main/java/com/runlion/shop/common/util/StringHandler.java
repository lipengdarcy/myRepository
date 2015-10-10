package com.runlion.shop.common.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.runlion.shop.entity.zwzcd.PayKeyLoginEntity;

public class StringHandler {

	/**
	 * 将传入的字符串首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upFirstChar(String str) {
		if (str != null && str.length() > 0) {
			String name = str.substring(0, 1).toUpperCase() + str.substring(1);
			return name;
		} else {
			return str;
		}

	}

	// / <summary>
	// / 获得指定顺序的字符在字符串中的位置索引
	// / </summary>
	// / <param name="s">字符串</param>
	// / <param name="order">顺序</param>
	// / <returns></returns>
	public static int IndexOf(String s, int order) {
		return IndexOf(s, '-', order);
	}

	// / <summary>
	// / 获得指定顺序的字符在字符串中的位置索引
	// / </summary>
	// / <param name="s">字符串</param>
	// / <param name="c">字符</param>
	// / <param name="order">顺序</param>
	// / <returns></returns>
	public static int IndexOf(String s, char c, int order) {
		int length = s.length();
		for (int i = 0; i < length; i++) {
			if (c == s.charAt(i)) {
				if (order == 1)
					return i;
				order--;
			}
		}
		return -1;
	}

	/**
	 * 判断输入的字符串参数是否为空
	 * 
	 * @return boolean 空则返回true,非空则flase
	 */
	public static boolean isEmpty(String input) {
		if (null == input || 0 == input.length()
				|| 0 == input.replaceAll("\\s", "").length()) {
			return true;
		} else {
			return false;
		}
	}

	// / <summary>
	// / 移除前导字符串
	// / </summary>
	// / <param name="sourceStr">源字符串</param>
	// / <param name="trimStr">移除字符串</param>
	// / <returns></returns>
	public static String TrimStart(String sourceStr, String trimStr) {

		// sourceStr = "0-186-0-0-0";
		// trimStr = "0-";
		if (isEmpty(sourceStr))
			return "";

		// 186-0-0-0
		return sourceStr.substring(trimStr.length(), sourceStr.length());
	}

	// / <summary>
	// / 移除后导字符串
	// / </summary>
	// / <param name="sourceStr">源字符串</param>
	// / <param name="trimStr">移除字符串</param>
	// / <returns></returns>
	public static String TrimEnd(String sourceStr, String trimStr) {

		// sourceStr = "186-0-0-0";
		// trimStr = "-0-0-0";
		if (isEmpty(sourceStr))
			return "";

		// 186
		return sourceStr.substring(0, sourceStr.length() - trimStr.length());
	}

	/**
	 * 格式化字符串
	 * 
	 * 例：formateString("xxx{0}bbb",1) = xxx1bbb
	 * 
	 * @param str
	 * @param params
	 * @return
	 */
	public static String formateString(String str, String... params) {
		for (int i = 0; i < params.length; i++) {
			str = str
					.replace("{" + i + "}", params[i] == null ? "" : params[i]);
		}
		return str;
	}

	// updateByIndex("0-0-0", 2, 123) result: 0-0-123
	public static String updateByIndex(String str, int index, int value) {
		StringBuilder result = new StringBuilder();
		String[] list = str.split("-");
		for (int i = 0; i < list.length; i++) {
			if (index == i)
				result.append(value + "-");
			else
				result.append(list[i] + "-");
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	// 生成分页标签
	public static String generatePageDiv(long total, int pageSize,
			int pageNumber, String path) {
		PageHTMLHelper p = new PageHTMLHelper();
		p.setTotal(total);
		p.setPageSize(pageSize);
		p.setIndex(pageNumber);
		p.setPath(path);
		return p.getPageHTML();
	}

	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return [0-9]{5,9}
	 */
	public static boolean isMobile(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(17[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 电话号码验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}

	public static boolean isNum(String number) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^[0-9]{5}$");
			Matcher m = p.matcher(number);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 用于将一个参数值对象转化成属性名=属性值的键值对并用&连接 返回 的结果形式如a=1&b=2
	 * 
	 * @param obj
	 * @param extPronames
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static String pramVoToKeyval(Object obj, List<String> extPronames)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String keyVal = "";
		Class objClass = obj.getClass();
		Field[] fs = objClass.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field field = fs[i];
			String fname = field.getName();
			if (!extPronames.contains(fname)) {
				String metname = "get" + StringHandler.upFirstChar(fname);
				Method m = objClass.getMethod(metname);
				Object rs = m.invoke(obj);
				if (rs != null) {
					if (!keyVal.equals("")) {
						keyVal += "&";
					}
					keyVal += fname + "=" + rs + "";
				}
			}
		}
		return keyVal;
	}

	/**
	 * TODO(md5加密)
	 * 
	 * @param buffer
	 * @return String 返回类型
	 */
	private static String getMD5Digest(byte[] buffer) {
		String resultHash = null;
		try {

			MessageDigest md5 = MessageDigest.getInstance("MD5");

			byte[] result = new byte[md5.getDigestLength()];

			md5.reset();
			md5.update(buffer);
			result = md5.digest();

			StringBuffer buf = new StringBuffer(result.length * 2);

			byte[] result1 = new byte[result.length];
			for (int i = 0; i < result.length; i++) {

				int intVal = result[i] & 0xff;
				if (intVal < 0x10) {
					buf.append("0");
				}

				buf.append(Integer.toHexString(intVal));
			}

			resultHash = buf.toString();
		} catch (NoSuchAlgorithmException e) {
		}
		return resultHash.toUpperCase();
	}

	/**
	 * TODO(登录第三方支付)
	 * 
	 * @param cid
	 * @param uid
	 * @return String 返回类型
	 * @throws UnsupportedEncodingException
	 */
	public static String paykeylogin(PayKeyLoginEntity payKeyLogin)
			throws UnsupportedEncodingException {
		Gson gson = new Gson();

		// 最新时间
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		payKeyLogin.setTime(df.format(date));

		// key密钥 来自电商发送的key，可以是由第三方支付提供的key
		String key = "EFWEED323FEW523FEWFE";
		payKeyLogin.setSign(key);

		// md5加密
		payKeyLogin.setVcode(getMD5Digest(gson.toJson(payKeyLogin).getBytes(
				"UTF-8")));

		// 输出不需要带入key
		payKeyLogin.setSign(null);

		try {
			return URLEncoder.encode(gson.toJson(payKeyLogin), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {

		PayKeyLoginEntity payKeyLogin = new PayKeyLoginEntity();
		payKeyLogin.setCid("d");
		payKeyLogin.setUid("2");
		System.out.println(paykeylogin(payKeyLogin));
		System.out
				.println(URLDecoder.decode(paykeylogin(payKeyLogin), "UTF-8"));

		System.out.println("sss");

		// 手机号码验证
		System.out.println(StringHandler.isMobile("13038081964") + "---");
		// 短信验证
		System.out.println(StringHandler.isEmail("fewfw@qq.com") + "---");
	}
}
