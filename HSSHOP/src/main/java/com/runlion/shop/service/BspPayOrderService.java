package com.runlion.shop.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.runlion.shop.dao.BspPayorderMapper;
import com.runlion.shop.dao.CusPayorderMapper;
import com.runlion.shop.entity.BspPayorder;
import com.runlion.shop.entity.BspPayorderExample;
import com.runlion.shop.vo.pay.SelPayorderListVO;
import com.runlion.shop.vo.pay.StoremnyInforVO;

@Service
public class BspPayOrderService {

	@Autowired
	BspPayorderMapper bspPayorderMapper;
	@Autowired
	CusPayorderMapper cusPayorderMapper;

	public List<BspPayorder> getBspPayorderList(SelPayorderListVO selVO) {
		this.handVo(selVO);

		PageHelper.startPage(selVO.getPageNum(), selVO.getNumPerPage());

		BspPayorderExample ex = new BspPayorderExample();
		Integer state = selVO.getState();
		Integer paytype = selVO.getPaytype();
		String startDate = selVO.getStartDate();
		String endDate = selVO.getEndDate();
		Integer uid = selVO.getUid();

		BspPayorderExample.Criteria ce = ex.createCriteria().andUidEqualTo(uid);
		if (state != null && state > -1) {
			ce.andStatusEqualTo(state);
		}
		if (paytype != null && paytype > -1) {
			ce.andPaytypeEqualTo(paytype);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(startDate + " 00:00:00");
			ce.andCreatetimeGreaterThan(date);
		} catch (Exception e) {

		}
		// //
		try {
			Date date = sdf.parse(endDate + " 23:59:59");
			ce.andCreatetimeLessThan(date);
		} catch (Exception e) {

		}

		ex.setOrderByClause("createtime desc");
		List<BspPayorder> list = bspPayorderMapper.selectByExample(ex);
		return list;
	}

	public int countBspPayorderList(SelPayorderListVO selVO) {
		this.handVo(selVO);

		BspPayorderExample ex = new BspPayorderExample();
		Integer state = selVO.getState();
		Integer paytype = selVO.getPaytype();
		String startDate = selVO.getStartDate();
		String endDate = selVO.getEndDate();
		Integer uid = selVO.getUid();

		BspPayorderExample.Criteria ce = ex.createCriteria().andUidEqualTo(uid);
		if (state != null && state > -1) {
			ce.andStatusEqualTo(state);
		}
		if (paytype != null && paytype > -1) {
			ce.andPaytypeEqualTo(paytype);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(startDate + " 00:00:00");
			ce.andPaytimeGreaterThan(date);
		} catch (Exception e) {

		}
		// //
		try {
			Date date = sdf.parse(endDate + " 23:59:59");
			ce.andPaytimeLessThan(date);
		} catch (Exception e) {

		}

		return bspPayorderMapper.countByExample(ex);
	}

	/**
	 * 
	 * @param selVO
	 * @return
	 */
	public List<StoremnyInforVO> getStoremnyInforVOList(SelPayorderListVO selVO) {
		this.handVo(selVO);

		BspPayorderExample ex = new BspPayorderExample();
		Integer state = selVO.getState();
		String startDate = selVO.getStartDate();
		String endDate = selVO.getEndDate();
		String userName = selVO.getUsername();

		Integer pageNum = selVO.getPageNum();
		if (pageNum > 0) {
			pageNum = pageNum - 1;
		}
		Integer numPerPage = selVO.getNumPerPage();
		Integer startRow = pageNum * numPerPage;

		Map<String, Object> par = new HashMap();
		par.put("status", state);
		par.put("startDate", null);
		par.put("endDate", null);
		par.put("username", userName);
		par.put("startRow", startRow);
		par.put("numPerPage", numPerPage);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(startDate + " 00:00:00");
			par.put("startDate", startDate);
		} catch (Exception e) {

		}
		// //
		try {
			Date date = sdf.parse(endDate + " 23:59:59");
			par.put("endDate", endDate);
		} catch (Exception e) {

		}
		List<StoremnyInforVO> list = cusPayorderMapper.selStoremnyVOList(par);
		return list;
	}

	/**
	 * 
	 * @param selVO
	 * @return
	 */
	public int countStoremnyInforVOList(SelPayorderListVO selVO) {
		this.handVo(selVO);

		BspPayorderExample ex = new BspPayorderExample();
		Integer state = selVO.getState();
		String startDate = selVO.getStartDate();
		String endDate = selVO.getEndDate();
		String userName = selVO.getUsername();

		Map<String, Object> par = new HashMap();
		par.put("status", state);
		par.put("startDate", null);
		par.put("endDate", null);
		par.put("username", userName);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(startDate + " 00:00:00");
			par.put("startDate", startDate);
		} catch (Exception e) {

		}
		// //
		try {
			Date date = sdf.parse(endDate + " 23:59:59");
			par.put("endDate", endDate);
		} catch (Exception e) {

		}

		return cusPayorderMapper.countStoremnyVOList(par);
	}

	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean setStatus(Integer id, Integer status) {
		BspPayorder payorder = new BspPayorder();
		payorder.setId(id);
		payorder.setStatus(status);
		int rsi = bspPayorderMapper.updateByPrimaryKeySelective(payorder);
		if (rsi > 0) {
			return true;
		} else {
			return false;
		}
	}

	private void handVo(SelPayorderListVO selVO) {
		if (selVO == null) {
			selVO = new SelPayorderListVO();
		}
		if (selVO.getPageNum() == null || selVO.getPageNum() < 1) {
			selVO.setPageNum(1);
		}
		if (selVO.getNumPerPage() == null || selVO.getNumPerPage() < 1) {
			selVO.setNumPerPage(10);
		}
		if (selVO.getState() == null) {
			selVO.setState(-1);
		}
		if (selVO.getPaytype() == null) {
			selVO.setPaytype(-1);
		}
	}
}
