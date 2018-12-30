package cn.com.bonc.sce.config;

import cn.com.bonc.sce.filter.TicketAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/22 17:08
 */
@Slf4j
@SpringBootConfiguration
@EnableWebMvc
@ComponentScan(basePackages = "cn.com.bonc.sce.controller")
public class RestConfig implements WebMvcConfigurer {

    @Autowired
    TicketAdvice ticketAdvice;

    @Override
    public void addArgumentResolvers( List< HandlerMethodArgumentResolver > argumentResolvers ) {
        argumentResolvers.add( ticketAdvice );
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry.addResourceHandler( "swagger-ui.html" )
                .addResourceLocations( "classpath:/META-INF/resources/" );
        registry.addResourceHandler( "/webjars/**" )
                .addResourceLocations( "classpath:/META-INF/resources/webjars/" );

    }
}
