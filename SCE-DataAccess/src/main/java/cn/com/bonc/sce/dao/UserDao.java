package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:50
 */
@Repository
public interface UserDao extends JpaRepository< User, String > {
    public User findUserByUserId( String userId );

    // TODO 必须考虑并发修改的问题
    @Modifying
    @Query( "UPDATE User user SET user.loginCounts = :counts WHERE user.userId = :userId" )
    public int updateUserLoginCount( @Param( "counts" ) int counts, @Param( "userId" ) String userId );

    public User findUserByLoginName( String loginName );
}
