package cn.com.bonc.sce.mapper;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface AppMarketMapper {

    List<Map> selectAppCount();

    List<Map> selectUserToDo(String userId);

    int insertBacklog(Map map);

    int insertBacklogItems(Map map);

    int insertBacklogType(Map map);


}
