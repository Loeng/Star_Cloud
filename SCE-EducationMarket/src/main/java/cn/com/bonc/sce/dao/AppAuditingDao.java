package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@FeignClient( "sce-data-access" )
public interface AppAuditingDao {
    @RequestMapping( value = "/app-version/approve/{userId}", method = RequestMethod.PUT )
    public RestRecord appVersionUpdateApprove(
            @PathVariable( "userId" ) String userId,
            @RequestBody List< Map< String, String > > approveList );

    @RequestMapping( value = "/app-version/approve/down-shelf/{userId}", method = RequestMethod.PUT )
    public RestRecord appVersionDownShelfApprove(
            @PathVariable( "userId" ) String userId,
            @RequestBody List< Map< String, String > > approveList );

    @RequestMapping( value = "/app-version/reject/{operateType}/{userId}", method = RequestMethod.PUT )
    public RestRecord appVersionUpdateReject(
            @PathVariable( "operateType" ) String operateType,
            @PathVariable( "userId" ) String userId,
            @RequestBody List< Map< String, String > > approveList,
            @RequestParam( "rejectReason" ) String rejectReason );
}
