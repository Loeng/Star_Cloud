package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author jc_D
 */
public interface AppInfoMapper {

    /**
     * 从应用信息表删除应用
     */
    int deleteAppInfo( @Param( "idList" ) List< String > idList,
                       @Param( "uid" ) String uid );

    @Select( "SELECT ID ,AUDIT_STATUS FROM STARCLOUDMARKET.SCE_AUDIT_STATUS_DIC ORDER BY ID ASC" )
    List< Map< String, Object > > getAllAuditStatus();

    /**
     * 从全部应用进入的详情
     */
    @Select( "SELECT * FROM STARCLOUDMARKET.V_APP_DETAIL_INFO WHERE APP_ID = #{appId} ORDER BY CREATE_TIME DESC" )
    List< Map< String, Object > > findAppDetailById( String appId );

    @Select( "SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_OPEN WHERE APP_ID = #{appId} AND USER_ID = #{userId}" )
    Map< String, Object > findAppOpenInfo( @Param( "appId" ) String appId, @Param( "userId" ) String userId );

    @Select( "SELECT count(*) FROM STARCLOUDMARKET.SCE_MARKET_APP_INFO  A  WHERE A.IS_DELETE=1" )
    int getAppCountInfo();

    /**
     * pp分类信息统计
     */
    @Select( "SELECT  COUNT(a.APP_TYPE_ID) as num,b.APP_TYPE_NAME FROM  STARCLOUDMARKET.SCE_MARKET_APP_APPTYPE_REL a INNER  JOIN (SELECT * FROM STARCLOUDMARKET.SCE_MARKET_APP_TYPE   WHERE IS_DELETE='1') b " +
            "ON  a.APP_TYPE_ID = b.APP_TYPE_ID  GROUP BY  b.APP_TYPE_NAME,a.APP_TYPE_ID " )
    List< Map > getAppInfo();
}
