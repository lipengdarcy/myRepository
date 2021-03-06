package org.darcy.eop.processor.back;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.darcy.eop.IEopProcessor;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.eop.sdk.context.UserConext;
import org.darcy.framework.context.spring.SpringContextHolder;
import org.darcy.framework.context.webcontext.ThreadContextHolder;
import org.darcy.framework.util.RequestUtil;
import org.darcy.service.auth.IAdminUserManager;
import org.darcy.service.resource.IAdminThemeManager;
import org.darcy.service.resource.model.AdminTheme;
import org.darcy.service.resource.model.AdminUser;
import org.darcy.service.resource.model.EopSite;
/**
 * 后台模板处理器<br>
 * 负责权限的判断及后台模板的解析<br>
 * @author kingapex
 *2015-3-13
 */
public class BackendProcessor implements IEopProcessor {
	
	
	public boolean process() throws IOException{
		
		IAdminUserManager adminUserManager = SpringContextHolder.getBean("adminUserManager");
		AdminUser adminUser  = UserConext.getCurrentAdminUser();
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		HttpServletResponse httpResponse=  ThreadContextHolder.getHttpResponse();
		
		String ctx = httpRequest.getContextPath();
		
		
		if("/".equals(ctx)){
			ctx="";
		}
		String uri = httpRequest.getServletPath();
		
		if( uri.startsWith("/core/admin/ueditor/get-config-json.do") ){
			return false;
		}
		if( uri.startsWith("/admin/login.do")){ //登录界面放过
			return false;
		}
		
		if(uri.startsWith("/core/admin/admin-user/login.do")){//登录验证放过
			return false;
		}
		 
		
		String redirectUrl ="";
		if(uri.startsWith("/admin") ){
			if(adminUser==null){
				redirectUrl=(ctx+"/admin/login.do");
			}else{ //已经登录过了
				if(uri.equals("/admin") || uri.equals("/admin/") ){
					httpResponse.sendRedirect(ctx+"/admin/main.do");
					return true;
				}
				if(!uri.startsWith("/admin/")){//如果不是访问的登录界，跳到登录界面
					httpResponse.sendRedirect(ctx+"/admin/main.do");
					return true;
				}
				return false;
			}
			httpResponse.sendRedirect(redirectUrl);
			return true;
		}else{ // 访问应用下的功能
			
			if(adminUser==null){//超时了
				String referer = RequestUtil.getRequestUrl(httpRequest);
				httpResponse.sendRedirect(ctx+"/admin/login.do?timeout=yes&referer="+referer);
				return true;
			}else{
				EopSite site=EopSite.getInstance();
				String product_type = EopSetting.PRODUCT;
				httpRequest.setAttribute("site",site);
				httpRequest.setAttribute("ctx",ctx);
				httpRequest.setAttribute("product_type",product_type);
				httpRequest.setAttribute("theme",getAdminTheme(site.getAdminthemeid() ));
				
			}
		}
		
		return false;
	}
	
	private String getAdminTheme(int themeid){
		
		IAdminThemeManager adminThemeManager =SpringContextHolder.getBean("adminThemeManager");
		// 读取后台使用的模板
		AdminTheme theTheme = adminThemeManager.get(themeid);
		String theme = "default";
		if (theTheme != null) {
			theme = theTheme.getPath();
		}
		return theme;
	}
	
}
