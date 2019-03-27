package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.SchoolBean;
import cn.com.bonc.sce.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Charles on 2019/3/6.
 */
public interface UserMapper {

    int saveUser(UserBean userBean);

    int saveAccount(AccountBean account);

    int delUser(@Param("id")String id);

    int resetPwd(@Param("id")String id, @Param("pwd")String pwd);

    int updateLoginPermission(@Param("id")String id, @Param("newStatus")int newStatus);

    List<SchoolBean> getSchools4edu(@Param("id") long id);

    int delSchools4edu(@Param("id") long id, @Param("institutionId") long institutionId);

    List<Map> getInstitutions(@Param("id") String id, @Param("institutionName") String institutionName, @Param("loginPermissionStatus") String loginPermissionStatus);

    String selectUserIdByLoginName( String LOGIN_NAME );

    int saveUserSelective( UserBean userBean );

    int updateUserByUserIdSelective( UserBean userBean );

    int isExist(@Param("loginName") String loginName);

    String getPhone(@Param("loginName") String loginName);

    int updatePwdByName(@Param("loginName") String loginName, @Param("password") String password);
}
