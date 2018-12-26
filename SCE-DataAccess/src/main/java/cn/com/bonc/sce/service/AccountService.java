package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账号安全信息相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class AccountService {
    @Autowired
    private AccountDao accountSecurityDao;

    /**
     * 修改账号信息
     *
     * @param phone phone
     * @param password password
     * @return 修改结果
     */
    public RestRecord updateAccount( String phone,String password) {
        return new RestRecord( 200, accountSecurityDao.updateAccount( phone, password ) );
    }
}
