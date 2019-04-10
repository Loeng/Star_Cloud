package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.NewsRecordBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.NewsRecordDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/news-record" )
public class NewsRecordController {

    @Autowired
    private NewsRecordDao newsRecordDao;
    @Autowired
    private IdWorker idWorker;

    @PostMapping( "/new-record" )
    @ResponseBody
    public RestRecord insertNewsRecord( @RequestBody NewsRecordBean newsRecordBean ) {
        try {
            newsRecordBean.setId( idWorker.nextId() );
            int count = newsRecordDao.insertNewsRecord( newsRecordBean );
            if ( count == 1 ) {
                return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
            } else {
                return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
            }
        } catch ( Exception e ) {
            log.error( "insert newsRecord fail {}", e );
            return new RestRecord( 423, MessageConstants.SCE_MSG_409, e );
        }
    }

}
