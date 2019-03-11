package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.UserOperationMybatisDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserOperationMybatisService {

    @Autowired
    private UserOperationMybatisDao userOperationMybatisDao;

    public RestRecord updateUserInfoById(Map< String, Object > userInfo ) {
        return userOperationMybatisDao.updateUserInfoById( userInfo );
    }

    public RestRecord updatePassword(Map< String, Object > info ) {
        return userOperationMybatisDao.updatePassword( info );
    }

}
