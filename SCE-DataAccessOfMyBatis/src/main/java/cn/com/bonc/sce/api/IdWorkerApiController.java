package cn.com.bonc.sce.api;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取id
 *
 * @author jc_D
 * @description
 * @date 2019/2/26
 **/
@Slf4j
@RestController
@RequestMapping( "/id-worker" )
public class IdWorkerApiController {

    @Autowired
    private IdWorker idWorker;

    @GetMapping
    public RestRecord getId() {
        return new RestRecord( 200, idWorker.nextId() );
    }


}
