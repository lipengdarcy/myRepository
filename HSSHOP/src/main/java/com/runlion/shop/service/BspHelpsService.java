package com.runlion.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.dao.BspHelpsMapper;

@Service
public class BspHelpsService {

	@Autowired
	private BspHelpsMapper mapper1;

	@Autowired
	private BspAttributevaluesMapper bspAttributevaluesMapper;

	public void removeHelpsById(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		mapper1.deleteHelpsById(par);
	}

	public void modifyHelpsById(int id, int pid, int displayorder,
			String title, String url, String description) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("id", id);
		par.put("pid", pid);
		par.put("displayorder", displayorder);
		par.put("url", url);
		par.put("title", title);
		par.put("description", description);
		mapper1.updateHelpsById(par);
	}

	public Map<String, Object> queryHelpsById(int newsId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", newsId);
		return mapper1.selectHlepsById(par);
	}

	public void addHelps(int pid, int displayorder, String title, String url,
			String description) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("pid", pid);
		par.put("displayorder", displayorder);
		par.put("title", title);
		par.put("url", url);
		par.put("description", description);
		mapper1.insertHelps(par);
	}

	public int countNews(int newstypeId, String title) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("NEWSTYPE_ID", newstypeId);
		par.put("title", title);
		return mapper1.countHelps(par);
	}

	public List<Map<String, Object>> queryHelpsLimit(int newstypeId,
			String title, int startNum, int limitNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("NEWSTYPE_ID", newstypeId);
		par.put("title", title);
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		return mapper1.selectHelpsLimit(par);
	}

}
