package com.runlion.shop.controller.business;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.service.OrderService;
import com.runlion.shop.service.business.BspNcEntityService;
import com.runlion.shop.vo.NCProInforVO;

//import com.runlion.shop.service.TransActionTest;

/**
 * 订单相关controller
 **/
@Controller
@RequestMapping("/saler")
public class SalerController {
	private static Logger logger = Logger.getLogger(SalerController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	private BspNcEntityService bspNcEntityService;

	// @Resource(name = "transActionTest")
	// private TransActionTest test;

	@RequestMapping("/toOrderNumTelVerf")
	public ModelAndView confirmorder(HttpSession httpSession,
			String selectedCartItemKeyList, String pickway) throws Exception {

		ModelAndView mv = new ModelAndView();

		mv.addObject("orderPrice", "");
		mv.setViewName("/home/center/business/orderNumTelCodeVerf");
		return mv;

	}

	/**
	 * 处理nc推送的经销商可售产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getNCProlist")
	@ResponseBody
	public Map getNCProlist(HttpServletRequest request, NCProInforVO proInfor)
			throws Exception {
		Map<String, Object> map = new HashMap();

		if (proInfor.getProList().size() == 0) {
			map.put("state", "failed");
			map.put("msg", "传送的数据的长度为0");

		} else {
			map = bspNcEntityService.handNcProList(proInfor.getProList());
		}

		return map;
	}
}
