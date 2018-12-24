package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.ParentsOperationDao;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.SendMessage;
import cn.com.bonc.sce.tool.VaildSecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class ParentsOperationService {

    private ParentsOperationDao parentsOperationDao;

    @Autowired
    public ParentsOperationService( ParentsOperationDao parentsOperationDao ) {
        this.parentsOperationDao = parentsOperationDao;
    }

    /**
     * 获取安全验证信息
     *
     * @param phone 手机号
     * @return 验证码
     */
    public RestRecord getSecurityVaildInfo( String phone ) {
        String valid;
        try {
            valid = VaildSecurityUtils.randomStr();
            SendMessage.postMsgToPhone(valid,phone);
        } catch ( UnsupportedEncodingException e ) {
            return new RestRecord(409, WebMessageConstants.SCE_PORTAL_MSG_409);
        }
        return new RestRecord(200,valid);
    }

    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @return 添加结果
     */
    public RestRecord insertParentsInfo( ParentsInfo parentsInfo ){
        return parentsOperationDao.insertParentsInfo(parentsInfo);
    }

    private String getAccountEncryptionCode( String str1, String str2 ) {
        StringBuilder sb = new StringBuilder( str1 );
        sb.append( str2 );
        return DigestUtils.md5DigestAsHex( sb.toString().getBytes() );
    }
}
