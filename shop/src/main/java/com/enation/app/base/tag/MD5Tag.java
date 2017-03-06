package com.enation.app.base.tag;

import java.util.Map;

import org.darcy.framework.taglib.BaseFreeMarkerTag;
import org.darcy.framework.util.StringUtil;

import freemarker.template.TemplateModelException;


public class MD5Tag extends BaseFreeMarkerTag {

	@Override
	protected Object exec(Map params) throws TemplateModelException {
		String str =(String)params.get("str");
		if(str==null) throw new TemplateModelException("必须传入str参数");
		str = StringUtil.md5(str);
		return str;
	}

}
