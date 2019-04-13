package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * 1
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:50
 */
@Repository
public interface UserDao extends JpaRepository< User, String > {

    public User findUserByUserId( String userId );

    // TODO 必须考虑并发修改的问题
    @Transactional
    @Modifying
    @Query( "UPDATE User user SET user.isFirstLogin = :isFirstLogin,user.lastLoginTime = sysdate WHERE user.userId = :userId" )
    public int updateUserLoginStatus( @Param( "userId" ) String userId, @Param( "isFirstLogin" ) Integer isFirstLogin );

    public User findUserByLoginName( String loginName );

    public User findUserByPhoneNumber( String phoneNumber );

    public User findByUserId( String userId );

    @Query( value = "select school_name from STARCLOUDPORTAL.SCE_ENTITY_SCHOOL where id=:Oid", nativeQuery = true )
    String findSchoolByOrganizationId( @Param( "Oid" ) String Oid );

    @Query( value = "SELECT grade FROM STARCLOUDPORTAL.SCE_INFO_STUDENT where user_id=:UserId", nativeQuery = true )
    String findGradeByUserId( @Param( "UserId" ) String UserId );

    @Transactional
    @Modifying
    @Query( value = "UPDATE STARCLOUDPORTAL.SCE_COMMON_USER SET LAST_LOGIN_TIME = LOGIN_TIME ,LOGIN_TIME=SYSDATE,LOGIN_COUNTS=NVL(LOGIN_COUNTS,0)+1 WHERE USER_ID = :userId", nativeQuery = true )
    public int updateUserLoginTimeAndCounts( @Param( "userId" ) String userId );


}
