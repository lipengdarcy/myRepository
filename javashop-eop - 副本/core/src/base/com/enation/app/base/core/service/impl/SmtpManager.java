package com.enation.app.base.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enation.app.base.core.model.Smtp;
import com.enation.app.base.core.service.ISmtpManager;
import com.enation.eop.sdk.database.BaseSupport;
import com.enation.framework.database.IDaoSupport;
import com.enation.framework.util.StringUtil;

/**
 * smtp管理
 * @author kingapex
 * @date 2011-11-1 下午12:10:30 
 * @version V1.0
 */
@SuppressWarnings("unchecked")
@Service("smtpDbManager")
public class SmtpManager extends BaseSupport<Smtp> implements ISmtpManager {

	
	@Autowired
	private IDaoSupport daoSupport;
	
	/*
	 * (non-Javadoc)
	 * @see com.enation.app.base.core.service.ISmtpManager#add(com.enation.app.base.core.model.Smtp)
	 */
	@Override
	public void add(Smtp smtp) {
		this.daoSupport.insert("es_smtp", smtp);
	}

	/*
	 * (non-Javadoc)
	 * @see com.enation.app.base.core.service.ISmtpManager#edit(com.enation.app.base.core.model.Smtp)
	 */
	@Override
	public void edit(Smtp smtp) {
		this.daoSupport.update("es_smtp", smtp,"id="+smtp.getId());
	}

	/*
	 * (non-Javadoc)
	 * @see com.enation.app.base.core.service.ISmtpManager#delete(java.lang.Integer[])
	 */
	@Override
	public void delete(Integer[] idAr) {
		
		if(idAr==null || idAr.length==0) return;
		String idstr = StringUtil.arrayToString(idAr, ",");
		
		this.daoSupport.execute("delete from es_smtp where id in("+idstr+")");
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.enation.app.base.core.service.ISmtpManager#list()
	 */
	
	@Override
	public List<Smtp> list() {
		return this.daoSupport.queryForList("select * from es_smtp", Smtp.class);
	}

	@Override
	public void sendOneMail(Smtp currSmtp) {
		this.daoSupport.update("es_smtp", currSmtp, "id="+currSmtp.getId());
	}

	
	/*
	 * (non-Javadoc)
	 * @see com.enation.app.base.core.service.ISmtpManager#get(int)
	 */
	@Override
	public Smtp get(int id){
		return (Smtp) this.daoSupport.queryForObject("select * from es_smtp where id=?", Smtp.class, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.enation.app.base.core.service.ISmtpManager#getCurrentSmtp()
	 */
	@Override
	public Smtp getCurrentSmtp() {
		// TODO Auto-generated method stub
		return null;
	}
}
