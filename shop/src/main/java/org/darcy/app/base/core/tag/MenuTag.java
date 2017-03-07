package org.darcy.app.base.core.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.darcy.framework.taglib.BaseFreeMarkerTag;
import org.darcy.framework.util.StringUtil;
import org.darcy.service.ISiteMenuManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import freemarker.template.TemplateModelException;
/**
 * 菜单标签
 * @author lina
 * 2013-12-20
 */
@Component
@Scope("prototype")
public class MenuTag extends BaseFreeMarkerTag {
	private ISiteMenuManager siteMenuManager;
	/**
	 * @param parentid 上一级菜单id 选填  默认为0
	 * @return list 菜单列表
	 */
	@Override
	protected Object exec(Map params) throws TemplateModelException {
		String parentid=(String) params.get("parentid");//上一级菜单id
		if(parentid==null)
			parentid = "0";
		List menuList  =siteMenuManager.list(Integer.valueOf(parentid));
		return menuList;
	}
	public ISiteMenuManager getSiteMenuManager() {
		return siteMenuManager;
	}
	public void setSiteMenuManager(ISiteMenuManager siteMenuManager) {
		this.siteMenuManager = siteMenuManager;
	}

}
