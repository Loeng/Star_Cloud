package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.StudentParentRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 学生家长对应
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
public interface StudentParentRelDao extends JpaRepository< StudentParentRel, Integer > {
    @Override
    StudentParentRel save( StudentParentRel user );

    @Query( value = "SELECT\n" +
            "\tC.PHONE_NUMBER \n" +
            "FROM\n" +
            "\tSTARCLOUDPORTAL.SCE_COMMON_USER A\n" +
            "\tINNER JOIN STARCLOUDPORTAL.SCE_INFO_STUDENT_PARENT_REL B ON A.USER_ID = B.STUDENT_USER_ID\n" +
            "\tINNER JOIN STARCLOUDPORTAL.SCE_COMMON_USER C ON B.PARENT_USER_ID = C.USER_ID \n" +
            "WHERE\n" +
            "\tB.IS_MAIN = 1 AND A.LOGIN_NAME = ?1",nativeQuery=true )
    String selectMainParentPhone( String studentAccount );
}