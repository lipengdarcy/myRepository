package com.witsafe.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.witsafe.model.Company;
import com.witsafe.service.HelloService;

@Controller
public class HelloController {
	
	@Autowired
	private HelloService HelloService;
	
	private static Logger log = Logger.getLogger("InfoLogger");
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		log.info("$$$$>>>>>>>>进入首页！");
		Company c = HelloService.get(1);
		model.put("record", c);
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {

		ModelAndView model = new ModelAndView();
		model.setViewName("hello");
		model.addObject("msg", name);

		return model;

	}

}