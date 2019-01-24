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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public RestRecord insertAuthority(Authority authority){
        User user = new User();
        String secret = Secret.generateSecret();
        String loginName = IDUtil.createID( "jg_" );
        user.setSecret( secret );
        user.setLoginName( loginName );
        user.setUserType( 7 );
        user.setIsDelete( 1 );
        userDao.save( user );

        Account account = new Account();
        account.setUserId( user.getUserId() );
        account.setPassword( "star123!" );
        account.setIsDelete( 1 );
        accountDao.save( account );

        authority.setIsDelete( 1 );
        return new RestRecord(200, authorityDao.save( authority ));
    }

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    public RestRecord getAll(Integer pageNum, Integer pageSize) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< Authority > page;
        page = authorityDao.findByIsDelete( 1,pageable );
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
    public RestRecord getUser(Integer pageNum, Integer pageSize) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< Map<String,Object> > page;
        page = authorityDao.getUser( pageable );
        Map< String, Object > info = new HashMap<>();
        List< Map<String,Object> > list = page.getContent();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }
}
