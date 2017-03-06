package org.darcy.controller.api;

import java.util.Date;

import org.apache.log4j.Logger;
import org.darcy.framework.util.DateUtil;
import org.darcy.framework.util.FileUtil;
import org.darcy.framework.util.JsonMessageUtil;
import org.darcy.framework.util.JsonResultUtil;
import org.darcy.framework.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@Scope("prototype")
@RequestMapping("/api/base/tagTestCreate")
public class TagTestCreateController  {
	
	
	public Object execute(String content,String params,String filename){
		try{
			if(StringUtil.isEmpty(filename)){
				filename = createFileName()+".html";
			}
			String app_apth = StringUtil.getRootPath();
			String filepath=app_apth+"/docs/tags/runtime/"+filename;
			
			if(content==null){
				content="";
			}
			FileUtil.write(filepath, content);
			if(params==null){
				params="";
			}
			return JsonMessageUtil.getStringJson("url", filename);
		}catch(Throwable e){
			Logger logger = Logger.getLogger(getClass());
			logger.error("生成标签测试页面出错",e);
			return JsonResultUtil.getErrorJson("生成标签测试页面出错"+e.getMessage());
		}
	}
	
	private String createFileName(){
		String filename = DateUtil.toString(new Date(), "yyyyMMddHHmmss");
		
		return filename+StringUtil.getRandStr(4);
	} 
	
	
}
