package cn.com.bonc.sce.mapper;

import java.util.Map;

public interface UserInfoMybatisMapper {

    int updateUserInfo( Map< String, Object > userInfo);

    int updatePassword( Map< String, Object > info);

    int addPassword( Map< String, Object > info);

}
