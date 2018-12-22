package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.repository.UserListData;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户列表-查询api
 *
 * @author jc_D
 * @description
 * @date 2018/12/22
 **/
@Slf4j
@RestController
@RequestMapping( "/user-list" )
public class UserListController {


    /**
     * 根据角色id查询用户信息
     *
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/role/{roleId}/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByRole( @PathVariable String roleId,
                                         @PathVariable( "pageNum" ) Integer pageNum,
                                         @PathVariable( "pageSize" ) Integer pageSize ) {
        int total = 10;
        List info = new ArrayList();
        switch ( roleId ) {
            case "1":
                info = UserListData.ziZhuCe( pageNum, pageSize, total );
                break;
            case "2":
                info = UserListData.xueXiao( pageNum, pageSize, total );
                break;
            case "3":
                info = UserListData.jiGou( pageNum, pageSize, total );
                break;
            case "4":
                info = UserListData.changShang( pageNum, pageSize, total );
                break;
            case "5":
                info = UserListData.daiLiShang( pageNum, pageSize, total );
                break;
            case "6":
                info = UserListData.shengHeYongHu( pageNum, pageSize, total );
                break;
        }
        Map map = new HashMap();
        map.put( "total", total );
        map.put( "data", info );
        return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200, map );
    }


    /**
     * 根据条件查询
     *
     * @param conditionMap
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping( "/condition/{pageNum}/{pageSize}" )
    public RestRecord getUserInfoByCondition( @RequestBody Map conditionMap,
                                              @PathVariable( "pageNum" ) Integer pageNum,
                                              @PathVariable( "pageSize" ) Integer pageSize ) {
        int total = 10;
        List info = UserListData.ziZhuCe( pageNum, pageSize, total );
        Map map = new HashMap();
        map.put( "total", total );
        map.put( "data", info );
        return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200, map );
    }
}
