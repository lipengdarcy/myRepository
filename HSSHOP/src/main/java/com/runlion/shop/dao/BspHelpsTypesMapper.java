package com.runlion.shop.dao;

import java.util.List;
import java.util.Map;

public interface BspHelpsTypesMapper {
	public List<Map<String, Object>> selecthelpstype();

	public int count();

	public void delete(Map<String, Object> par);

	public Map<String, Object> selectbyid(int id);

	public void update(Map<String, Object> par);

	public void insert(Map<String, Object> par);
}