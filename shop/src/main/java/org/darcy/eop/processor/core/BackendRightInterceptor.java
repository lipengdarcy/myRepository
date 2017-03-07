package org.darcy.eop.processor.core;

import org.darcy.eop.sdk.context.UserConext;
import org.darcy.framework.context.spring.SpringContextHolder;
import org.darcy.service.auth.IAdminUserManager;
import org.darcy.service.resource.model.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限拦截器
 * @author kingapex
 *
 */
public class BackendRightInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation inv) throws Exception {
		IAdminUserManager adminUserManager = SpringContextHolder.getBean("adminUserManager");
		AdminUser user  = UserConext.getCurrentAdminUser();
		if(user==null){
			return "not_login";
		}
		
		String result = inv.invoke();
		return result;
	}

}
