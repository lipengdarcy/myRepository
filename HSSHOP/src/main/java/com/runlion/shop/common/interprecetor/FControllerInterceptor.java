package com.runlion.shop.common.interprecetor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.runlion.shop.controller.BaseController;
import com.runlion.shop.entity.common.StatusHtml;

/**
 * @Description TODO
 * @author 赵威
 * @date 2014-9-23 下午4:03:34
 * @version V1.0
 */
@Component
public class FControllerInterceptor implements HandlerInterceptor {
	private static Logger loggererror = Logger.getLogger("ErrorLogger");
	private static Logger loggerinfo = Logger.getLogger("InfoLogger");

	/*
	 * // 利用正则映射到需要拦截的路径
	 * 
	 * @Value("${cfg.client.ips}") private String clientIPs;
	 * 
	 * // 登陆超时和状态
	 * 
	 * @Value("${msg.admin.timeout}") private String adminTimeOut;
	 * 
	 * @Value("${msg.admin.currentuser}") private String admincurrentuser;
	 */

	/**
	 * 在业务处理器处理请求之前被调用 如果返回false 从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true 执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 从最后一个拦截器往回执行所有的postHandle() 接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String ip = request.getRemoteHost().toString();
		loggerinfo.info("==========[ControllerInterceptor] 1:preHandle...");
		if (!request.getRequestURL().toString().contains("login")) {

			HttpSession httpSession = request.getSession();
			Integer uid = (Integer) httpSession
					.getAttribute(BaseController.ACCOUNT_SESSION_UID);
			// 如果session中没有user对象
			if (null == uid || uid <= 0) {
				String requestedWith = request.getHeader("x-requested-with");
				// ajax请求
				if (requestedWith != null
						&& "XMLHttpRequest".equals(requestedWith)) {
					StatusHtml statusHtml = new StatusHtml();
					statusHtml.setStatusCode("301");
					statusHtml.setMessage("登录超时！");
					statusHtml.setCallbackType("closeCurrent");
					response.setHeader("session-status", "timeout");
					response.getWriter().print(JSON.toJSONString(statusHtml));
				} else {
					// 普通页面请求
					response.sendRedirect(request.getContextPath()
							+ "/account/login.do");
				}
				return false;
			}
			return true;
			/* } */
		} else {
			return true;
		}

	}

	// 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		loggerinfo.info("==========[ControllerInterceptor] 2:postHandle...");
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		loggerinfo
				.info("==========[ControllerInterceptor] 3:afterCompletion...");
	}
}
