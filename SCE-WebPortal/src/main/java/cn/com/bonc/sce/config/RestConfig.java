package cn.com.bonc.sce.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/20 11:43
 */
@Slf4j
@SpringBootConfiguration
public class RestConfig {
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
                // registry.addInterceptor( new LoginInterceptor() ).addPathPatterns( "/**/*" ).addPathPatterns( "*" );
            }

        };
    }
}
