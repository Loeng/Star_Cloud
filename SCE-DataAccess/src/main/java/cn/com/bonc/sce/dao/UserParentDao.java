package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Notification;
import cn.com.bonc.sce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 用户表
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Repository
@Transactional
public interface UserParentDao extends JpaRepository< User, Integer > {

    @Override
    User save( User user );

    @Query( value = "SELECT CU.USER_ID AS userId,CU.USER_ACCOUNT AS userAccount,CUP.PASSWORD AS password FROM STARCLOUDPORTAL.COMMON_USER AS CU\n" +
            "LEFT JOIN STARCLOUDPORTAL.COMMON_USER_PASSWORD AS CUP ON CU.USER_ID=CUP.USER_ID\n" +
            "WHERE CU.USER_ACCOUNT=?1 AND CU.IS_DELETE=0",nativeQuery=true )
    List<Map<String,String>> selectUserInfo(String userAccount);
}