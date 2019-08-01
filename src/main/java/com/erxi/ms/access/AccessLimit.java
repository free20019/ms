package com.erxi.ms.access;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 限流接口
 * 限制时间段内的访问次数
 * 防止同一接口重复访问造成数据库的压力
 * 限流5秒 最大连接5次 需要登录
 * @AccessLimit(seconds = 5, maxCount = 5, needLogin = true)
 * @author erxi
 * @date : 2018年11月26日 下午2:30:26
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
	int seconds();

	int maxCount();

	boolean needLogin() default true;
}
