package org.darcy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SmsService {
	public static void main(String[] args) {
		postForm();
	}

	/**
	 * post方式提交表单（模拟用户登录请求）
	 */
	public static void postForm() {
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		// 创建httppost
		HttpPost httppost = new HttpPost("http://sms.runlion.com/PostSms.ashx");
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("MsgId", ""));
		formparams.add(new BasicNameValuePair("DeptId", ""));
		formparams.add(new BasicNameValuePair("EmpId", ""));
		formparams.add(new BasicNameValuePair("From", ""));
		formparams.add(new BasicNameValuePair("To", "13777898622"));
		formparams.add(new BasicNameValuePair("Time", ""));
		formparams.add(new BasicNameValuePair("Flag", ""));
		formparams.add(new BasicNameValuePair("Success", ""));
		formparams.add(new BasicNameValuePair("Cardid", ""));
		formparams.add(new BasicNameValuePair("MsgType", ""));
		formparams.add(new BasicNameValuePair("SendType", ""));
		formparams.add(new BasicNameValuePair("Subject", ""));
		formparams.add(new BasicNameValuePair("Body", "aaa bbb"));
		formparams.add(new BasicNameValuePair("vcode", ""));

		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = closeableHttpClient
					.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out
							.println("--------------------------------------");
					System.out.println("Response content: "
							+ EntityUtils.toString(entity, "UTF-8"));
					System.out
							.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
