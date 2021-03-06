package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.mapper.AppMarketMapper;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public RestRecord userToDo( String userId, String pageNum, String pageSize ) {
        try {
            PageHelper.startPage( Integer.parseInt( pageNum ), Integer.parseInt( pageSize ) );
        } catch ( NumberFormatException e ) {
            log.warn( "不支持的分页参数 -> pageNum:{},pageSize:{}", pageNum, pageSize );
            return new RestRecord( 433, WebMessageConstants.SCE_PORTAL_MSG_433 );
        }
        RestRecord restRecord = new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        List list = appMarketMapper.selectUserToDo( userId );
        restRecord.setData( list );
        long total = list == null ? 0L : ((Page<Map<String, Object>>) list).getTotal();
        restRecord.setTotal( total );
        return restRecord;
    }

    @Transactional
    public RestRecord saveBacklog( String appId, String appToken, String userId, Map< String, Object > backlog ) {
        if( !appToken.equals( appMarketMapper.selectAppToken( appId ) ) ){
            return new RestRecord( 152, WebMessageConstants.SCE_WEB_MSG_152 );
        }
        List< String > operateUserIds = ( List ) backlog.get( "users" );
        if ( operateUserIds.size() < 1 ) {
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "users" ) );
        }
        List< Map > result = new ArrayList<>();
        backlog.put( "userId", userId );
        for ( String operateUserId : operateUserIds ) {
            //调用id生成器生成id
            Map< String, Object > map = new HashMap<>();
            long backlogId = idWorker.nextId();
            backlog.put( "backlogId", backlogId );
            backlog.put( "operateUserId", operateUserId );
            backlog.put( "appId", appId );
            map.put( "backlogId", backlogId );
            map.put( "operateUserId", operateUserId );
            result.add( map );
            appMarketMapper.insertBacklog( backlog );
            backlog.put( "id", idWorker.nextId() );
            appMarketMapper.insertToDo( backlog );
            appMarketMapper.insertPendingItem( backlog );
        }

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, result );
    }

    @Transactional
    public RestRecord changeBacklog( String userId, Map map ){
        Object backlogId = map.get( "backlogId" );
        Object status = map.get( "status" );
        if( backlogId == null || status == null ){
            return new RestRecord( 431, String.format( WebMessageConstants.SCE_PORTAL_MSG_431, "必须" ) );
        }
        appMarketMapper.updateBacklog( map );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    public String findAppToken( String appId ) {
        return appMarketMapper.selectAppToken( appId );
    }

    public Map<String, Object> getAppInfoById( String appId ){
        return appMarketMapper.getAppInfoById( appId );
    }

    public int getUserAppAuth( String userId, String appId ){
        return appMarketMapper.selectUserAppAuth( userId, appId );
    }

    public RestRecord getUser(String appId, String appToken ){
        //验证appToken
        if( !appToken.equals( appMarketMapper.selectAppToken( appId ) ) ){
            return new RestRecord( 152, WebMessageConstants.SCE_WEB_MSG_152 );
        }
        // 可以同步给应用的数据类型
        List< String > userTypes = new ArrayList<>( 5 );
        userTypes.add("1");
        userTypes.add("2");
        userTypes.add("5");
        userTypes.add("7");
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appMarketMapper.selectUserToApp( appId, null, userTypes ) );
    }

    public RestRecord getUser( String appId, List users ){
        // 可以同步给应用的数据类型
        List< String > userTypes = new ArrayList<>( 5 );
        userTypes.add("1");
        userTypes.add("2");
        userTypes.add("5");
        userTypes.add("7");
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appMarketMapper.selectUserToApp( appId, users, userTypes ) );
    }

    public RestRecord getUserDetailed( String appId, String userId ){
        List< String > userTypes = new ArrayList<>( 5 );
        userTypes.add("1");
        userTypes.add("2");
        userTypes.add("5");
        userTypes.add("7");
        List< String > users = new ArrayList<>( 2 );
        users.add( userId );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, appMarketMapper.selectUserToApp( appId, users, userTypes ) );
    }

}
