package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppAuditingDao;
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
public class AppAuditingService {
    private AppAuditingDao appAuditingDao;

    @Autowired
    public AppAuditingService(AppAuditingDao appAuditingDao){
        this.appAuditingDao = appAuditingDao;
    }

    public RestRecord appVersionUpdateApprove( String appId, String userId ) {
        return appAuditingDao.appVersionUpdateApprove( appId, userId );
    }

    public RestRecord appVersionUpdateReject( String appId, String userId, String rejectReason ) {
        return appAuditingDao.appVersionUpdateReject( appId, userId, rejectReason );
    }
}
