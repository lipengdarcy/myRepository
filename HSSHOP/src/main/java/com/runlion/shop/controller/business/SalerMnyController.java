package com.runlion.shop.controller.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspPayorder;
import com.runlion.shop.service.BspPayOrderService;
import com.runlion.shop.service.business.BspNcEntityService;
import com.runlion.shop.vo.pay.SelPayorderListVO;

//import com.runlion.shop.service.TransActionTest;

/**
 * 经销商资金管理controller
 * */
@Controller
@RequestMapping("/salermny")
public class SalerMnyController {
	private static Logger logger = Logger.getLogger(SalerMnyController.class);
	@Autowired
	private BspPayOrderService bspPayOrderService;
	@Autowired
	private BspNcEntityService bspNcEntityService;

	// @Resource(name = "transActionTest")
	// private TransActionTest test;

	@RequestMapping("/toSalermnyList")
	public ModelAndView toSalermnyList(HttpSession httpSession,
			SelPayorderListVO selVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		Integer userId = (Integer) httpSession
				.getAttribute(BaseController.ACCOUNT_SESSION_UID);
		if (userId == null) {
			return new ModelAndView("redirect:/account/login.do");
		}
		if (selVO == null) {
			selVO = new SelPayorderListVO();
		}
		selVO.setUid(userId);
		List<BspPayorder> payList = bspPayOrderService
				.getBspPayorderList(selVO);
		int count = bspPayOrderService.countBspPayorderList(selVO);

		mv.addObject("payList", payList);

		String selWords = StringHandler.pramVoToKeyval(selVO,
				new ArrayList<String>(Arrays.asList("pageNum")));
		String newPath = "toSalermnyList.do?";
		if (selWords != null && !selWords.equals("")) {
			newPath += selWords + "&pageNum=";
		} else {
			newPath += "&pageNum=";
		}
		mv.addObject("pageDiv", StringHandler.generatePageDiv(count,
				selVO.getNumPerPage(), selVO.getPageNum(), newPath));
		mv.addObject("selVO", selVO);
		mv.setViewName("/home/center/business/salermnyList");
		return mv;

	}

	/**
	 * 处理nc推送的经销商可售产品信息
	 * 
	 * @return
	 * @throws Exception
	 * @RequestMapping("/getNCProlist")
	 * @ResponseBody public Map getNCProlist(HttpServletRequest request,
	 *               NCProInforVO proInfor) throws Exception { Map<String,
	 *               Object> map = new HashMap();
	 * 
	 *               if (proInfor.getProList().size() == 0) { map.put("state",
	 *               "failed"); map.put("msg", "传送的数据的长度为0");
	 * 
	 *               } else { map =
	 *               bspNcEntityService.handNcProList(proInfor.getProList()); }
	 * 
	 *               return map; }
	 */
}
