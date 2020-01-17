package com.erxi.ms.config;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.erxi.ms.access.UserContext;
import com.erxi.ms.domain.User;

/**
 * 统一处理修改token的获取方式 通过mvc参数是否有 User对象
 * 
 * @author erxi
 * @date : 2018年6月19日 上午9:52:48
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		return clazz == User.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		return UserContext.getUser();
	}
}
