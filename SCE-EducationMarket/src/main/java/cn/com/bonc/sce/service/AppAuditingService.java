package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AppAuditingDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public AppAuditingService( AppAuditingDao appAuditingDao ) {
        this.appAuditingDao = appAuditingDao;
    }

    public RestRecord appVersionUpdateApprove( String userId, List< Map< String, String > > approveList ) {
        return appAuditingDao.appVersionUpdateApprove( userId, approveList );
    }

    public RestRecord appVersionDownShelfApprove( String userId, List< Map< String, String > > approveList ) {
        return appAuditingDao.appVersionDownShelfApprove( userId, approveList );
    }

    public RestRecord appVersionUpdateReject( String operateType, String userId, List< Map< String, String > > approveList, String rejectReason ) {
        return appAuditingDao.appVersionUpdateReject(operateType, userId, approveList, rejectReason );
    }
}
