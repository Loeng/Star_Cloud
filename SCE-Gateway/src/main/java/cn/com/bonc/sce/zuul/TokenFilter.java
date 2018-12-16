package cn.com.bonc.sce.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: tlz
 * @Date: 2018/11/7 17:04
 * @Description: filterOrder：过滤的顺序
 * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
 * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
 */
public class TokenFilter extends ZuulFilter {
    @Override
    public String filterType() {

        /**这里有四个串可供返回
         *  pre：路由之前
         *  routing：路由之时
         *  post： 路由之后
         *  error：发送错误调用
         *  */
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");

        if (token == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            requestContext.setResponseBody("unauthorized");

            return  null;
        }
        return null;
    }
}
