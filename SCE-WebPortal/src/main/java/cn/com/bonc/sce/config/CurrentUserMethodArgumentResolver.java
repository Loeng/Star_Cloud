package cn.com.bonc.sce.config;

import cn.com.bonc.sce.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter( MethodParameter parameter ) {
        //如果参数有CurrentUser注解则支持
        return parameter.hasParameterAnnotation( CurrentUser.class );
    }

    @Override
    public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory ) throws Exception {

        String auth = webRequest.getHeader( "authentication" );
        System.out.println( auth );
        return auth;
    }
}
