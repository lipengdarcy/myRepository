package org.darcy.app.base.tag;

import java.util.Map;

import org.darcy.framework.taglib.BaseFreeMarkerTag;
import org.darcy.framework.util.FileUtil;
import org.darcy.framework.util.StringUtil;

import freemarker.template.TemplateModelException;

public class DemoFileReadTag extends BaseFreeMarkerTag {

	@Override
	protected Object exec(Map params) throws TemplateModelException {
		 String filename=(String)params.get("filename");
		 if(filename==null) return "文件名不能为空";
		 String app_apth = StringUtil.getRootPath();
		 String filePath = app_apth+"/docs/tags/demo/"+filename;
		 String content =FileUtil.read(filePath, "UTF-8");
		return content;
	}

}
