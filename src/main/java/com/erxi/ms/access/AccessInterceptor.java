package com.erxi.ms.access;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.erxi.ms.domain.User;
import com.erxi.ms.redis.AccessKey;
import com.erxi.ms.redis.RedisService;
import com.erxi.ms.result.CodeMsg;
import com.erxi.ms.service.CommonService;

/**
 * 拦截器 同意处理user 接口限流处理
 * 
 * @author erxi
 * @date : 2018年7月3日 上午11:43:31
 */

@Service
public class AccessInterceptor extends HandlerInterceptorAdapter  {
	
	private static Logger log = LoggerFactory.getLogger(AccessInterceptor.class);
	
	private final String loginUrl="/login.html";
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	CommonService commonService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			User user = getUser(request, response);
			if(user == null){
				redirect(request, response);
			}
			UserContext.setUser(user);

			HandlerMethod hm = (HandlerMethod) handler;
			AccessLimit al = hm.getMethodAnnotation(AccessLimit.class);
			if (al == null) {
				return true;
			}

			int seconds = al.seconds();
			int maxCount = al.maxCount();
			boolean needLogin = al.needLogin();
			String key = request.getRequestURI();

			if (needLogin) {
				if (user == null) {
					render(response, CodeMsg.SESSION_ERROR);
					return false;
				}
				key += "_" + user.getUsername();
			} else {
				// do nothing
			}
			// 查询访问次数
			AccessKey ak = AccessKey.withExpire(seconds);
			Integer count = redisService.get(ak, key, Integer.class);
			if (count == null) {
				redisService.set(ak, key, 1);
			} else if (count < maxCount) {
				redisService.incr(ak, key);
			} else {
				//频繁刷接口提醒
				render(response, CodeMsg.ACCESS_LIMIT_REACHED);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 对于请求是ajax请求重定向问题的处理方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void redirect(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//获取当前请求的路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"  + request.getServerPort()+request.getContextPath();
        //如果request.getHeader("X-Requested-With") 返回的是"XMLHttpRequest"说明就是ajax请求，需要特殊处理
        if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            response.setHeader("CONTENTPATH", basePath+"/login.html");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else{
            response.sendRedirect(basePath + "/login.html");
        }
	}

	/**
	 * 返回response 频繁刷接口提醒
	 * @param response
	 * @param cm
	 * @throws Exception
	 */
	private void render(HttpServletResponse response, CodeMsg cm)
			throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		String s = JSON.toJSONString(cm);
		out.write(s.getBytes("UTF-8"));
		out.flush();
		out.close();
	}

	/**
	 * 获取user
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	private User getUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String paramToken = request.getParameter(CommonService.COOKIE_NAME_TOKEN);
		String cookieToken = getCookieValue(request,CommonService.COOKIE_NAME_TOKEN);
		if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken) ? cookieToken: paramToken;
		return commonService.getByToken(response, token);

	}
	
	private String getCookieValue(HttpServletRequest request,
			String cookieNameToken) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length <= 0) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieNameToken)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
