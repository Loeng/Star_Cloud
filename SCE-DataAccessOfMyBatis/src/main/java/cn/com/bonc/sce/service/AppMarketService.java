package cn.com.bonc.sce.service;

import cn.com.bonc.sce.annotation.Payloads;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.mapper.AppMarketMapper;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AppMarketService {

    @Autowired
    AppMarketMapper appMarketMapper;

    @Autowired
    IdWorker idWorker;

    public List<Map> getAppCount(){
        return appMarketMapper.selectAppCount();
    }

    public List<Map> userToDo(String userId){
        return appMarketMapper.selectUserToDo(userId);
    }

    @Transactional
    public RestRecord saveBacklog(String appId, String appToken, String authentication, Map<String, Object> backlog){
        // todo  验证appId 、 appToken 和 authentication
        String userId = null;
        try {
            String payloadsStr = Base64.decodeStr( authentication.split( "\\." )[ 1 ] );
            userId = JSONUtil.toBean( payloadsStr, Map.class ).get("userId").toString();
        }catch (Exception e){
            log.info("ticket验证失败");
            return new RestRecord(420, WebMessageConstants.SCE_PORTAL_MSG_420);
        }
        //调用id生成器生成id
        long backlogId = idWorker.nextId();

        backlog.put("backlogId", backlogId);
        backlog.put("userId", userId);
        appMarketMapper.insertBacklog(backlog);
        appMarketMapper.insertBacklogItems(backlog);
        appMarketMapper.insertBacklogType(backlog);

        return null;
    }

}
