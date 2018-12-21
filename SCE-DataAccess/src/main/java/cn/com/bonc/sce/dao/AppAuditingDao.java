package cn.com.bonc.sce.dao;

import org.springframework.stereotype.Repository;
/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
public class AppAuditingDao {
    public boolean appVersionUpdateApprove( String appId, String userId ) {
        return false;
    }

    public boolean appVersionUpdateReject( String appId, String userId, String rejectReason ) {
        return false;
    }
}
