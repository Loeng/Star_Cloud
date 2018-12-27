package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.CountDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Service
public class CountService {
    private CountDao countDao;

    @Autowired
    public CountService( CountDao countDao ) {
        this.countDao = countDao;
    }

    public RestRecord countSingleAppDownload( String appId, Integer pageNum, Integer pageSize ) {
        return countDao.countSingleAppDownload( appId, pageNum, pageSize );
    }

    public RestRecord countAppDownloadByType( String appType, Integer pageNum, Integer pageSize ) {
        return countDao.countAppDownloadByType( appType, pageNum, pageSize );
    }

    public RestRecord countAppDownloadByCompanyId( Long companyId, Integer pageNum, Integer pageSize ) {
        return countDao.countAppDownloadByCompanyId( companyId, pageNum, pageSize );
    }

    public RestRecord getAppDownloadRanking( Integer pageSize, Integer pageNum ) {
        return countDao.getAppDownloadRanking( pageSize, pageNum );
    }

    public RestRecord getDownloadChange( String userId, String startTime, String endTime ) {
        return countDao.getDownloadChange( userId, startTime, endTime );
    }

    public RestRecord getDownloadByType( String userId, String time ) {
        return countDao.getDownloadByType( userId, time );
    }

    public RestRecord getAppTypePrecent( String userId ) {
        return countDao.getAppTypePrecent( userId );
    }

    public RestRecord getCollectionChange( String userId, String startTime, String endTime ) {
        return countDao.getCollectionChange( userId, startTime, endTime );
    }
}
