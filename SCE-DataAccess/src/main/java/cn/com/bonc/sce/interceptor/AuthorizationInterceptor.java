package cn.com.bonc.sce.interceptor;

import cn.com.bonc.sce.annotation.Authorization;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: tlz
 * @description: 验证操作是否有效，对token进行校验
 * @create: 2018-12-13 17:24
 **/
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle( HttpServletRequest request,
                              HttpServletResponse response, Object handler) throws Exception {


        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod )) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod ) handler;
        Method method = handlerMethod.getMethod();


        //从header中得到token
        String authorization = request.getHeader("authorization");

        // redis查询或者 数据库查询  token是否有效，有效操作则更新redis的token时间


        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            //抛出异常，让全局异常处理
            throw new Exception();
        }

        return true;
    }
}
