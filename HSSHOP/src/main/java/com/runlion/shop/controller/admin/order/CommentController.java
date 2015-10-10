package com.runlion.shop.controller.admin.order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.runlion.shop.controller.BaseController;
import com.runlion.shop.dao.BspCommentMapper;
import com.runlion.shop.dao.BspProductsMapper;
import com.runlion.shop.entity.BspComment;
import com.runlion.shop.entity.BspCommentExample;
import com.runlion.shop.entity.BspProducts;
import com.runlion.shop.entity.BspUserdetails;
import com.runlion.shop.entity.BspUserranks;
import com.runlion.shop.entity.BspUsers;
import com.runlion.shop.entity.common.StatusHtml;
import com.runlion.shop.service.UserService;

@Controller
@RequestMapping("/admin/comment")
public class CommentController extends BaseController {

	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	@Autowired
	private UserService userService;
	@Autowired
	private BspCommentMapper bcm;
	@Autowired
	private BspProductsMapper bpm;

	@RequestMapping("/commentlist")
	public ModelAndView commentlist(
			@RequestParam(value = "pageNum", required = false) String pageNoStr,
			@RequestParam(value = "numPerPage", required = false) String pageSizeStr,
			@RequestParam(value = "cid", required = false) String cid,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "score", required = false) String score,
			@RequestParam(value = "status1", required = false) String st) {
		ModelAndView mv = new ModelAndView();
		pageNoStr = pageNoStr == null || "".equals(pageNoStr) ? "1" : pageNoStr;
		pageSizeStr = pageSizeStr == null || "".equals(pageSizeStr) ? "30"
				: pageSizeStr;

		int pageNo = Integer.parseInt(pageNoStr);
		int pageSize = Integer.parseInt(pageSizeStr);

		int startNum = (pageNo - 1) * pageSize;

		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", pageSize);
		par.put("cid", cid);
		par.put("username", username);
		par.put("name", name);
		par.put("score", score);
		par.put("status", st);
		List<Map<String, Object>> commentlist = bcm.selectByExamplePrice(par);
		int cnt = bcm.getCommentCount(par);
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		mv.setViewName("admin/comment/commentlist");
		mv.addObject("status", "success");
		mv.addObject("commentlist", commentlist);
		mv.addObject("totalCount", cnt);
		mv.addObject("currentPage", pageNo);
		mv.addObject("numPerPage", pageSize);
		mv.addObject("formate", formate);
		loggerinfo.info("==========[AreaManagerController saleaddress] End...");
		return mv;
	}

	@RequestMapping("deleteOne")
	@ResponseBody
	public StatusHtml delete(int cid) {
		StatusHtml statusHtml = new StatusHtml();
		bcm.deleteByPrimaryKey(cid);
		statusHtml.setStatusCode("200");
		statusHtml.setMessage("操作成功");
		statusHtml.setNavTabId("page40");
		return statusHtml;
	}

	@RequestMapping("deleteSelect")
	@ResponseBody
	public StatusHtml deleteSelect(String ids) {
		StatusHtml statusHtml = new StatusHtml();
		List<Integer> idList = new ArrayList<Integer>();

		if (ids != null && ids.length() > 0) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				idList.add(Integer.parseInt(id));
			}
		}
		BspCommentExample oe = new BspCommentExample();
		oe.or().andCidIn(idList);
		bcm.deleteByExample(oe);
		statusHtml.setStatusCode("200");
		statusHtml.setMessage("操作成功");
		statusHtml.setNavTabId("page40");
		return statusHtml;
	}

	@RequestMapping("passOne")
	@ResponseBody
	public StatusHtml passOne(int cid) {
		StatusHtml statusHtml = new StatusHtml();
		BspComment c = bcm.selectByPrimaryKey(cid);
		c.setStatus(1);
		bcm.updateByPrimaryKey(c);

		statusHtml.setStatusCode("200");
		statusHtml.setMessage("操作成功");
		statusHtml.setNavTabId("page40");
		return statusHtml;
	}

	@RequestMapping("pass")
	@ResponseBody
	public String pass(int cid) {
		StatusHtml statusHtml = new StatusHtml();
		BspComment c = bcm.selectByPrimaryKey(cid);
		c.setStatus(1);
		bcm.updateByPrimaryKey(c);
		return "ok";
	}

	@RequestMapping("passSelect")
	@ResponseBody
	public StatusHtml passSelect(String ids) {
		StatusHtml statusHtml = new StatusHtml();
		List<Integer> idList = new ArrayList<Integer>();

		if (ids != null && ids.length() > 0) {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				idList.add(Integer.parseInt(id));
			}
		}
		for (int cid : idList) {
			BspComment c = bcm.selectByPrimaryKey(cid);
			c.setStatus(1);
			bcm.updateByPrimaryKey(c);
		}
		statusHtml.setStatusCode("200");
		statusHtml.setMessage("操作成功");
		statusHtml.setNavTabId("page40");
		return statusHtml;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(int cid) {
		ModelAndView mv = new ModelAndView();
		BspComment c = bcm.selectByPrimaryKey(cid);
		mv.addObject("comment", c);
		// 用户
		BspUsers user = userService.getUsersById(c.getUid());
		mv.addObject("user", user);
		if (user != null) {
			// 用户等级
			BspUserranks userrank = userService.getUserranks(user.getUserrid());
			mv.addObject("userrank", userrank);
			// 用户详细信息
			BspUserdetails userdetails = userService.getUserdetails(user
					.getUid());
			mv.addObject("userdetails", userdetails);
		}
		BspProducts p = bpm.selectByPrimaryKey(c.getPid());
		mv.addObject("product", p);
		SimpleDateFormat forday = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fortime = new SimpleDateFormat("HH:mm:ss");
		mv.addObject("forday", forday);
		mv.addObject("fortime", fortime);
		mv.setViewName("admin/comment/commentDetail");
		mv.addObject("status", "success");
		return mv;
	}
}