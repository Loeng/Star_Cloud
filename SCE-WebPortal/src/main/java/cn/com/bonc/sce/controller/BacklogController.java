package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.annotation.Payloads;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppMarketService;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/backlog-service")
public class BacklogController {

    @Autowired
    private AppMarketService appMarketService;

    @PostMapping("/backlog")
    public RestRecord backlog( @RequestBody Map< String, Object > backlog, @CurrentUserId String userId ) {
        if ( backlog == null || backlog.get( "backlogType" ) == null || backlog.get( "content" ) == null || backlog.get( "url" ) == null || backlog.get( "users" ) == null ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必须" ) );
        }
        return appMarketService.backlog( backlog, userId );
    }

    @GetMapping( "/backlog/{pageNum}/{pageSize}" )
    public RestRecord backlog( @CurrentUserId String userId,
                               @PathVariable String pageNum,
                               @PathVariable String pageSize ) {
        return appMarketService.userToDo( userId, pageNum, pageSize );
    }

    @PatchMapping("/backlog")
    public RestRecord backlog_patch(@CurrentUserId String userId,
                                    @RequestBody Map<String, String> map){
        return appMarketService.backlog_patch(userId,map);
    }

}
