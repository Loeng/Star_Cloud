package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
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

    List<Map> selectTeacherList(@Param("organizationId") BigDecimal organizationId);

    BigDecimal selectOrganizationId(@Param("userId") String userId);
}
