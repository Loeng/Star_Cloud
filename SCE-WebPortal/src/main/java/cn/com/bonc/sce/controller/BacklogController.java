package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.annotation.Payloads;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/backlog-service")
public class BacklogController {

    @Autowired
    private AppMarketService appMarketService;

    @PostMapping("/backlog")
    public RestRecord backlog( @RequestBody Map< String, Object > backlog, @Payloads List<Map> list ) {
        System.out.println(backlog);
        if ( backlog == null || backlog.get( "backlogType" ) == null || backlog.get( "url" ) == null|| backlog.get( "appId" ) == null
                || backlog.get( "users" ) == null|| backlog.get( "title" ) == null || backlog.get( "appToken" ) == null ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必须" ) );
        }
        return appMarketService.backlog( backlog, list );
    }

    @GetMapping( "/backlog" )
    public RestRecord backlog( @CurrentUserId String userId,
                               @RequestParam( required = false, value = "pageNum", defaultValue = "1" ) String pageNum,
                               @RequestParam( required = false, value = "pageSize", defaultValue = "10" ) String pageSize ) {
        return appMarketService.userToDo( userId, pageNum, pageSize );
    }

    @PatchMapping("/backlog")
    public RestRecord backlog_patch( @Payloads List<Map> list,
                                     @RequestBody Map< String, String > map){
        Object userId = list.get( 0 ).get( "userId" );
        return appMarketService.backlog_patch( userId.toString(), map );
    }

}
