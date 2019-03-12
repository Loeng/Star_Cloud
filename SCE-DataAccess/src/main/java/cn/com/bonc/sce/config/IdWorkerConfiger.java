package cn.com.bonc.sce.config;

import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置[分布式自增长ID]的bean
 * author jc_D
 */
@Slf4j
@Configuration
public class IdWorkerConfiger {

    @Value( "${IdWorker.workerId}" )
    private Integer workId;

    @Value( "${IdWorker.datacenterId}" )
    private Integer datacenterId;

    @Bean
    public IdWorker idWorker() {
        log.info( "workId:{},datacenterId:{}",workId,datacenterId );
        return new IdWorker( workId, datacenterId );
    }
}
