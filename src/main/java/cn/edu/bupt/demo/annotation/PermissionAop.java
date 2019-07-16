package cn.edu.bupt.demo.annotation;

import java.lang.annotation.*;

/**
 * Created by zyf on 2019/5/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAop {

    String value() default "";

}
