package com.runlion.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runlion.shop.dao.BspAttributevaluesMapper;
import com.runlion.shop.dao.BspNewstypesMapper;
import com.runlion.shop.entity.BspNewstypes;
import com.runlion.shop.entity.BspNewstypesExample;

@Service
public class BspNewstypesService {
	@Autowired
	private BspNewstypesMapper mapper;

	@Autowired
	private BspAttributevaluesMapper bspAttributevaluesMapper;

	public void removeNewstypeById(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		mapper.deleteNewstypeById(par);
	}

	public void modifyNewstypeById(int id, String name, int order) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		par.put("NAME", name);
		par.put("DISPLAYORDER", order);
		mapper.updateNewstypeById(par);
	}

	public Map<String, Object> queryNewstypeById(int id) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("ID", id);
		return mapper.selectNewstypeById(par);
	}

	public void addNewstype(String name, int order) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("NAME", name);
		par.put("DISPLAYORDER", order);
		mapper.insertNewstype(par);
	}

	public int countNewstype() {
		Map<String, Object> par = new HashMap<String, Object>();
		return mapper.countNewstype(par);
	}

	public List<Map<String, Object>> queryNewstypeLimit(int startNum,
			int limitNum) {
		Map<String, Object> par = new HashMap<String, Object>();
		par.put("START_NUM", startNum);
		par.put("LIMIT_NUM", limitNum);
		return mapper.selectNewstypeLimit(par);
	}

	public BspNewstypes selectByPrimaryKey(int pid) {
		BspNewstypes entity = mapper.selectByPrimaryKey(pid);
		return entity;
	}

	public void addNewstype(BspNewstypes newstype) {
		int r = mapper.insertSelective(newstype);

	}

	public List<BspNewstypes> queryNewstypeList() {
		BspNewstypesExample e = new BspNewstypesExample();
		e.setOrderByClause("displayorder");
		List<BspNewstypes> list = mapper.selectByExample(e);
		return list;
	}

}
