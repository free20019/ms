package com.erxi.ms.annotation;

import java.lang.annotation.*;

/**
 * @author: xianlehuang
 * @Description: 自定义注解，拦截service
 * @date: 2020/3/11 - 11:19
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description() default "";
}
