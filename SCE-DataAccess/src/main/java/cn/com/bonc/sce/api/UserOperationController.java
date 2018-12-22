package cn.com.bonc.sce.api;


import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.rest.RestRecord;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserOperationController( UserInfoRepository userInfoRepository ) {
        this.userInfoRepository = userInfoRepository;
    }*/

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
        return new RestRecord( 200, "操作成功!" );
    }

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     * @return 修改是否成功
     */
    @PutMapping
    @ResponseBody
    public RestRecord updateTeacherRecommendAppInfo(
            @RequestBody  Map   userInfo ) {
        return new RestRecord( 200, "修改成功！", "" );
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

        return new RestRecord( 200, "删除成功！", "" );
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
        return new RestRecord( 200, "操作成功！", map );
    }

}
