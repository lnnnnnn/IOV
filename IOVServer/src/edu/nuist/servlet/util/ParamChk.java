package edu.nuist.servlet.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD )
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamChk {
	//paramchk: string: name=type:must; name=type:must; name=type:must;
	String value() default "";
}
