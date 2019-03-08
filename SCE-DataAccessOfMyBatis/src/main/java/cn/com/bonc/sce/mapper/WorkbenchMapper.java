package cn.com.bonc.sce.mapper;

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

    int queryIsBinding(@Param("PARENT_USER_ID") String PARENT_USER_ID , @Param("STUDENT_USER_ID") String STU_USER_ID);

    int addStudentBinding(@Param("PARENT_USER_ID") String PARENT_USER_ID , @Param("STUDENT_USER_ID") String STU_USER_ID);

}
