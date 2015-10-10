package com.runlion.shop.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * Filename: JspToHtml.java <br>
 * Ttitle: jsp转换成html<br>
 * De.ion: 把动态网页转换成静态网页<br>
 * Copyright: Copyright (c) ,Inc.All Rights Reserved. <br>
 * Company: sd<br>
 * Author: <a href="mailto:d">ss</a> <br>
 * Date: 2014-6-19 <br>
 * Time: 16:41:09 <br>
 * Version: 2.0.0 <br>
 */
public class JspToHtml {

	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	private String urlStr;
	private String savePath;

	public JspToHtml(String urlStr, String savePath) {
		super();
		this.urlStr = urlStr;
		this.savePath = savePath;
	}

	private static String title = "标题测试";
	private static String context = "标题测试";
	private static String editer = "标题测试";

	/**
	 * 根据本地模板生成静态页面
	 * 
	 * @param JspFile
	 *            jsp路经
	 * @param HtmlFile
	 *            html路经
	 * @return
	 */
	public static boolean JspToHtmlFile(String filePath, String HtmlFile) {
		String str = "";
		long beginDate = (new Date()).getTime();
		try {
			String tempStr = "";
			FileInputStream is = new FileInputStream(filePath);// 读取模块文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((tempStr = br.readLine()) != null)
				str = str + tempStr;
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {

			str = str.replaceAll("###title###", title);
			str = str.replaceAll("###content###", context);
			str = str.replaceAll("###author###", editer);// 替换掉模块中相应的地方

			File f = new File(HtmlFile);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			if (!f.exists()) {
				f.createNewFile();
			}
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
			System.out.println("共用时：" + ((new Date()).getTime() - beginDate)
					+ "ms");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 根据url生成静态页面
	 *
	 * @param urlStr
	 *            动态文件路经 如：http://www.163.com/x.jsp
	 * 
	 * @param path
	 *            文件存放路经如：x:\\abc\bbb.html
	 * @return
	 */
	public static boolean JspToHtmlByURL(String urlStr, String path, int pid,
			String realPath, String cookie) {
		// 从utl中读取html存为str
		String str = "";
		try {
			str = sendGet(urlStr, cookie);

			String url = "<label id=\"contentItems\"><a href="
					+ urlStr
					+ "><span style=\"font-size:20px\">查看最新物品信息</span></a></label>";
			String start = "<div id=\"buy\">";
			String end = "加收藏</b>";
			int startIndex = str.indexOf(start);
			int endIndex = str.indexOf(end);

			String contentToReplace = str.substring(startIndex, endIndex);
			contentToReplace = contentToReplace + end;
			str = str.replace(contentToReplace, url);
			// System.out.println(str);
			String javascript = "</body><script  type=\"text/javascript\">"
					+ "$(document).ready(function()"
					+ "{function countProduct(pid){"
					+ "$.ajax({url:'"
					+ realPath
					+ "/order/countProduct.do',"
					+ "data:{pid : pid,},type:'post',dataType:'json',"
					+ "success:function(data) "
					+ "{if(data == \"no\"){document.getElementById(\"contentItems\").innerHTML = \"没有该物品最新信息\";}},"
					+ "	error : function() {alert(\"异常！请重新尝试或者联系管理员！\");}});}"
					+ "countProduct(" + pid + ");});</script>";
			start = "</body>";
			end = "</html>";
			startIndex = str.indexOf(start);
			endIndex = str.indexOf(end);

			contentToReplace = str.substring(startIndex, endIndex);
			str = str.replace(contentToReplace, javascript);
			// System.out.println(str);
			// 写入文件
			File f = new File(path);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			if (!f.exists()) {
				f.createNewFile();
				FileOutputStream fos = new FileOutputStream(f);
				OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
				osw.write(str);
				osw.flush();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String cookie) {
		String result = "";
		BufferedReader in = null;
		try {
			// 打开和URL之间的连接
			HttpURLConnection conn = getHttpURLConnection(url, cookie);
			// 获取所有响应头字段
			Map<String, List<String>> map = conn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line + "\n";
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
	 * TODO(设置请求属性)
	 * 
	 * @param urlStr
	 * @param cookie
	 * @return HttpURLConnection 返回类型
	 */
	private static HttpURLConnection getHttpURLConnection(String urlStr,
			String cookie) {
		HttpURLConnection conn = null;
		// String cookie =
		// "JSESSIONID=A57F6493C5D456A0B6487A3AD31B6F1F; allarea=192224-212131-214785-214786-214787-0; lastarea=214787; a2404_pages=5; a2404_times=1";
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("contentType", "utf-8");
			// conn.setRequestProperty("Accept-Charset",
			// "GBK,utf-8;q=0.7,*;q=0.3");
			conn.setRequestProperty("Accept-Charset", "utf-8;q=0.7,*;q=0.3");
			// conn.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
			conn.setRequestProperty("Accept-Encoding", "deflate");
			conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("Cookie", cookie);
			// conn.setRequestProperty("Host", "192.168.158.101:8090");
			conn.setRequestProperty("Referer", urlStr);
			conn.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.43 Safari/537.31");
			conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

			// 建立实际的连接
			conn.connect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * TODO(xpath)
	 * 
	 * @param url
	 * @param xpathExp
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws XPathExpressionException
	 *             void 返回类型
	 */
	public static void crawlByXPath(String url, String xpathExp)
			throws IOException, ParserConfigurationException, SAXException,
			XPathExpressionException {

		String str = "";
		str = sendGet(url, "");

		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();

		XPathExpression expression = xPath.compile(xpathExp);
		expression.evaluate(str);

	}

	/**
	 * TODO(regex)
	 * 
	 * @param url
	 * @param regex
	 * @throws IOException
	 *             void 返回类型
	 */
	public static String crawlByRegex(String url, String regex, String totxt)
			throws IOException {

		String str = "";
		str = sendGet(url, "");

		str = str.replaceAll(regex, totxt);
		return str;
	}

	/**
	 * 测试main 函数
	 *
	 * @param arg
	 * @throws IOException
	 */
	public static void main(String[] arg) throws IOException {
		long begin = System.currentTimeMillis();

		String savepath = "d:/t1.html";// 生成文件地址s

		// JspToHtml ss = new JspToHtml(
		// "http://192.168.158.101:8090/product/detail.do?id=333",
		// savepath);
		// ss.run();
		// String str = crawlByRegex(
		// "http://192.168.158.101:8090/product/detail.do?id=325",
		// "<div\\s+id=\"buy(.*?)\">([\\s\\S]+)</div>([\\s\\S]+)<div\\s+id=\"ConInfo\"\\s+class=\"box1210\">",
		// "测试");

		String myCookie = "JSESSIONID=A57F6493C5D456A0B6487A3AD31B6F1F; allarea=192224-212131-214785-214786-214787-0; a2404_pages=5; a2404_times=1 ;lastarea=212420";

		String realPath = "http://192.168.158.101:8090";

		String urlStr = realPath + "/product/detail.do?id=706";

		JspToHtmlByURL(urlStr, savepath, 123, realPath, myCookie);
		System.out.println("dd");
	}
}