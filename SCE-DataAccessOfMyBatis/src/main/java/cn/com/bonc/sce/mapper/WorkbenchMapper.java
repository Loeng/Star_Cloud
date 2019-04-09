package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.AccountBean;
import cn.com.bonc.sce.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkbenchMapper {

    int deleteAddress(Integer ID);

    List<Map> getAddress(String USER_ID);

    int defaultTotal(String USER_ID);

    int setDefault(String USER_ID);

    int updateAddress(Map<String, Object> addressInfo);

    int addAddress(Map<String, Object> addressInfo);

    List<Map> getStudentBinding(String USER_ID);

    int deleteStudentBinding(Integer ID);

    String queryStudentUserId(Map<String, Object> info);

    int queryIsBinding(@Param("PARENT_USER_ID") String PARENT_USER_ID, @Param("STUDENT_USER_ID") String STU_USER_ID);

    int addStudentBinding(@Param("PARENT_USER_ID") String PARENT_USER_ID, @Param("STUDENT_USER_ID") String STU_USER_ID);

    List<Map> getOrganization(@Param("USER_ID") String USER_ID, @Param("LOGIN_NAME") String LOGIN_NAME,
                              @Param("USER_NAME") String USER_NAME,@Param("GENDER") String GENDER);

    String queryUserId(Map<String, Object> info);

    int queryUserType(Map<String, Object> info);

    int addAgentInfo(Map<String, Object> info);

    long queryOrganizationId(Map<String, Object> info);

    int addOrganization(UserBean userBean);

    int updateOrganization(Map<String, Object> info);

    int updateAgentAddress(Map<String, Object> info);

    int saveAccount(AccountBean account);

    int deleteOrganization(String USER_ID);

}
