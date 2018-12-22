package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.UserListDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author jc_D
 * @description
 * @date 2018/12/22
 **/
@Slf4j
@Service
public class UserListService {
    private UserListDao userListDao;

    @Autowired
    public UserListService( UserListDao userListDao ) {
        this.userListDao = userListDao;
    }

    /**
     * 查询全部用户信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public RestRecord getAllUserInfo( @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
                                      @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return userListDao.getAllUserInfo( pageNum, pageSize );
    }

    /**
     * 根据角色id查询用户信息
     *
     * @param roleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public RestRecord getUserInfoByRole( String roleId, String pageNum, String pageSize ) {
        return userListDao.getUserInfoByRole( roleId, pageNum, pageSize );
    }


    /**
     * 根据条件查询
     *
     * @param conditionMap
     * @param pageNum
     * @param pageSize
     * @return
     */
    public RestRecord getUserInfoByCondition( Map conditionMap, String pageNum, String pageSize ) {
        return userListDao.getUserInfoByCondition( conditionMap, pageNum, pageSize );
    }

}
