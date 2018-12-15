package cn.com.bonc.sce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/5 14:33
 */
@Slf4j
@EnableSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulServiceApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ZuulServiceApplication.class, args );
    }

}
