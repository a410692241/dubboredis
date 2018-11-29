package com.wei.shopcart.aop;

import java.lang.annotation.*;

/**@Document表示该注解可通过javadoc生成api文档的时候自动写入到文件
 * @Retention表示注解的作用范围
 *      - RUNTIME:可以作用到运行时,反射可获得这个注解的范围,和反射配合使用
 *      - CLASS:只在.class中,编译消失
 *      - SOURCE:作用范围只在源码中,编译之后消失
 * @Target表示注解的标记范围
 *      - ANNOTATION_TYPE 注解上
 *      - CONSTRUCTOR构造器
 *      - FIELD属性
 *      - LOCAL_VARIABLE局部变量
 *      - METHOD方法
 *      - PACKAGE包
 *      - PARAMETER方法参数
 *      - ElementType.TYPE类接口
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//登录验证注解
public @interface IsLogin {
    /* 类型 参数名() @IsLogin(abc = "XXXX")
     * 类型[] 参数名() @IsLogin(arr={"1","2"})
     * 如果注解参数名是value 可以不写value @IsLogin("")或者@IsLogin,这样的注解一般用于不需要提供参数的注解,比如这里
     */
//    String abc();
//    String[] arr();
    String value() default "";
}
