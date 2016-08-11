package com.ink.user.mongo.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * mongo查询条件灵活处理用的的注解
 * @author yangchen
 * @date 2016年1月30日 下午5:30:37
 */
@Target( { ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MongoQueryKey {
	String value() default "";
	String acction() default "equal";
}