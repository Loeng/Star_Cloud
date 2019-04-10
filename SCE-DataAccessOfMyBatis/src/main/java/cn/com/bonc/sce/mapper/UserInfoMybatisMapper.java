package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface UserInfoMybatisMapper {

    int updateUserInfo( Map< String, Object > userInfo );

    int updatePassword( Map< String, Object > info );

    int addPassword( Map< String, Object > info );

    @Update( "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET HEAD_PORTRAIT = (SELECT FILE_STORE_PATH FROM STARCLOUDPORTAL.SCE_FILE_RESOURCE WHERE RESOURCE_ID=#{resourceId}) WHERE USER_ID = #{userId}" )
    int updateUserHeadPortrait( @Param( "userId" ) String userId,
                                @Param( "resourceId" ) Integer resourceId );

    @Select( "SELECT USER_NAME FROM STARCLOUDPORTAL.SCE_COMMON_USER WHERE USER_ID = #{userId}" )
    String getUserNameById( @Param( "userId" ) String userId );

}
