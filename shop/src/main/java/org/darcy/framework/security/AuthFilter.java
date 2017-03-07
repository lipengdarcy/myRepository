package org.darcy.framework.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.darcy.app.base.core.model.AuthAction;
import org.darcy.eop.processor.core.HttpHeaderConstants;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.eop.sdk.context.UserConext;
import org.darcy.framework.context.webcontext.ThreadContextHolder;
import org.darcy.service.resource.IMenuManager;
import org.darcy.service.resource.model.AdminUser;
import org.darcy.service.resource.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义权限过滤器:拦截菜单表中定义的所有url并且没有授权给当前用户的
 * 
 * @author tito
 *
 */
public class AuthFilter extends AccessControlFilter {
	public static final String CURRENT_ADMINUSER_MENU_KEY = "CURRENT_ADMINUSER_MENU_KEY";

	@Autowired
	private IMenuManager menuManager;

	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println("=========AuthFilter========" + httpRequest.getServletPath());
	
		
//		String[] mapArr = (String[]) mappedValue;  
//        if (mapArr == null || mapArr.length == 0) {  
//            return true;  
//        }  

		if(EopSetting.INSTALL_LOCK == "yes")
		{
			
			AdminUser user = UserConext.getCurrentAdminUser();
			if (user!=null &&user.getFounder() == 1) {
				return true;
			}
			
			
			List<Menu> allMenus = menuManager.getMenuList();
			Map<String, Menu> map = new HashMap<String, Menu>();
			if (CollectionUtils.isNotEmpty(allMenus)) {
				for (Menu menu : allMenus) {
					if(Menu.MENU_TYPE_SYS != menu.getMenutype() && StringUtils.isNoneBlank(menu.getUrl()))
					{
						map.put(menu.getUrl().trim(), menu);
					}
				}
			}
			
			
			if("/core/admin/themeUri/list.do".equalsIgnoreCase(httpRequest.getServletPath()))
			{
				System.out.println();
			}
			if(map.containsKey(httpRequest.getServletPath()))
			{
				Menu m = map.get(httpRequest.getServletPath());
				List<AuthAction> authActions = user.getAuthList();
				if(CollectionUtils.isNotEmpty(authActions))
				{
					for (AuthAction authAction : authActions) {
						String arth[] = authAction.getObjvalue().split(",");
						
						//authAction 的objectvalue中怕偶有空格。。。
						for(String authStr : arth)
						{
							
							if(authStr.trim().equals(m.getUrl()))
							{
								return true;
							}
						}
					}
				}
				return false;
			}
		} 
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		
		//TODO 以下需要进一步优化，现在只是简单返回401
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = ThreadContextHolder.getHttpResponse();
		if(httpResponse!=null){
		 
			httpResponse.setStatus(HttpHeaderConstants.status_401);
		}
		
		
		//是否异步请求
		 if (!(httpRequest.getHeader("accept").indexOf("application/json") > -1 || (httpRequest.getHeader("X-Requested-With")!= null && httpRequest  
                .getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			 String ctx = httpRequest.getContextPath();
				httpResponse.sendRedirect(ctx +"/admin/401.do");  
			 return false;  
		 }else{
			 try {  
                PrintWriter writer = response.getWriter();  
                writer.write("ajax 401 没有访问权限");  
                writer.flush();  
                
            } catch (IOException e) {  
            }  
            return false;  
		 }
		
	}
}
