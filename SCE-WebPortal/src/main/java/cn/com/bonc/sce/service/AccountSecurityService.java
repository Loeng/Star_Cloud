package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AccountSecurityDao;
import cn.com.bonc.sce.model.AccountSecurity;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.VaildSecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Slf4j
@Service
public class AccountSecurityService {

    private AccountSecurityDao accountSecurityDao;

    @Autowired
    public AccountSecurityService( AccountSecurityDao accountSecurityDao ) {
        this.accountSecurityDao = accountSecurityDao;
    }

    /**
     * 获取安全验证信息
     *
     * @param phone 手机号
     * @return 验证码
     */
    public RestRecord getSecurityVaildInfo( String phone ) {
        RestRecord rr = accountSecurityDao.getSecurityVaildInfo( phone );
        if ( rr.getCode() == 200 ) {
            //验证码
            String vaild = rr.getMsg();
            //加密后的验证码
            String vaild_ = getAccountEncryptionCode( phone, vaild );
            VaildSecurityUtils.addVaild( vaild_ );
            return rr;
        }
        return rr;
    }

    /**
     * 验证安全信息
     *
     * @param phone 手机号
     * @param vaild 验证码
     * @return 验证结果和安全码
     */
    public RestRecord vaildInfo( String phone, String vaild ) {
        String vaild_ = getAccountEncryptionCode( phone, vaild );
        if ( VaildSecurityUtils.checkVaild( vaild_ ) ) {
            VaildSecurityUtils.delVaild(vaild_);
            //创建安全码
            String randomStr = VaildSecurityUtils.randomStr();
            VaildSecurityUtils.addCode( getAccountEncryptionCode(phone,randomStr) );
            return new RestRecord( 200, "success", randomStr);
        }
        return new RestRecord( 200, "error" );
    }

    /**
     * 修改账号信息
     *
     * @param accountSecurity 安全码h和账号信息
     * @return 修改结果
     */
    public RestRecord updateAccount( AccountSecurity accountSecurity ) {
        String code = getAccountEncryptionCode(
                accountSecurity.getPhone(), accountSecurity.getCode() );
        if ( VaildSecurityUtils.checkCode( code ) ) {
            VaildSecurityUtils.delCode(code);
            return accountSecurityDao.updateAccount( accountSecurity );
        }
        else return new RestRecord( 200, "请重新进行安全验证" );
    }

    private String getAccountEncryptionCode( String str1, String str2 ) {
        StringBuilder sb = new StringBuilder( str1 );
        sb.append( str2 );
        return DigestUtils.md5DigestAsHex( sb.toString().getBytes() );
    }
}
