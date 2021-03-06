package org.darcy.service.resource.impl;

import java.util.List;

import org.darcy.framework.database.IDaoSupport;
import org.darcy.service.resource.IIndexItemManager;
import org.darcy.service.resource.model.IndexItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 首页项管理实现
 * 
 * @author kingapex 2010-10-12下午04:00:31
 */
@Service
public class IndexItemManager   implements IIndexItemManager {

	@Autowired
	private IDaoSupport<IndexItem> daoSupport;
	/**
	 * 添加首页项
	 */
	public void add(IndexItem item) {
		daoSupport.insert("es_index_item", item);
	}

	/**
	 * 读取首页项列表
	 */
	public List<IndexItem> list() {
		String sql = "select * from es_index_item order by sort";
		return daoSupport.queryForList(sql, IndexItem.class);
	}

	public void clean() {
		daoSupport.execute("truncate table es_index_item");
	}

}
