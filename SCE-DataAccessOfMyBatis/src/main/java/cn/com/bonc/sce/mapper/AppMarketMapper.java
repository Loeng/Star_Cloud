package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface AppMarketMapper {

    List<Map> selectAppCount();

    List<Map> selectUserToDo(String userId);

    int selectUserToDoCount( String userId );

    void insertBacklog( Map map );

    String selectAppToken( String appId );

    void updateBacklog( Map map );

    Map<String, Object> getAppInfoById(String appId);

    int selectUserAppAuth( @Param( "userId" ) String userId, @Param( "appId" ) String appId );

    List<Map> selectUserToApp( @Param( "appId" ) String appId, @Param( "users" ) List users, @Param( "userTypes" ) List userTypes );



}
