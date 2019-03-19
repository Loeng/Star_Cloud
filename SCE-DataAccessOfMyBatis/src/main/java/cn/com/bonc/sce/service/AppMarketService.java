package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.mapper.AppMarketMapper;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

    public RestRecord userToDo(String userId, String pageNum, String pageSize){
        try {
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        }catch (NumberFormatException e){
            log.warn("不支持的分页参数");
            return new RestRecord(433, WebMessageConstants.SCE_PORTAL_MSG_433);
        }
        RestRecord restRecord = new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
//        List list = appMarketMapper.selectUserToDo(userId);
//        PageInfo<List> pageInfo = new PageInfo<List>(list);
//        restRecord.setData(list);
//        System.out.println("list:");
//        System.out.println(list);
//        restRecord.setTotal(pageInfo.getTotal());
        List list = appMarketMapper.selectUserToDo(userId);
        restRecord.setData(list);
        restRecord.setTotal(appMarketMapper.selectUserToDoCount(userId));
        return restRecord;
    }

    @Transactional(rollbackFor = Exception.class)
    public RestRecord saveBacklog(String appId, String appToken, String userId, Map<String, Object> backlog){
        if(!appToken.equals(appMarketMapper.selectAppToken(appId))){
            return new RestRecord(152, WebMessageConstants.SCE_WEB_MSG_152);
        }
        List<String> operateUserIds = (List) backlog.get("users");
        if(operateUserIds.size() < 1){
            return new RestRecord(431, String.format(WebMessageConstants.SCE_PORTAL_MSG_431, "users"));
        }
        // todo 验证authentication
        List<Map> result = new ArrayList<>();
        backlog.put("userId", userId);
        for(String operateUserId : operateUserIds){
            //调用id生成器生成id
            Map<String, Object> map = new HashMap();
            long backlogId = idWorker.nextId();
            backlog.put("backlogId", backlogId);
            backlog.put("operateUserId", operateUserId);
            map.put("backlogId", backlogId);
            map.put("operateUserId", operateUserId);
            result.add(map);
            appMarketMapper.insertBacklog(backlog);
        }

        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200, result);
    }

    @Transactional(rollbackFor = Exception.class)
    public RestRecord changeBacklog(String appId, String appToken, String userId, Map map){
        if(!appToken.equals(appMarketMapper.selectAppToken(appId))){
            return new RestRecord(152, WebMessageConstants.SCE_WEB_MSG_152);
        }
        appMarketMapper.updateBacklog(map);
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }

}
