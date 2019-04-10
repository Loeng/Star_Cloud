package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient( value = "sce-data-mybatis" )
@Repository
public interface UserOperationMybatisDao {

    /**
     * 更新用户
     *
     * @param userInfo
     * @return 是否更新成功
     */
    @RequestMapping( value = "/user-info-mybatis/updateUserInfo", method = RequestMethod.PUT )
    RestRecord updateUserInfoById( @RequestBody Map< String, Object > userInfo );

    @RequestMapping( value = "/user-info-mybatis/updatePassword", method = RequestMethod.PUT )
    RestRecord updatePassword( @RequestBody Map< String, Object > info );

    @RequestMapping( value = "/user-info-mybatis/user_head_portrait", method = RequestMethod.GET )
    RestRecord updateUserHeadPortrait( @RequestParam( "userId" ) String userId,
                                       @RequestParam( "resourceId" ) Integer resourceId );

}
