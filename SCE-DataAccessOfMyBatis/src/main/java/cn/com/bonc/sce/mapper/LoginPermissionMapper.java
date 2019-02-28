package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by Charles on 2019/2/26.
 */
public interface LoginPermissionMapper {

    Integer updateLoginPermission( @Param( "userId" ) String userId,
                           @Param( "loginPermissionStatus" ) Integer loginPermissionStatus );
}
