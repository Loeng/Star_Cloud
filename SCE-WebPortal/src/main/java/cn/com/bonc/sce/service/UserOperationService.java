package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.TeacherRecommendDao;
import cn.com.bonc.sce.dao.UserOperationDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Slf4j
@Service
public class UserOperationService {

    @Autowired
    private UserOperationDao userOperationDao;

   /* @Autowired
    public UserOperationService( UserOperationDao userOperationDao ) {
        this.userOperationDao = userOperationDao;
    }*/
    public RestRecord addUserInfo(Map< String, Object >  userInfo ) {
        return userOperationDao.addUserInfo( userInfo );
    }

    public RestRecord updateUserInfoById(Map< String, Object >  userInfo,String userId ) {
        return userOperationDao.updateUserInfoById(userInfo, userId);

    }

    public RestRecord deleteUserInfoById( String userId ) {
        return userOperationDao.deleteUserInfoById( userId );

    }

    public RestRecord selectUserInfoById( String userId) {
        return userOperationDao.selectUserInfoById( userId );

    }

}
