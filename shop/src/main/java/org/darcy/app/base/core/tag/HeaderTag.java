package org.darcy.app.base.core.tag;

import java.util.HashMap;
import java.util.Map;

import org.darcy.eop.sdk.HeaderConstants;
import org.darcy.eop.sdk.context.EopContext;
import org.darcy.framework.context.webcontext.ThreadContextHolder;
import org.darcy.framework.taglib.BaseFreeMarkerTag;
import org.darcy.framework.util.StringUtil;
import org.darcy.service.resource.model.EopSite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import freemarker.template.TemplateModelException;
/**
 * 网站头标签
 * @author lina
 * 2014-5-27
 */
@Component
@Scope("prototype")
public class HeaderTag extends BaseFreeMarkerTag {

	@Override
	protected Object exec(Map params) throws TemplateModelException {
		Map siteHeader = new HashMap();
		String ctx = ThreadContextHolder.getHttpRequest().getContextPath();
		EopSite site  =EopSite.getInstance();
		siteHeader.put("title", StringUtil.isEmpty(site.getTitle()) ? site.getSitename() : site.getTitle());
		siteHeader.put("keywords", site.getKeywords());
		siteHeader.put("description", site.getDescript());
		siteHeader.put("ctx",ctx);
		return siteHeader;
	}

}
