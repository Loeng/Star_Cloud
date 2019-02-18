package cn.com.bonc.sce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/18 15:50
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {
    public static void main( String[] args ) {
        SpringApplication.run( GatewayApplication.class, args );
    }
}
