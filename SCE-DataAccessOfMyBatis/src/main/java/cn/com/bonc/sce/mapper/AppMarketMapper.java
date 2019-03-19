package cn.com.bonc.sce.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface AppMarketMapper {

    List<Map> selectAppCount();

    List<Map> selectUserToDo(String userId);

    int selectUserToDoCount(String userId);

    void insertBacklog(Map map);

    String selectAppToken(String appId);

    void updateBacklog(Map map);

}
