package com.dsw.androidutils.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationColumn {
	/**
	 * 字段名称
	 * @return
	 */
	 String name() default "";
	 /**
	  * 字段类型
	  * @return
	  */
	 String type();
	 /**
	  * 是否为空
	  * @return
	  */
	 boolean isNull() default false;
	 /**
	  * 是否为主键
	  * @return
	  */
	 boolean isPrimaryKey() default false;   
}
