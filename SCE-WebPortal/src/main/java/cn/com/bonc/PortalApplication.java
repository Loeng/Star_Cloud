package cn.com.bonc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:57
 */
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PortalApplication {
    public static void main( String[] args ) {
        SpringApplication.run( PortalApplication.class, args );
    }
}
