package com.runlion.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspCommentMapper;
import com.runlion.shop.entity.CommentDTO;
import com.runlion.shop.vo.CommentVO;

@Service
public class BspCommentService {

	@Autowired
	private BspCommentMapper bcm;

	public List<CommentDTO> getCommentList(int startNum, int pageSize, int pid,
			String type) {
		List<CommentDTO> commentList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("START_NUM", startNum);
		map.put("LIMIT_NUM", pageSize);
		map.put("pid", pid);
		if (type.equals("all")) {
			commentList = bcm.getList(map);
		} else if (type.equals("good")) {
			commentList = bcm.getListByGood(map);
		} else if (type.equals("bad")) {
			commentList = bcm.getListByBad(map);
		} else {
			commentList = bcm.getListByMiddle(map);
		}
		return commentList;
	}

	/**
	 * 获取某个用户对商品的评论列表
	 * 
	 * @param startNum
	 * @param pageSize
	 * @param uid
	 * @return
	 */
	public List<CommentVO> getVOList(int startNum, int pageSize, int uid) {
		List<CommentVO> voList = null;
		//
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PAGE_NUM", startNum);
		map.put("PAGE_SIZE", pageSize);
		map.put("UID", uid);
		voList = bcm.getVOList(map);
		//
		return voList;
	}

	/**
	 * 获取某个用户对商品的评论总数
	 * 
	 * @param uid
	 * @return
	 */
	public int countVOList(int uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("UID", uid);
		return bcm.countVOList(map);
	}
}
