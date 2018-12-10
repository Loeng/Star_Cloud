package cn.com.bonc.sce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/6 14:30
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main( String[] args ) {
        SpringApplication.run( ConfigServerApplication.class, args );
    }

}
