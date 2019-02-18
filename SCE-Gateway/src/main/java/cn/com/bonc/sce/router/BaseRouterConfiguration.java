package cn.com.bonc.sce.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/18 16:08
 */
@Slf4j
@Controller
@SpringBootConfiguration
public class BaseRouterConfiguration {
    @Bean
    @Profile( value = "dev,test")
    public RouteLocator defaultRoutes( RouteLocatorBuilder builder ) {
        return builder.routes()
                .route( config -> config
                        .path( "/api-management$" )
                        .filters( f -> f.addRequestHeader( "Hello", "World" ) )
                        .uri( "http://httpbin.org:80" ) )
                .build();
    }
}
