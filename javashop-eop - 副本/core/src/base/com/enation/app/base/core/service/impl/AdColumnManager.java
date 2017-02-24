package com.enation.app.base.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enation.app.base.core.model.AdColumn;
import com.enation.app.base.core.service.IAdColumnManager;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.database.Page;
import com.enation.framework.util.StringUtil;

/**
 * 后台广告位管理接口实现类
 * @author DMRain 2016年2月20日 版本改造
 * @version v2.0 改为spring mvc
 * @since v6.0
 */
@Service
public class AdColumnManager implements IAdColumnManager {

	@Autowired
	private IDaoSupport<AdColumn> daoSupport;
	
	/* (non-Javadoc)
	 * @see com.enation.app.base.core.service.IAdColumnManager#addAdvc(com.enation.app.base.core.model.AdColumn)
	 */
	@Override
	public void addAdvc(AdColumn adColumn) {
		this.daoSupport.insert("es_adcolumn", adColumn);
	}

	/* (non-Javadoc)
	 * @see com.enation.app.base.core.service.IAdColumnManager#delAdcs(java.lang.Integer[])
	 */
	@Override
	public void delAdcs(Integer[] ids) {
		if (ids == null || ids.equals(""))
			return;
		String id_str = StringUtil.arrayToString(ids, ",");
		String sql = "delete from es_adcolumn where acid in (" + id_str + ")";
		this.daoSupport.execute(sql);
	}

	/* (non-Javadoc)
	 * @see com.enation.app.base.core.service.IAdColumnManager#getADcolumnDetail(java.lang.Long)
	 */
	@Override
	public AdColumn getADcolumnDetail(Long acid) {
		AdColumn  adColumn = this.daoSupport.queryForObject("select * from es_adcolumn where acid = ?", AdColumn.class, acid);
		return adColumn;
	}

	/* (non-Javadoc)
	 * @see com.enation.app.base.core.service.IAdColumnManager#listAllAdvPos()
	 */
	@Override
	public List listAllAdvPos() {
		List<AdColumn> list = this.daoSupport.queryForList("select * from es_adcolumn", AdColumn.class);
		return list;
	}

	/* (non-Javadoc)
	 * @see com.enation.app.base.core.service.IAdColumnManager#pageAdvPos(int, int)
	 */
	@Override
	public Page pageAdvPos(int page, int pageSize) {
		String sql = "select * from es_adcolumn order by acid desc";
		Page rpage = this.daoSupport.queryForPage(sql, page, pageSize);
		return rpage;
	}

	/* (non-Javadoc)
	 * @see com.enation.app.base.core.service.IAdColumnManager#updateAdvc(com.enation.app.base.core.model.AdColumn)
	 */
	@Override
	public void updateAdvc(AdColumn adColumn) {
		this.daoSupport.update("es_adcolumn", adColumn, "acid = " + adColumn.getAcid());
	}

}
