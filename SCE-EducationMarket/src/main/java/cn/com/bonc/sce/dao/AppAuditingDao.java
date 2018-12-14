package cn.com.bonc.sce.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AppAuditingDao {
    public boolean appVersionUpdateApprove( String appId, String userId ) {
        return false;
    }

    public boolean appVersionUpdateReject( String appId, String userId, String rejectReason ) {
        return false;
    }
}
