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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/backlog-service")
public class BacklogController {

    @Autowired
    private AppMarketService appMarketService;

    @PostMapping("/backlog")
    public RestRecord backlog(HttpServletRequest request, @RequestBody Map<String, Object> backlog){
        if(backlog == null || backlog.get("backlogType") == null || backlog.get("content") == null || backlog.get("url") == null || backlog.get("users") == null ||
                request.getHeader("appId") == null || request.getHeader("appToken") == null || request.getHeader("authentication") == null){
            return new RestRecord(WebMessageConstants.SCE_WEb_MSG_002,"必须");
        }
        return appMarketService.backlog(request, backlog);
    }

    @GetMapping("/backlog")
    public RestRecord backlog(@CurrentUserId String userId){
        return appMarketService.userToDo(userId);
    }

}
