package org.darcy.service.impl;

import org.darcy.app.base.core.model.Help;
import org.darcy.eop.sdk.database.BaseSupport;
import org.darcy.service.IHelpManager;

/**
 * 帮助管理
 * @author kingapex
 * 2010-10-18上午01:37:12
 */
public class HelpManager extends BaseSupport<Help> implements IHelpManager {

	public Help get(String helpid) {
		String sql ="select * from es_help_1_1 where helpid=?";
		return this.daoSupport.queryForObject(sql, Help.class, helpid);
	}
	
}
