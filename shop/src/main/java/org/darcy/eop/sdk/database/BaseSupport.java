package org.darcy.eop.sdk.database;

import org.apache.log4j.Logger;
import org.darcy.eop.sdk.context.EopContext;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.framework.database.IDBRouter;
import org.darcy.framework.database.IDaoSupport;
import org.darcy.service.resource.model.EopSite;

public abstract class BaseSupport<T> {
	protected final Logger logger = Logger.getLogger(getClass());

	private IDBRouter baseDBRouter;
	protected IDaoSupport<T> baseDaoSupport;
	protected IDaoSupport<T> daoSupport;

	/**
	 * 获取表名
	 * 
	 * @return
	 */
	protected String getTableName(String moude) {
		return baseDBRouter.getTableName(moude);

	}
 

	public IDaoSupport<T> getBaseDaoSupport() {
		return baseDaoSupport;
	}

	public void setBaseDaoSupport(IDaoSupport<T> baseDaoSupport) {
		this.baseDaoSupport = baseDaoSupport;
	}

	public void setDaoSupport(IDaoSupport<T> daoSupport) {
		this.daoSupport = daoSupport;
	}

	public IDBRouter getBaseDBRouter() {
		return baseDBRouter;
	}

	public void setBaseDBRouter(IDBRouter baseDBRouter) {
		this.baseDBRouter = baseDBRouter;
	}
}
