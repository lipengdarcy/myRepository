package com.example.myfirstapp.network;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.content.Context;

@SuppressWarnings("deprecation")
public class WebUtil {
	private static String SERVER_IP = "127.0.0.1";

	public static JSONArray getJSONArrayByWeb(String methodName,
			JSONArray params) {

		String returnValue = "";
		JSONArray result = null;
		HttpParams httpParams = new BasicHttpParams();
		httpParams.setParameter("charset", "UTF-8");
		HttpClient hc = new DefaultHttpClient(httpParams);
		HttpPost hp = new HttpPost(SERVER_IP + "/HelloServer/servlet/"
				+ methodName);
		try {
			hp.setEntity(new StringEntity(params.toString(), "UTF-8"));
			HttpResponse hr = hc.execute(hp);
			if (hr.getStatusLine().getStatusCode() == 200) {
				returnValue = EntityUtils.toString(hr.getEntity(), "UTF-8");
				result = new JSONArray(returnValue);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (hc != null) {
			hc.getConnectionManager().shutdown();
		}
		return result;
	}

	public static String getContentFromServer() throws Exception {
		String r = "";
		// 打开一个HttpURLConnection连接
		URL url = new URL("");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		r = (String) connection.getContent();
		connection.disconnect();
		return r;
	}

	public static InputStream getHttpClientInputStream(Context context,
			String requestUrl, Map<String, String> param) throws Exception {
		HttpClient client = new DefaultHttpClient();
		if (getAPNType(context) == NetWorkUtil.CMWAP) // 当请求的网络为wap的时候，就需要添加中国移动代理
		{
			HttpHost proxy = new HttpHost("10.0.0.172", 80);
			client.getParams().setParameter("DEFAULT_PROXY", proxy);
		}
		HttpPost hp = new HttpPost(requestUrl);
		hp.setHeader("Charset", "UTF-8");
		hp.setHeader("Content-Type", "application/x-www-form-urlencoded");
		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();

		Iterator<String> it = param.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			list.add(new BasicNameValuePair(key, param.get(key)));
		}
		hp.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
		HttpResponse response = null;
		response = client.execute(hp);
		return response.getEntity().getContent();
	}

	public static String getAPNType(Context context) {
		return "CMWAP";
	}
}
