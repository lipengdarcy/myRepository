package com.runlion.shop.controller.home.product;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.runlion.shop.common.util.StringHandler;
import com.runlion.shop.dao.BspCommentMapper;
import com.runlion.shop.entity.CommentDTO;
import com.runlion.shop.entity.CommentRateDTO;
import com.runlion.shop.service.BspCommentService;

@Controller
@RequestMapping("/comment")
public class CommentsController {

	private static Logger loggerinfo = Logger.getLogger("InfoLogger");
	@Autowired
	BspCommentService bspCommentService;
	@Autowired
	private BspCommentMapper bcm;

	@RequestMapping("/productCommentDetail")
	public String productCommentDetail(
			@RequestParam(defaultValue = "0") Integer id,
			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap m,
			@RequestParam(required = false, value = "pageNumber", defaultValue = "1") int pageNumber,
			@RequestParam(required = false, value = "pageNumber1", defaultValue = "1") int pageNumber1,
			@RequestParam(required = false, value = "pageNumber2", defaultValue = "1") int pageNumber2,
			@RequestParam(required = false, value = "pageNumber3", defaultValue = "1") int pageNumber3,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize,
			@RequestParam(required = false, value = "type") String type) {
		loggerinfo.info("==========[productCommentDetail detail] Start...");
		// 好评率
		int good;
		int bad;
		int middle;
		CommentRateDTO rate = new CommentRateDTO();
		// 好评
		int g = bcm.countBygood(id);
		// 差评
		int b = bcm.countBybad(id);
		// 中评
		int mi = bcm.countBymiddle(id);
		int count = g + b + mi;
		if (count != 0) {
			good = 100 * g / count;

			bad = 100 * b / count;

			middle = 100 * mi / count;
		} else {
			good = 0;
			bad = 0;
			middle = 0;
		}
		rate.setBad(bad);
		rate.setGood(good);
		rate.setMiddle(middle);
		rate.setCount(count);
		m.put("rate", rate);
		// 全部评价列表
		int startNum = (pageNumber - 1) * pageSize;
		List<CommentDTO> commentList = bspCommentService.getCommentList(
				startNum, pageSize, id, "all");
		// 好评价列表
		int startNum1 = (pageNumber1 - 1) * pageSize;
		List<CommentDTO> commentList1 = bspCommentService.getCommentList(
				startNum1, pageSize, id, "good");
		// 差评价列表
		int startNum2 = (pageNumber2 - 1) * pageSize;
		List<CommentDTO> commentList2 = bspCommentService.getCommentList(
				startNum2, pageSize, id, "bad");
		// 中评价列表
		int startNum3 = (pageNumber3 - 1) * pageSize;
		List<CommentDTO> commentList3 = bspCommentService.getCommentList(
				startNum3, pageSize, id, "middle");
		m.put("commentList3", commentList3);
		String newPath3 = "productCommentDetail.do?type=3&&id=" + id
				+ "&&pageNumber3=";
		m.put("pageDiv3", StringHandler.generatePageDiv(mi, pageSize,
				pageNumber3, newPath3));
		m.put("commentList2", commentList2);
		String newPath2 = "productCommentDetail.do?type=4&&id=" + id
				+ "&&pageNumber2=";
		m.put("pageDiv2", StringHandler.generatePageDiv(b, pageSize,
				pageNumber2, newPath2));
		m.put("commentList1", commentList1);
		String newPath1 = "productCommentDetail.do?type=2&&id=" + id
				+ "&&pageNumber1=";
		m.put("pageDiv1", StringHandler.generatePageDiv(g, pageSize,
				pageNumber1, newPath1));
		SimpleDateFormat forday = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat fortime = new SimpleDateFormat("HH:mm:ss");
		m.put("forday", forday);
		m.put("fortime", fortime);
		m.put("commentList", commentList);
		m.put("type", type);
		String newPath = "productCommentDetail.do?type=1&&id=" + id
				+ "&&pageNumber=";
		m.put("pageDiv", StringHandler.generatePageDiv(count, pageSize,
				pageNumber, newPath));

		loggerinfo.info("==========[productCommentDetail detail] End...");
		return "home/product/comment";
	}
}
