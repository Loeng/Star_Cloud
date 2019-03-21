package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.dao.AuthorityDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.entity.Account;
import cn.com.bonc.sce.entity.Authority;
import cn.com.bonc.sce.entity.user.User;
import cn.com.bonc.sce.model.Secret;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IDUtil;
import cn.com.bonc.sce.utils.UUID;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class AuthorityService {
    @Autowired
    private AuthorityDao authorityDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;

    @PersistenceContext
    private EntityManager entityManager;

    public RestRecord insertAuthority( Authority authority ) {
        authority.setIsDelete( 1 );
        authority = authorityDao.save( authority );

        User user = new User();
        String secret = Secret.ES256GenerateSecret();
        String loginName = IDUtil.createID( "jg_" );
        user.setSecret( secret );
        user.setLoginName( loginName );
        user.setUserType( 7 );
        user.setIsDelete( 1 );
        user.setOrganizationId( authority.getId().toString() );
        user.setLoginPermissionStatus( 1 );
        user.setIsFirstLogin( 0 );
        userDao.save( user );

        Account account = new Account();
        account.setUserId( user.getUserId() );
        account.setPassword( "star123!" );
        account.setIsDelete( 1 );
        accountDao.save( account );


        return new RestRecord( 200, authority );
    }

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    public RestRecord getAll( Integer pageNum, Integer pageSize ) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< Authority > page;
        page = authorityDao.findByIsDelete( 1, pageable );
        Map< String, Object > info = new HashMap<>();
        List< Authority > list = page.getContent();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    public RestRecord getUser( Integer pageNum, Integer pageSize ) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< Map< String, Object > > page;
        page = authorityDao.getUser( pageable );
        Map< String, Object > info = new HashMap<>();
        List< Map< String, Object > > list = page.getContent();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }

    /**
     * 获取机构[条件查询]
     *
     * @return 获取机构
     */
    public RestRecord getUserByCondition( String edu_id, String edu_name, String to_login, Integer pageNum, Integer pageSize ) {
        StringBuilder sql = new StringBuilder( "SELECT A.USER_ID,A.USER_NAME,A.LOGIN_NAME,A.LOGIN_TIME,A.LOGIN_PERMISSION_STATUS,A.IS_FIRST_LOGIN,B.ID,B.INSTITUTION_NAME,B.IS_DELETE,B.PID,B.DISTRICT,B.CITY,B.PROVINCE,B.COUNTY,B.ADDRESS,B.TELEPHONE FROM STARCLOUDPORTAL.SCE_COMMON_USER A LEFT JOIN STARCLOUDPORTAL.SCE_ENTITY_INSTITUTION B ON A.ORGANIZATION_ID=B.ID WHERE A.USER_TYPE=7  AND  A.IS_DELETE=1" );
        //组织编号是否为空
        if ( !StringUtils.isEmpty( edu_id ) ) {
            sql.append( " AND ORGANIZATION_ID =" ).append( edu_id );
        } else {
            sql.append( " AND  ORGANIZATION_ID IS NOT NULL " );
        }
        //机构名称是否为空
        if ( !StringUtils.isEmpty( edu_name ) ) {
            sql.append( " AND B.INSTITUTION_NAME LIKE '%" ).append( edu_name ).append( "%' " );
        }
        //是否允许登陆 为空查全部
        if ( !StringUtils.isEmpty( to_login ) ) {
            sql.append( " AND A.LOGIN_PERMISSION_STATUS = " ).append( to_login );
        }

        Session session = entityManager.unwrap( org.hibernate.Session.class );
        NativeQuery query = session.createNativeQuery( sql.toString() );
        query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
        int start = ( pageNum - 1 ) * pageSize;
        int total = query.getResultList().size();
        //判断分页
        if ( start < total && pageSize > 0 ) {
            query.setFirstResult( start );
            query.setMaxResults( pageSize );
        }
        Map< String, Object > temp = new HashMap<>( 3 );
        temp.put( "info", query.getResultList() );
        temp.put( "totalPage", ( total + pageSize - 1 ) / pageSize );
        temp.put( "total", total );
        return new RestRecord( 200, temp );
    }
}
