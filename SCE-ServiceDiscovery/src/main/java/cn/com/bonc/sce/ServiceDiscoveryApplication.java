package cn.com.bonc.sce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/6 15:22
 */
@Slf4j
@EnableEurekaServer
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class ServiceDiscoveryApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ServiceDiscoveryApplication.class, args );
    }
}
