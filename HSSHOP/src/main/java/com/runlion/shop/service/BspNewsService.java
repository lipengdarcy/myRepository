package com.runlion.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.dao.BspNewsMapper;
import com.runlion.shop.entity.BspNews;

@Service
public class BspNewsService {
	@Autowired
	private BspNewsMapper mapper;

	@Autowired
	private BspAttributevaluesMapper bspAttributevaluesMapper;

	public List<Map<String, Object>> queryNewsByType(int newstypeId,
			int isHome, int limit) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("NEWSTYPE_ID", newstypeId);
		par.put("IS_HOME", isHome);
		par.put("LIMIT_NUM", limit);
		return mapper.selectNewsByType(par);
	}

	public void removeNewsById(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		mapper.deleteNewsById(par);
	}

	public void modifyNewsById(int id, int newstypeId, int isShow, int isTop,
			int isHome, int order, String title, String body) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		par.put("NEWSTYPE_ID", newstypeId);
		par.put("IS_SHOW", isShow);
		par.put("IS_TOP", isTop);
		par.put("IS_HOME", isHome);
		par.put("DISPLAYORDER", order);
		par.put("TITLE", title);
		par.put("BODY", body);
		mapper.updateNewsById(par);
	}

	public Map<String, Object> queryNewsById(int newsId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", newsId);
		return mapper.selectNewsById(par);
	}

	public void addNews(int newstypeId, int isShow, int isTop, int isHome,
			int displayorder, String title, String url, String body) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("NEWSTYPE_ID", newstypeId);
		par.put("IS_SHOW", isShow);
		par.put("IS_TOP", isTop);
		par.put("IS_HOME", isHome);
		par.put("DISPLAYORDER", displayorder);
		par.put("TITLE", title);
		par.put("URL", url);
		par.put("BODY", body);
		mapper.insertNews(par);
	}

	public int countNews(int newstypeId) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("NEWSTYPE_ID", newstypeId);
		return mapper.countNews(par);
	}

	public List<Map<String, Object>> queryNewsLimit(int newstypeId,
			int startNum, int limitNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("NEWSTYPE_ID", newstypeId);
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		return mapper.selectNewsLimit(par);
	}

	public BspNews selectByPrimaryKey(int pid) {
		BspNews entity = mapper.selectByPrimaryKey(pid);
		return entity;
	}

}
