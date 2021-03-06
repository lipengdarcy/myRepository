package org.darcy.eop.processor.core;

import javax.servlet.http.HttpServletRequest;

import org.darcy.app.base.core.model.Member;
import org.darcy.eop.sdk.context.EopSetting;
import org.darcy.eop.sdk.context.UserConext;
import org.darcy.framework.context.webcontext.ThreadContextHolder;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;


public class ApiRightInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2188748169858714415L;
	private String json;

	@Override
	protected String doIntercept(ActionInvocation inv) throws Exception {
		if(EopSetting.PRODUCT.equals("b2c")){
			
			Member member =  UserConext.getCurrentMember();
			if(member==null){
				HttpServletRequest request = ThreadContextHolder.getHttpRequest();
				request.setAttribute("json", "{\"result\":0,\"message\":\"服务器连接超时，请刷新页面！\"}");
				return "json_message";
			}
			
		}else if(EopSetting.PRODUCT.equals("b2b2c")){
			
			Object obj = ThreadContextHolder.getSessionContext().getAttribute("curr_store_member");
			if(obj==null){
				HttpServletRequest request = ThreadContextHolder.getHttpRequest();
				request.setAttribute("json", "{\"result\":0,\"message\":\"服务器连接超时，请刷新页面！\"}"); 
				return "json_message";
			}
		}
		
		String result = inv.invoke();
		return result;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
