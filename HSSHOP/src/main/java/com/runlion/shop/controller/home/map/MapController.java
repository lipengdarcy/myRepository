package com.runlion.shop.controller.home.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.dao.BspSaleaddressMapper;
import com.runlion.shop.entity.BspSaleaddress;
import com.runlion.shop.service.BspSaleaddressService;

@Controller
@RequestMapping("/map")
public class MapController {

	@Autowired
	BspSaleaddressMapper bspSaleaddressMapper;

	@Autowired
	BspSaleaddressService bspSaleaddressService;

	@RequestMapping("/tomap")
	public ModelAndView tomap(String address) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("address", address);
		mv.setViewName("home/map/mapshow");
		return mv;
	}

	@RequestMapping("/showDoor")
	@ResponseBody
	public List<Map<String, String>> showDoor(String left, String right) {
		List<BspSaleaddress> all = bspSaleaddressMapper.selectall();
		List<Map<String, String>> rs = new ArrayList<Map<String, String>>();
		for (BspSaleaddress sale : all) {
			if (sale.getWorktime() != null && !sale.getWorktime().equals("")) {

				if (bspSaleaddressService.isinscope(left, right,
						sale.getWorktime())) {
					Map<String, String> door = new HashMap<String, String>();
					String[] point = bspSaleaddressService.turntoDouble(sale
							.getWorktime());
					door.put("left", point[0]);
					door.put("right", point[1]);
					door.put("name", sale.getName());
					door.put("contacts", sale.getContacts());
					door.put("tel", sale.getTel());
					door.put("address", sale.getAddress());

					rs.add(door);
				}
			}
		}
		return rs;
	}

	@RequestMapping("/togetpointmap")
	public String togetpointmap(String point,
			@RequestParam(defaultValue = "当前位置") String title,
			@RequestParam(defaultValue = "") String content, ModelMap m) {
		List<String> baidumappoint = new ArrayList<String>();

		String mapx = "120.158268";
		String mapy = "30.252927";

		if (!StringHandler.isEmpty(point)
				&& StringUtils.split(point, ",").length == 2) {
			for (String string : StringUtils.split(point, ",")) {
				baidumappoint.add(StringUtils.trim(string));
			}

			mapx = baidumappoint.get(0);
			mapy = baidumappoint.get(1);
		}

		m.put("mapx", mapx);
		m.put("mapy", mapy);
		m.put("title", title);
		m.put("content", content);
		return "home/map/getpoint";
	}

	@RequestMapping("/togetcenterbusiness")
	public String togetcenterbusiness(Integer id, ModelMap m) {
		String mapx = "120.158268";
		String mapy = "30.252927";

		List<String> baidumappoint = new ArrayList<String>();
		BspSaleaddress store = bspSaleaddressMapper.selectByPrimaryKey(id);

		if (store != null) {

			if (!StringHandler.isEmpty(store.getWorktime())
					&& StringUtils.split(store.getWorktime(), ",").length == 2) {
				for (String string : StringUtils
						.split(store.getWorktime(), ",")) {
					baidumappoint.add(StringUtils.trim(string));
				}

				mapx = baidumappoint.get(0);
				mapy = baidumappoint.get(1);
			}
		}

		m.put("mapx", mapx);
		m.put("mapy", mapy);
		m.put("store", store);
		return "home/map/togetcenterbusiness";
	}
}
