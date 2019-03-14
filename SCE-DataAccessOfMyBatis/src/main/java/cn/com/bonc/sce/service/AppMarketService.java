package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.mapper.AppMarketMapper;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public RestRecord saveBacklog(String appId, String appToken, String userId, Map<String, Object> backlog){
        if(!appToken.equals(appMarketMapper.selectAppToken(appId))){
            return new RestRecord(152, WebMessageConstants.SCE_WEB_MSG_152);
        }
        List<String> operateUserIds = (List) backlog.get("users");
        if(operateUserIds.size() < 1){
            return new RestRecord(431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431, "users"));
        }
        // todo 验证authentication
        backlog.put("userId", userId);
        for(String operateUserId : operateUserIds){
            //调用id生成器生成id
            backlog.put("backlogId", idWorker.nextId());

            backlog.put("operateUserId", operateUserId);
            appMarketMapper.insertBacklog(backlog);
        }


        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }

}
