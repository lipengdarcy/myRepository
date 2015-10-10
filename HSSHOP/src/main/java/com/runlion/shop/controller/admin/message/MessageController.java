package com.runlion.shop.controller.admin.message;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.BspMessage;
import com.runlion.shop.service.MessageService;

/**
 * @Description TODO
 * @author 李俊杰
 * @date 2015-7-28
 * @version V1.0
 */
@Controller
@RequestMapping("/admin/message")
public class MessageController extends BaseController {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private MessageService messageService;

	@RequestMapping(params = "method=toMessageList")
	public ModelAndView toMessageList(HttpServletRequest request, ModelMap m) {
		ModelAndView mv = new ModelAndView();
		loggerinfo.info("==========[NewsManagerController news]...");
		int pageNo = integer("pageNum", request) == -1 ? 1 : integer("pageNum",
				request);
		int pageSize = integer("numPerPage", request) == -1 ? 20 : integer(
				"numPerPage", request);

		int startNum = (pageNo - 1) * pageSize;

		List<BspMessage> messageList = messageService.getMessageList(startNum,
				pageSize);
		int count = messageService.countMessage();
		mv.addObject("status", "success");
		mv.addObject("messageList", messageList);
		mv.addObject("totalCount", count);
		mv.addObject("currentPage", pageNo);
		mv.addObject("numPerPage", pageSize);

		mv.setViewName("admin/message/message-list");
		return mv;
	}
}
