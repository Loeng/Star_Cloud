package cn.com.bonc.sce.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/22 17:08
 */
public class RestConfig extends WebMvcConfigurationSupport {
    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry.addResourceHandler( "/**" ).addResourceLocations( "classpath:/static/" );
        registry.addResourceHandler( "swagger-ui.html" )
                .addResourceLocations( "classpath:/META-INF/resources/" );
        registry.addResourceHandler( "/webjars/**" )
                .addResourceLocations( "classpath:/META-INF/resources/webjars/" );
        super.addResourceHandlers( registry );
    }
}
