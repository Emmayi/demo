package cn.edu.bupt.demo.annotation;

import java.lang.annotation.*;

/**
 * Created by zyf on 2019/5/21.
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

    //String[] user() default {};

    String[] authorities() default {};

    String[] roles() default {};

}
