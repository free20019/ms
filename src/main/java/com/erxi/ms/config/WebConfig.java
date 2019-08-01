package com.erxi.ms.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.erxi.ms.access.AccessInterceptor;

/**
 * AccessInterceptor 拦截器
 * 
 * UserArgumentResolver 方法参数解析器
 * 
 * @author erxi
 * @date : 2018年12月3日 下午8:58:43
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Autowired
	UserArgumentResolver userArgumentResolver;

	@Autowired
	AccessInterceptor accessInterceptor;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//拦截规则：除了login，其他都拦截判断
		registry.addInterceptor(accessInterceptor).addPathPatterns("/**").excludePathPatterns("/common/do_login");
		super.addInterceptors(registry);
	}
}
