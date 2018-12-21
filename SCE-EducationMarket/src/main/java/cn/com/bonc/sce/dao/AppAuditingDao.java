package cn.com.bonc.sce.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@FeignClient( "sce-data-access" )
public interface AppAuditingDao {
    @RequestMapping( value = "/AppVersion/approve/{appId}", method = RequestMethod.PATCH )
    public boolean appVersionUpdateApprove(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId );

    @RequestMapping( value = "/AppVersion/reject/{appId}", method = RequestMethod.PATCH )
    public boolean appVersionUpdateReject(
            @PathVariable( "appId" ) String appId,
            @RequestParam( "userId" ) String userId,
            @RequestParam( "rejectReason" ) String rejectReason );
}
