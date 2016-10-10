package com.reptile.common.framework.interceptor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * *
 * 类名称：		SecurityInterceptor.java 
 * 类描述：   		安全使用权限拦截
 * 创建人：		
 * 创建时间：		2016-8-26上午10:37:24 
 * 修改人：		liuxing
 * 修改时间：		2016-8-26上午10:37:24 
 * 修改备注：   		
 * @version
 */
public class SecurityInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	private List<String> excludeUrls;// 不需要拦截的资源

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	/**
	 * 完成页面的render后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	/**
	 * 在调用controller具体方法后拦截
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//		String requestUri = request.getRequestURI();
//		String contextPath = request.getContextPath();
//		String url = requestUri.substring(contextPath.length());
//
//		if (url.indexOf("/baseController/") > -1 || excludeUrls.contains(url)) {// 如果要访问的资源是不需要验证的
//			return true;
//		}
//
//		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("USER_SESSION");
//		if (sessionInfo == null) {// 如果没有登录或登录超时
//			request.setAttribute("msg", "登录已超时，请重新登录，然后再刷新本功能！");
//			request.getRequestDispatcher("/common/error/noSession.jsp").forward(request, response);
//			return false;
//		}
		return true;
	}
}
