package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  author wf
 *  date 2019/04/01
 */
@FeignClient( "sce-data-mybatis" )
public interface UserDaoOfMybatis {

    @RequestMapping( value = "/app-portal/user-rest", method = RequestMethod.GET )
    RestRecord getUser( @RequestHeader( "appId" ) String appId,
                        @RequestHeader( "appToken" ) String appToken );

    @RequestMapping( value = "/app-portal/user-jwt", method = RequestMethod.GET )
    RestRecord getUserJWT( @RequestParam("appId") String appId,
                           @RequestParam("users") List users );

    @RequestMapping( value = "/app-portal/user-detailed", method = RequestMethod.GET )
    RestRecord getUserDetailed( @RequestParam( "appId" ) String appId,
                                @RequestParam( "userId" ) String userId );

}
