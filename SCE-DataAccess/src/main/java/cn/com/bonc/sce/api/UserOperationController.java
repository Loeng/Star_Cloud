package cn.com.bonc.sce.api;


import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.rest.RestRecord;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;

import java.util.Map;

/**
 * @author 管理员
 * @Auther: tlz
 * @Date: 2018/12/22 10:54
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping( "/user-info" )
public class UserOperationController {

    public static final String PASSWORD = "star123!";

    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserOperationController( UserInfoRepository userInfoRepository ) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * 添加用户信息
     *
     * @param userInfo
     * @return 是否添加成功
     */
    @PostMapping
    @ResponseBody
    public RestRecord addUserInfo(
            @RequestBody Map< String, Object > userInfo ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200,"" );
    }

    /**
     * 修改用户信息（登录状态、重置密码、删除）
     *
     * @param userInfo 用户信息
     * @param userId 修改的用户ID
     * @return 修改是否成功
     */
    @PutMapping("/updateUserInfo")
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo( @RequestBody Map<String,Object>  userInfo,@RequestParam("userId") String userId ) {
        int isLogin ;
        int isDelete ;
        String isReset ;
        int status = 0;
        if (!CollectionUtils.isEmpty(userInfo)){
            if (null != userInfo.get("isLogin") && !"".equals(userInfo.get("isLogin"))){
                isLogin = (int) userInfo.get("isLogin");
                log.info("更正用户[{}]的登录权限[{}]",userId,isLogin);
                status = userInfoRepository.updateUserLoginStatus(isLogin,userId);
            }
            else if (null != userInfo.get("isDelete") && !"".equals(userInfo.get("isDelete"))){
                isDelete = (int) userInfo.get("isDelete");
                log.info("删除用户[{}]-[{}]",userId,isDelete);
                status = userInfoRepository.deleteUser(isDelete,userId);
            }
            else if(null != userInfo.get("isReset") && !"".equals(userInfo.get("isReset"))){
                isReset = PASSWORD;
                log.info("更正用户[{}]的密码[{}]",userId,isReset);
                status = userInfoRepository.resetUserPassword(isReset,userId);
            }
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200,status );
    }

    /**
     * 删除用户信息
     *
     * @param userId 用户ID
     * @return 删除是否成功
     */
    @DeleteMapping
    @ResponseBody
    public RestRecord deleteUserInfo(
            @RequestParam( "userId" ) String userId ) {

        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, "" );
    }

    /**
     * 查询用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping
    @ResponseBody
    public RestRecord selectUserInfo(
            @RequestParam( "userId" ) String userId ) {
        Map map = new HashMap<>();
        map.put( "userId", "43999" );
        map.put( "userAccount", "X213212123" );
        map.put( "userName", "loader" );
        map.put( "userId", "X213212123" );
        map.put( "gender", "1" );
        map.put( "userType", "2" );
        map.put( "mailAddress", "123112@qq.com" );
        map.put( "phoneNumber", "186678XXX88" );
        map.put( "certificateType", "2" );
        map.put( "certificateNumber", "XR5441161X" );
        map.put( "address", "成都市青羊区青年路" );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, map );
    }

}
