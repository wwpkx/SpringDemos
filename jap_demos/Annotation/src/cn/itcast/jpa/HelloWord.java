package cn.itcast.jpa;

import java.lang.String;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)//��������ʱ��Ч
public @interface HelloWord {
	public String value() default "hello";
}
