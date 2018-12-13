package cn.com.bonc.sce.resolvers;

import cn.com.bonc.sce.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author: tlz
 * @description:
 * @create: 2018-12-13 20:23
 */

/**
 *
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数有CurrentUser注解则支持
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //从请求中取出 header中的authorization信息

        String authorization =  webRequest.getHeader("authorization");

        //通过 token 获取用户信息，可选择 redis查询或者 数据库查询  并返回 user_id

        return null;
    }
}
