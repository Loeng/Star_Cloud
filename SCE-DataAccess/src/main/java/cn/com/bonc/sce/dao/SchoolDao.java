package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Repository
public interface SchoolDao extends JpaRepository< School, Integer > {


    Page<School> findByIsDelete( Integer isDelete , Pageable pageable);

    @Override
    School save( School school);

    @Query(value = " select * FROM \n" +
            " (SELECT USER_ID,LOGIN_NAME,LOGIN_PERMISSION_STATUS,IS_FIRST_LOGIN,ORGANIZATION_ID FROM  STARCLOUDPORTAL.SCE_COMMON_USER  WHERE user_type= 3 AND ORGANIZATION_ID  IS NOT NULL) \n" +
            "  u LEFT  JOIN \n" +
            "STARCLOUDPORTAL.SCE_ENTITY_SCHOOL s \n" +
            "ON  u.ORGANIZATION_ID = s.id   ",nativeQuery = true)
    Page<School> selectAllSchool( Integer isDelete , Pageable pageable);
}
