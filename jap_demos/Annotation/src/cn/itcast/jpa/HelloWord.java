package cn.itcast.jpa;

import java.lang.String;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//该类运行时生效
public @interface HelloWord {
	public String value() default "hello";
}
