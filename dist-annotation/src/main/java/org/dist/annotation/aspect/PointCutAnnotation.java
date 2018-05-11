package org.dist.annotation.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yaowf
 * @createDate 2018-05-08 10:16
 */


/**
 * @Target({ElementType.CONSTRUCTOR,ElementType.FIELD,ElementType.METHOD})
 * // Target 注解的作用域   CONSTRUCTOR 构造方法声明，FIELD 字段声明，LOCAL_VARIABLE 局部变量声明 ，METHOD 方法声明，PACKAGE 包声明，PARAMETER 参数声明，TYPE 类接口。
 * @Retention(RetentionPolicy.RUNTIME)
 * //Retention 生命周期 SOURCE 只在源码显示，编译时会丢弃，CLASS 编译时会记录到class中，运行时忽略，RUNTIME 运行时存在，可以通过反射读取。
 * @Inherited
 * //Inherited 允许子类继承
 * @Documented
 * //Documented 生成javadoc的时候包含注解
 *
 *
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PointCutAnnotation {
    String value() default  "";
}
