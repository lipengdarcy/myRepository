package com.runlion.shop.controller.app.b2b;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.runlion.shop.entity.BspNews;
import com.runlion.shop.entity.common.JsonResponseData;

@Controller
@RequestMapping("/b2bapp/api")
public class B2bAppIndexController {

	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	/**
	 * B2B首页接口
	 * 
	 * @param app
	 * @return AppResponseData<List<BspNews>> 返回类型
	 */
	@RequestMapping
	@ResponseBody
	public JsonResponseData<List<BspNews>> load() {
		loggerinfo.info("==========[B2bAppIndexController load] Start...");

		JsonResponseData<List<BspNews>> app = new JsonResponseData<List<BspNews>>();
		List<BspNews> list = new ArrayList<BspNews>();
		BspNews entity = new BspNews();
		entity.setNewsid(1);
		entity.setTitle("b2b测试新闻1");

		BspNews entity1 = new BspNews();
		entity1.setNewsid(2);
		entity1.setTitle("b2b测试新闻2");

		BspNews entity2 = new BspNews();
		entity2.setNewsid(3);
		entity2.setTitle("b2b测试新闻3");

		list.add(entity);
		list.add(entity1);
		list.add(entity2);

		app.setResult(list);
		app.setErrorMsg("");
		app.setErrorNo("");
		app.setSuccess(true);

		loggerinfo.info("==========[B2bAppIndexController load] End...");
		return app;
	}

	@RequestMapping(params = "method=toIndex")
	@ResponseBody
	public JsonResponseData<List<BspNews>> appIndex() {
		loggerinfo.info("==========[B2bAppIndexController appIndex] Start...");

		JsonResponseData<List<BspNews>> app = new JsonResponseData<List<BspNews>>();
		List<BspNews> list = new ArrayList<BspNews>();
		BspNews entity = new BspNews();
		entity.setNewsid(1);
		entity.setTitle("b2b测试新闻1");

		BspNews entity1 = new BspNews();
		entity1.setNewsid(2);
		entity1.setTitle("b2b测试新闻2");

		BspNews entity2 = new BspNews();
		entity2.setNewsid(3);
		entity2.setTitle("b2b测试新闻3");

		list.add(entity);
		list.add(entity1);
		list.add(entity2);

		app.setResult(list);
		app.setErrorMsg("");
		app.setErrorNo("");
		app.setSuccess(true);

		loggerinfo.info("==========[B2bAppIndexController appIndex] End...");
		return app;
	}

	@RequestMapping(params = "method=toTest")
	public String appTest() {
		loggerinfo.info("==========[B2bAppIndexController appIndex] Start...");

		JsonResponseData<List<BspNews>> app = new JsonResponseData<List<BspNews>>();
		List<BspNews> list = new ArrayList<BspNews>();
		BspNews entity = new BspNews();
		entity.setNewsid(1);
		entity.setTitle("b2b测试新闻1");

		BspNews entity1 = new BspNews();
		entity1.setNewsid(2);
		entity1.setTitle("b2b测试新闻2");

		BspNews entity2 = new BspNews();
		entity2.setNewsid(3);
		entity2.setTitle("b2b测试新闻3");

		list.add(entity);
		list.add(entity1);
		list.add(entity2);

		app.setResult(list);
		app.setErrorMsg("");
		app.setErrorNo("");
		app.setSuccess(true);

		loggerinfo.info("==========[B2bAppIndexController appIndex] End...");
		return "ccc";
	}

}
