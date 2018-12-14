package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppAuditingDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppAuditingService {
    private AppAuditingDao appAuditingDao;

    @Autowired
    public AppAuditingService(AppAuditingDao appAuditingDao){
        this.appAuditingDao = appAuditingDao;
    }

    public boolean appVersionUpdateApprove( String appId, String userId ) {
        return appAuditingDao.appVersionUpdateApprove( appId, userId );
    }

    public boolean appVersionUpdateReject( String appId, String userId, String rejectReason ) {
        return appAuditingDao.appVersionUpdateReject( appId, userId, rejectReason );
    }
}
