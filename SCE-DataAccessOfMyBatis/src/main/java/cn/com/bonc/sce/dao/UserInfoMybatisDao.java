package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.UserInfoMybatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserInfoMybatisDao {

    @Autowired( required = false )
    private UserInfoMybatisMapper userInfoMybatisMapper;

    public int updateUserInfo (Map< String, Object > userInfo){
        return userInfoMybatisMapper.updateUserInfo(userInfo);
    }

    public int updatePassword (Map< String, Object > userInfo){
        return userInfoMybatisMapper.updatePassword(userInfo);
    }

    public int addPassword (Map< String, Object > userInfo){
        return userInfoMybatisMapper.addPassword(userInfo);
    }

}
