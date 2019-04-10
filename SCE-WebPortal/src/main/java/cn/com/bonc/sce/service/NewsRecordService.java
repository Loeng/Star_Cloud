package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.NewsRecordDao;
import cn.com.bonc.sce.model.NewsRecordModel;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BTW
 */
@Slf4j
@Service
public class NewsRecordService {

    @Autowired
    private NewsRecordDao newsRecordDao;

    public RestRecord insertNewsRecord( NewsRecordModel newsRecordModel){
        return newsRecordDao.insertNewsRecord( newsRecordModel );
    }
}
