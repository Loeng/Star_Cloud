package cn.com.bonc.sce.config;

import cn.com.bonc.sce.interceptor.AuthorizationInterceptor;
import cn.com.bonc.sce.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.List;

/**
 * @author: tlz
 * @description: 对请求加入跨域访问限制，添加拦截器，请求是参数装配
 * @create: 2018-09-10 19:35
 **/

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.com.bonc.sce.api")
@PropertySource(value = "classpath:bootstrap.yaml", ignoreResourceNotFound = true, encoding = "UTF-8")
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver > argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
    /*

        @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            //单个文件最大
            factory.setMaxFileSize("80MB"); //KB,MB
            /// 设置总上传数据总大小
            factory.setMaxRequestSize("102400KB");
            return factory.createMultipartConfig();
        }
    */


}
