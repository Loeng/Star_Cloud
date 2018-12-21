package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.model.Account.Account;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.VaildSecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 账号安全信息相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/14/12 12:00
 */
@Slf4j
@Service
public class AccountService {

    private AccountDao accountSecurityDao;

    @Autowired
    public AccountService( AccountDao accountSecurityDao ) {
        this.accountSecurityDao = accountSecurityDao;
    }

    /**
     * 获取安全验证信息
     *
     * @param userAccount 手机号
     * @return 验证码
     */
    public RestRecord sendSecurityPhoneValid( String userAccount ) {
        /*RestRecord rr = accountSecurityDao.getUserAccountPhone( userAccount );
        if ( rr.getCode() == 200 ) {
            //验证码
            String vaild = rr.getMsg();
            //加密后的验证码
            String vaild_ = getAccountEncryptionCode( phone, vaild );
            VaildSecurityUtils.addVaild( vaild_ );
            return rr;
        }*/
        return null;
    }

    /**
     * 验证安全信息
     *
     * @param phone 手机号
     * @param valid 验证码
     * @return 验证结果和安全码
     */
    public RestRecord validInfo( String phone, String valid ) {
        String valid_ = getAccountEncryptionCode( phone, valid );
        if ( VaildSecurityUtils.checkVaild( valid_ ) ) {
            VaildSecurityUtils.delVaild( valid_ );
            //创建安全码
            String randomStr = VaildSecurityUtils.randomStr();
            VaildSecurityUtils.addCode( getAccountEncryptionCode( phone, randomStr ) );
            return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200, randomStr );
        }
        return new RestRecord( 411, PortalMessageConstants.SCE_PORTAL_MSG_411 );
    }

    /**
     * 修改账号信息
     *
     * @param accountSecurity 安全码和账号信息
     * @return 修改结果
     */
    public RestRecord updateAccount( Account accountSecurity ) {
        Integer successCode = 200;
        //加密加工
        accountSecurity.getPassword();
        String code = getAccountEncryptionCode(
                accountSecurity.getPhone(), accountSecurity.getCode() );
        if ( VaildSecurityUtils.checkCode( code ) ) {
            VaildSecurityUtils.delCode( code );
            RestRecord rr = accountSecurityDao.updateAccount( accountSecurity.getPhone(), accountSecurity.getPassword() );
            if ( rr.getCode() == successCode ) {
                rr.setMsg( PortalMessageConstants.SCE_PORTAL_MSG_200 );
            }
            return rr;
        } else {
            return new RestRecord( 412, PortalMessageConstants.SCE_PORTAL_MSG_412 );
        }
    }

    private String getAccountEncryptionCode( String str1, String str2 ) {
        StringBuilder sb = new StringBuilder( str1 );
        sb.append( str2 );
        return DigestUtils.md5DigestAsHex( sb.toString().getBytes() );
    }
}
