package org.darcy.controller.admin;

import java.util.List;

import org.darcy.eop.sdk.context.EopContext;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.framework.action.WWAction;
import org.darcy.service.resource.IAdminThemeManager;
import org.darcy.service.resource.ISiteManager;
import org.darcy.service.resource.model.AdminTheme;
import org.darcy.service.resource.model.EopSite;

/**
 * 站点主题管理
 * 
 */
public class SiteAdminThemeAction extends WWAction {

	private IAdminThemeManager adminThemeManager;
	private ISiteManager siteManager;
	
	private List<AdminTheme> listTheme;
	private AdminTheme adminTheme;
	private EopSite eopSite;
	private String previewpath;
	private String previewBasePath;
	private Integer themeid;
	
	
	
	
	public String execute() throws Exception {
		String contextPath = this.getRequest().getContextPath();
		previewBasePath =  contextPath+ "/adminthemes/";
		adminTheme = adminThemeManager.get( EopSite.getInstance().getAdminthemeid());
		listTheme = adminThemeManager.list();
		previewpath = previewBasePath + adminTheme.getPath() + "/preview.png";
		return SUCCESS;
	}
	
	public String change()throws Exception {
	//	siteManager.changeAdminTheme(themeid);
		return this.execute();
	}

	public EopSite getEopSite() {
		return eopSite;
	}

	public void setEopSite(EopSite eopSite) {
		this.eopSite = eopSite;
	}

	public ISiteManager getSiteManager() {
		return siteManager;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public String getPreviewpath() {
		return previewpath;
	}

	public void setPreviewpath(String previewpath) {
		this.previewpath = previewpath;
	}

	public String getPreviewBasePath() {
		return previewBasePath;
	}

	public void setPreviewBasePath(String previewBasePath) {
		this.previewBasePath = previewBasePath;
	}

	public Integer getThemeid() {
		return themeid;
	}

	public void setThemeid(Integer themeid) {
		this.themeid = themeid;
	}

	public List<AdminTheme> getListTheme() {
		return listTheme;
	}

	public void setListTheme(List<AdminTheme> listTheme) {
		this.listTheme = listTheme;
	}

	public AdminTheme getAdminTheme() {
		return adminTheme;
	}

	public void setAdminTheme(AdminTheme adminTheme) {
		this.adminTheme = adminTheme;
	}

	public IAdminThemeManager getAdminThemeManager() {
		return adminThemeManager;
	}

	public void setAdminThemeManager(IAdminThemeManager adminThemeManager) {
		this.adminThemeManager = adminThemeManager;
	}

}
