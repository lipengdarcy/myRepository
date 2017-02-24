package com.enation.app.base.component.plugin;

import java.util.List;

import org.darcy.service.ISitemapManager;
import org.darcy.service.resource.IThemeUriManager;
import org.darcy.service.resource.model.ThemeUri;
import org.springframework.stereotype.Component;

import com.enation.app.base.core.model.SiteMapUrl;
import com.enation.app.base.core.plugin.IRecreateMapEvent;
import com.enation.framework.plugin.AutoRegisterPlugin;
import com.enation.framework.util.DateUtil;

@Component
public class ThemeUriSitemapPlugin extends AutoRegisterPlugin implements
		IRecreateMapEvent {
	
	private ISitemapManager sitemapManager;
	private IThemeUriManager themeUriManager;

	public void register() {

	}

	public void onRecreateMap() {
		List<ThemeUri> list = this.themeUriManager.list(null);
		for(ThemeUri uri:list){
			if(uri.getSitemaptype()==1){
				SiteMapUrl url = new SiteMapUrl();
				url.setLoc(uri.getUri());
				url.setLastmod(DateUtil.getDateline());
				this.sitemapManager.addUrl(url);
			}
		}

	}

	public String getAuthor() {
		return "lzf";
	}

	public String getId() {
		return "themeUriSitemap";
	}

	public String getName() {
		return "地址转向定义重建站点地图";
	}

	public String getType() {
		return "themeUriSitemap";
	}

	public String getVersion() {
		return "v2.1.5";
	}

	public void perform(Object... params) {

	}

	public ISitemapManager getSitemapManager() {
		return sitemapManager;
	}

	public void setSitemapManager(ISitemapManager sitemapManager) {
		this.sitemapManager = sitemapManager;
	}

	public IThemeUriManager getThemeUriManager() {
		return themeUriManager;
	}

	public void setThemeUriManager(IThemeUriManager themeUriManager) {
		this.themeUriManager = themeUriManager;
	}

}
