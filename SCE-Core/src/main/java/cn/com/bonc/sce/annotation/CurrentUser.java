package cn.com.bonc.sce.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: wf
 * @description: 获取用户id注解，本注解属于扩展内容-》 -与CurrentUserId不同的是，本注解在ticket校验失败时返回的是 "" 作为userId
 * @create: 2019年4月28日14:23:32
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
