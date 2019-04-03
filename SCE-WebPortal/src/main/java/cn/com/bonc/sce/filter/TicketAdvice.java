package cn.com.bonc.sce.filter;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.annotation.Payloads;
import cn.com.bonc.sce.exception.InvalidTokenException;
import cn.com.bonc.sce.service.AuthenticationService;
import cn.com.bonc.sce.tool.JWTUtil;
import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/29 19:19
 */
@Component
public class TicketAdvice implements HandlerMethodArgumentResolver {

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public boolean supportsParameter( MethodParameter parameter ) {

        if ( parameter.hasParameterAnnotation( Payloads.class ) || parameter.hasParameterAnnotation( CurrentUserId.class ) ) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory ) throws Exception {
        //验证JWT
        Claims claims = authenticationService.validateJWT( webRequest );

        return JWTUtil.getDataOfTicket( claims, parameter );
    }
}
