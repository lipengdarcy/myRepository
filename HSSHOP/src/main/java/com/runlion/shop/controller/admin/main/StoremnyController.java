package com.runlion.shop.controller.admin.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.BspPayOrderService;
import com.runlion.shop.vo.pay.SelPayorderListVO;
import com.runlion.shop.vo.pay.StoremnyInforVO;

/**
 * @Description StoremnyController控制器
 * @author lwt
 * @date 2014/11/11 11:15:02
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/storemny")
public class StoremnyController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	BspPayOrderService bspPayOrderService;

	@RequestMapping
	public ModelAndView load(HttpSession httpSession, SelPayorderListVO selVO)
			throws Exception {
		loggerinfo.info("==========[NewsController load] Start...");
		ModelAndView mv = new ModelAndView();
		if (selVO == null) {
			selVO = new SelPayorderListVO();
		}
		selVO.setNumPerPage(20);
		List<StoremnyInforVO> payList = bspPayOrderService
				.getStoremnyInforVOList(selVO);
		int count = bspPayOrderService.countStoremnyInforVOList(selVO);

		mv.addObject("payList", payList);

		mv.addObject("selVO", selVO);
		mv.addObject("totalCount", count);
		mv.setViewName("admin/storemny/storemnylist");
		loggerinfo.info("==========[NewsController load] End...");
		return mv;
	}

	@RequestMapping(params = "method=setStatus")
	@ResponseBody
	public StatusHtml add(Integer id) throws Exception {
		loggerinfo.info("==========[NewsController add] Start...");

		StatusHtml statusHtml = new StatusHtml();
		if (bspPayOrderService.setStatus(id, 1)) {
			statusHtml.setStatusCode("200");
			statusHtml.setMessage("操作成功");
			statusHtml.setNavTabId("page49");
		} else {
			statusHtml.setStatusCode("300");
			statusHtml.setMessage("操作错误");
		}

		loggerinfo.info("==========[NewsController add] End...");
		return statusHtml;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 忽略字段绑定异常
		// binder.setIgnoreInvalidFields(true);

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "insertTime",
				new CustomDateEditor(format, true));
		binder.registerCustomEditor(Date.class, "modifyTime",
				new CustomDateEditor(format, true));
	}
}
