package org.darcy.controller;

import java.util.List;

import org.apache.log4j.Logger;
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
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,
			ModelMap m) throws Exception {
		loggerinfo.info("==========[ProductController load] Start...");

		BspProductsExample example = new BspProductsExample();
		List<BspProducts> list = service.selectByExample(example, pageNumber,
				pageSize);

		loggerinfo.info("==========[ProductController load] End...");

		return list;
	}

}
