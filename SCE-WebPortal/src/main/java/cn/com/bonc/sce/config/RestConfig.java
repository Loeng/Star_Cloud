package cn.com.bonc.sce.config;

import cn.com.bonc.sce.filter.TicketAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/20 11:43
 */
@Slf4j
@SpringBootConfiguration
@EnableWebMvc
@ComponentScan(basePackages = "cn.com.bonc.sce.controller")
public class RestConfig  implements  WebMvcConfigurer{


    @Autowired
    TicketAdvice ticketAdvice;

    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry.addResourceHandler( "/**" )
        .addResourceLocations( "classpath:/META-INF/resources/" );

        registry.addResourceHandler( "/*/**" )
                .addResourceLocations( "classpath:/META-INF/resources/" );
    }

    @Override
    public void addArgumentResolvers( List< HandlerMethodArgumentResolver > argumentResolvers ) {
        argumentResolvers.add( ticketAdvice );
    }

    /**
     * Rest 配置
     */
    @Bean
    public WebMvcConfigurer restConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings( CorsRegistry registry ) {
                log.info( "No rest interceptors" );
                registry.addMapping( "/**/*" ).allowedOrigins( "*" ).allowedMethods( "*" ).allowCredentials( true );
            }

            @Override
            public void addInterceptors( InterceptorRegistry registry ) {
//                 registry.addInterceptor( new LoginInterceptor() ).addPathPatterns( "/**/*" ).addPathPatterns( "*" );
            }

        };
    }
}
