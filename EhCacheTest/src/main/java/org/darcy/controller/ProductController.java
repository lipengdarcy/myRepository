package org.darcy.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.darcy.common.JqGridData;
import org.darcy.entity.BspProducts;
import org.darcy.entity.BspProductsExample;
import org.darcy.service.BspProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

//import static org.hamcrest.Matchers.*;

/**
 * 产品controller
 * */
@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private BspProductsService service;

	// test
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(ModelMap m) throws Exception {
		loggerinfo.info("==========[ProductController test] Start...");
		// test for service
		BspProducts p = service.selectByPrimaryKey(54);
		System.out.println("BspProducts name: " + p.getName());

		// test for pagination
		BspProductsExample example = new BspProductsExample();
		List<BspProducts> list = service.selectByExample(example, 1, 10);
		System.out
				.println("bspProductsService.selectByExample: " + list.size());

		loggerinfo.info("==========[ProductController test] End...");
		return "index";
	}

	// 产品列表list
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<BspProducts> list(
			@RequestParam(required = false, defaultValue = "1", value = "page") int pageNumber, // 表示请求页码的参数名称
			@RequestParam(required = false, defaultValue = "20", value = "rows") int pageSize, // 表示请求行数的参数名称
			@RequestParam(required = false, value = "sidx") String sidx, // 表示用于排序的列名的参数名称
			@RequestParam(required = false, value = "sord") String sord, // 表示采用的排序方式的参数名称
			@RequestParam(required = false, value = "_search") boolean search, // 表示是否是搜索请求的参数名称
			@RequestParam(required = false, value = "searchField") String searchField,
			@RequestParam(required = false, value = "searchOper") String searchOper,
			@RequestParam(required = false, value = "searchString") String searchString,
			@RequestParam(required = false, value = "filters") String filters,
			ModelMap m) throws Exception {
		loggerinfo.info("==========[ProductController load] Start...");

		BspProductsExample example = new BspProductsExample();
		List<BspProducts> list = service.selectByExample(example, pageNumber,
				pageSize);

		loggerinfo.info("==========[ProductController load] End...");

		return list;
	}

	// 产品列表list,前台jqgrid展示
	@ResponseBody
	@RequestMapping(value = "/jqgrid", method = RequestMethod.GET)
	public JqGridData<BspProducts> jqgrid(
			@RequestParam(required = false, defaultValue = "1", value = "page") int pageNumber, // 表示请求页码的参数名称
			@RequestParam(required = false, defaultValue = "20", value = "rows") int pageSize, // 表示请求行数的参数名称
			@RequestParam(required = false, value = "sidx") String sidx, // 表示用于排序的列名的参数名称
			@RequestParam(required = false, value = "sord") String sord, // 表示采用的排序方式的参数名称
			@RequestParam(required = false, value = "_search") boolean search, // 表示是否是搜索请求的参数名称
			@RequestParam(required = false, value = "searchField") String searchField,
			@RequestParam(required = false, value = "searchOper") String searchOper,
			@RequestParam(required = false, value = "searchString") String searchString,
			@RequestParam(required = false, value = "filters") String filters,
			ModelMap m) throws Exception {
		loggerinfo.info("==========[ProductController load] Start...");

		BspProductsExample example = new BspProductsExample();
		List<BspProducts> list = service.selectByExample(example, pageNumber,
				pageSize);
		Page p = (Page) list;
		// JqGridData(int total, int page, int records, List<T> rows)
		JqGridData<BspProducts> result = new JqGridData<BspProducts>(
				p.getPages(), p.getPageNum(), (int) p.getTotal(), list);

		loggerinfo.info("==========[ProductController load] End...");

		return result;
	}
}
