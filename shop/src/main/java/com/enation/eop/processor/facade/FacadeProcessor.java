package com.enation.eop.processor.facade;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.darcy.framework.context.webcontext.ThreadContextHolder;
import org.darcy.framework.util.RequestUtil;

import com.enation.eop.IEopProcessor;
import com.enation.eop.SystemSetting;

/**
 * 前台模板处理器<br>
 * docs目录 由文档解析器处理
 * 其它目录由模板处理器处理
 * @author kingapex
 *2015-3-13
 */
public class FacadeProcessor implements IEopProcessor{
	
	public boolean process()throws IOException, ServletException{
		HttpServletRequest httpRequest =ThreadContextHolder.getHttpRequest();
		HttpServletResponse httpResponse=ThreadContextHolder.getHttpResponse();
		
		String uri =httpRequest.getServletPath();
		ThreadContextHolder.getHttpResponse().setContentType("text/html;charset=UTF-8");
		SsoProcessor processor = new SsoProcessor();
		processor.parse(); 
		
		if(uri.startsWith("/docs")){
			DocsPageParser docsPageParser = new DocsPageParser();
			docsPageParser.parse(uri);
			return true;
		}
		
		if(SystemSetting.getStatic_page_open()==1){
			StaticPageParser staticPageParser = new StaticPageParser();
			boolean result =staticPageParser.parse(uri);
			if(!result){
				FacadePageParser parser = new FacadePageParser();
				return parser.parse(uri);
			}else{
				return true;
			}
		}else{
		
			FacadePageParser parser = new FacadePageParser();
			
			return parser.parse(uri);
		}
		
	}
	
}
