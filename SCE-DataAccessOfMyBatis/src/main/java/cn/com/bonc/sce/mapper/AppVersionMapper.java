package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author BTW
 */
public interface AppVersionMapper {

    @Update( "UPDATE STARCLOUDMARKET.SCE_MARKET_APP_VERSION  SET  APP_STATUS = #{applyType},UPDATE_USER_ID= #{userId}  WHERE  APP_ID = #{appId} and APP_VERSION = #{appVersion} " )
    int applyAppOnShelfByUserId( @Param( "applyType" ) String applyType,
                                 @Param( "userId" ) String userId,
                                 @Param( "appId" ) String appId,
                                 @Param( "appVersion" ) String appVersion );

}
