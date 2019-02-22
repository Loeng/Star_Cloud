package cn.com.bonc.sce.service;

import cn.com.bonc.sce.tool.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author jc_D
 * @description
 * @date 2019/2/21
 **/
@Service
public class IdWorkerService {

    @Value( "${IdWorker.workerId}" )
    private Integer workId;

    @Value( "${IdWorker.datacenterId}" )
    private Integer datacenterId;

    public Long getId() {
        System.out.println( workId );
        System.out.println( datacenterId );
        return new IdWorker( workId, datacenterId ).nextId();
    }
}
