package org.darcy.service.solution;

import org.darcy.framework.database.IDaoSupport;
import org.darcy.framework.database.Page;
import org.darcy.service.resource.model.EopProduct;

/**
 * 安装者管理
 * @author kingapex
 * 2010-6-24下午05:27:22
 */
public class InstallerManager {
	private IDaoSupport<EopProduct> daoSupport;
	
	public void add(Installer installer){
		String sql ="select count(0) from eop_installer where domain=?";
		int count = this.daoSupport.queryForInt(sql, installer.getDomain());
		if(count==0)
			this.daoSupport.insert("eop_installer", installer);
	}
	
	public void add1(Installer installer){
		this.daoSupport.insert("eop_installer", installer);
	}
	
	public Page list(int pageNo,int pageSize){
		
		return this.daoSupport.queryForPage("select * from eop_installer order by installtime desc",pageNo, pageSize);
		
	}
	
	public IDaoSupport<EopProduct> getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport<EopProduct> daoSupport) {
		this.daoSupport = daoSupport;
	}
	
}