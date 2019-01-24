package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.Authority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Repository
public interface AuthorityDao extends JpaRepository< Authority, Integer > {
    Page< Authority > findByIsDelete( Integer isDelete ,Pageable pageable);

    @Query( value = "SELECT A.USER_ID,A.USER_NAME,A.LOGIN_TIME,A.LOGIN_PERMISSION_STATUS,B.ID,B.INSTITUTION_NAME,B.IS_DELETE,B.PID,B.DISTRICT,B.CITY,B.PROVINCE,B.COUNTY,B.ADDRESS,B.TELEPHONE FROM STARCLOUDPORTAL.SCE_COMMON_USER A\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_ENTITY_INSTITUTION B ON A.ORGANIZATION_ID=B.ID\n" +
            "WHERE A.USER_TYPE=7 AND  ORGANIZATION_ID IS NOT NULL",countQuery="select count(*) FROM STARCLOUDPORTAL.SCE_COMMON_USER A\n" +
            "LEFT JOIN STARCLOUDPORTAL.SCE_ENTITY_INSTITUTION B ON A.ORGANIZATION_ID=B.ID\n" +
            "WHERE A.USER_TYPE=7 AND  ORGANIZATION_ID IS NOT NULL",nativeQuery = true)
    Page< Map<String,Object> > getUser( Pageable pageable);
}
