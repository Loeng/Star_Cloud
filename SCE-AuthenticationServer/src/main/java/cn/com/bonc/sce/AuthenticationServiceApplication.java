package cn.com.bonc.sce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/5 14:33
 */
@Slf4j
@EnableSwagger2
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AuthenticationServiceApplication {

    public static void main( String[] args ) {
        SpringApplication.run( AuthenticationServiceApplication.class, args );
    }

    /**
     * Rest 跨域配置
     */
    @Bean
    public WebMvcConfigurer restConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings( CorsRegistry registry ) {
                registry.addMapping( "/**/*" ).allowedOrigins( "*" ).allowedMethods( "*" ).allowCredentials( true );
            }

        };
    }
}
