package com.runlion.shop.controller.home.thymeleafe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/testthymeleafe")
public class TestThymeleafeControler {

	@RequestMapping(value = { "/index" })
	public String goSerchUserH(Model m) {
		System.out.println("sss");
		m.addAttribute("allUsers", "测试");
		return "index";
	}

}
