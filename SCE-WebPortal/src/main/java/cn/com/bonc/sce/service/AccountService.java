package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.AccountDao;
import cn.com.bonc.sce.model.Account;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.SendMessage;
import cn.com.bonc.sce.tool.VaildSecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
     * @param phone 手机号
     * @return 验证码
     */
    public RestRecord sendSecurityPhoneValid( String phone ) {
        String valid = "123456";
        StringBuffer sb = new StringBuffer();
        try {
            //valid = VaildSecurityUtils.randomStr();
            VaildSecurityUtils.addValid( getAccountEncryptionCode( phone, valid ) );
            SendMessage.postMsgToPhone( valid, phone );
        } catch ( UnsupportedEncodingException e ) {
            return new RestRecord( 409, WebMessageConstants.SCE_PORTAL_MSG_409 );
        }

        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = valid.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[] { '0', '1', '2', '3', '4', '5',
                    '6', '7' , '8', '9', 'A', 'B', 'C', 'D', 'E','F' };
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new RestRecord( 200, sb );
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
        if ( VaildSecurityUtils.checkValid( valid_ ) ) {
            VaildSecurityUtils.delValid( valid_ );
            //创建安全码
            String randomStr = VaildSecurityUtils.randomStr();
            VaildSecurityUtils.addCode( getAccountEncryptionCode( phone, randomStr ) );
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, randomStr );
        }
        return new RestRecord( 411, WebMessageConstants.SCE_PORTAL_MSG_411 );
    }

    /**
     * 修改账号信息
     *
     * @param accountSecurity 安全码和账号信息
     * @return 修改结果
     */
    public RestRecord updateAccount( Account accountSecurity ) {
        Integer successCode = 200;
        if ( !StringUtils.isEmpty( accountSecurity.getUserId() ) &&
                !StringUtils.isEmpty( accountSecurity.getPassword() ) &&
                !StringUtils.isEmpty( accountSecurity.getNewPassword() ) ) {
            RestRecord rr = accountSecurityDao.updateAccount( accountSecurity );
            if ( rr.getCode() == successCode ) {
                rr.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
            }
            return rr;
        }
        //加密加工
        //accountSecurity.getPassword();
        String code;
        if ( StringUtils.isEmpty( accountSecurity.getPhone() ) || StringUtils.isEmpty( accountSecurity.getCode() ) ) {
            return new RestRecord( 412, WebMessageConstants.SCE_PORTAL_MSG_412 );
        } else {
            code = getAccountEncryptionCode(
                    accountSecurity.getPhone(), accountSecurity.getCode() );
        }
        if ( VaildSecurityUtils.checkValid( code ) ) {
            VaildSecurityUtils.delValid( code );
            RestRecord rr = accountSecurityDao.updateAccount( accountSecurity );
            if ( rr.getCode() == successCode ) {
                rr.setMsg( WebMessageConstants.SCE_PORTAL_MSG_200 );
            }
            return rr;
        } else {
            return new RestRecord( 412, WebMessageConstants.SCE_PORTAL_MSG_412 );
        }
    }

    private String getAccountEncryptionCode( String str1, String str2 ) {
        StringBuilder sb = new StringBuilder( str1 );
        sb.append( str2 );
        return DigestUtils.md5DigestAsHex( sb.toString().getBytes() );
    }
}
