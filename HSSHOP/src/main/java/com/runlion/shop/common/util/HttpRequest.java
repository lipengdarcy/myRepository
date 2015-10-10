package com.runlion.shop.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.runlion.shop.common.util.date.DateStyle;
import com.runlion.shop.common.util.date.DateUtil;
import com.runlion.shop.entity.MessageEntity;

public class HttpRequest {
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		// 发送 GET 请求
		// String s = HttpRequest.sendGet(
		// "http://localhost:6144/Home/RequestString", "key=123&v=456");
		// System.out.println(s);

		// 发送 POST 请求

		MessageEntity msg = new MessageEntity();
		msg.setFrom("A02");
		msg.setTo("13738081964");
		msg.setTime(DateUtil.DateToString(new Date(),
				DateStyle.YYYY_MM_DD_HH_MM));
		msg.setFlag("1");
		msg.setSuccess("-1");
		msg.setMsgType("SMS");
		msg.setSendType("Text");
		msg.setBody("集团MES数据同步服务异常情况报告");
		msg.setSign("E11F340C980A425E98C089F0E45CF9E8");

		// String reture = sendMsm(msg);
		// System.out.println(reture);

		System.out.println(sendMsm("13738081964", "ddd"));
		// FC10EAD4DDD1A05230FD455397513BA1
	}

	public static String sendMsm(MessageEntity e) throws Exception {

		// form数据
		String strmsg = "From={0}&MsgId={1}&DeptId={2}&EmpId={3}&To={4}&Time={5}&Flag={6}&Success={7}&Cardid={8}&MsgType={9}&SendType={10}&Subject={11}&Body={12}";
		strmsg = StringHandler.formateString(strmsg, e.getFrom(), e.getMsgId(),
				e.getDeptId(), e.getEmpId(), e.getTo(), e.getTime(),
				e.getFlag(), e.getSuccess(), e.getCardid(), e.getMsgType(),
				e.getSendType(), e.getSubject(), e.getBody(), e.getSign());

		// testkey
		String sign = strmsg + "&Sign={0}";
		sign = StringHandler.formateString(sign, e.getSign());

		// md5加密
		String v = strmsg + "&vcode=" + getMD5Digest(sign.getBytes("UTF-8"));
		System.out.println(v);

		// 发送 POST 请求
		String sr = HttpRequest.sendPost("http://sms.runlion.com/PostSms.ashx",
				v);
		// System.out.println(sr);
		return sr;
	}

	/**
	 * TODO(短信发送方法)
	 * 
	 * 1：相同手机号，相同内容，10分钟内不能超过3条 2：相同手机号，不同内容内，90秒不可重复发送
	 * 
	 * @param telNum
	 * @param msgText
	 * @return
	 * @throws Exception
	 *             String 返回类型
	 */
	public static String sendMsm(String telNum, String msgText)
			throws Exception {
		// 发送 POST 请求
		MessageEntity msg = new MessageEntity();
		msg.setFrom("A02");
		msg.setTo(telNum);
		msg.setTime(DateUtil.DateToString(new Date(),
				DateStyle.YYYY_MM_DD_HH_MM));
		msg.setFlag("1");
		msg.setSuccess("-1");
		msg.setMsgType("SMS");
		msg.setSendType("Text");
		msg.setBody(msgText);
		msg.setSign("E11F340C980A425E98C089F0E45CF9E8");
		//
		return sendMsm(msg);
	}

	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };// 用来将字节转换成16进制表示的字符
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.update(source);
			byte tmp[] = md.digest(source);// MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16
											// 个字节
			char str[] = new char[16 * 2];// 每个字节用 16 进制表示的话，使用两个字符， 所以表示成 16
											// 进制需要 32 个字符
			int k = 0;// 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) {// 从第一个字节开始，把MD5 的每一个字节转换成2个 16 进制字符
				byte byte0 = tmp[i];// 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取字节中高 4
														// 位的数字转换,>>>为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf];// 取字节中低 4 位的数字转换
			}
			s = new String(str);// 换后的结果转换为字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return s;
	}

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

	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
