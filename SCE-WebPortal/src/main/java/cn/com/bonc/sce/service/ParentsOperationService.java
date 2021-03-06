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
import java.util.List;

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
    public RestRecord sendSecurityPhoneValid( String phone ) {
        String valid;
        try {
            //valid = VaildSecurityUtils.randomStr();
            valid="123456";
            VaildSecurityUtils.addValid(getAccountEncryptionCode(phone,valid));
            SendMessage.postMsgToPhone(valid,phone);
        } catch ( UnsupportedEncodingException e ) {
            return new RestRecord(409,WebMessageConstants.SCE_PORTAL_MSG_409);
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
        String mainParentValid = getAccountEncryptionCode( parentsInfo.getMainParentPhone(), parentsInfo.getMainParentPhoneValid() );
        String parentValid = getAccountEncryptionCode( parentsInfo.getParentPhone(), parentsInfo.getParentPhoneValid() );
        if ( !VaildSecurityUtils.checkValid( mainParentValid ) ){
            return new RestRecord( 413, WebMessageConstants.SCE_PORTAL_MSG_413 );
        }
        if ( !VaildSecurityUtils.checkValid( parentValid ) ){
            return new RestRecord( 411, WebMessageConstants.SCE_PORTAL_MSG_411 );
        }
        VaildSecurityUtils.delValid( mainParentValid );
        VaildSecurityUtils.delValid( parentValid );
        return parentsOperationDao.insertParentsInfo(parentsInfo);
    }

    /**
     * 用户注册
     *
     * @param info 注册信息
     * @return 添加结果
     */
    public RestRecord insertUsersInfo( ParentsInfo info ){
        String parentValid = getAccountEncryptionCode( info.getParentPhone(), info.getParentPhoneValid() );
        if ( !VaildSecurityUtils.checkValid( parentValid ) ){
            return new RestRecord( 411, WebMessageConstants.SCE_PORTAL_MSG_411 );
        }
        VaildSecurityUtils.delValid( parentValid );
        return parentsOperationDao.insertUsersInfo(info);
    }

    /**
     * 获取审核列表
     *
     * @return 结果
     */
    public RestRecord getExamine(Integer pageNum,Integer pageSize){
        return parentsOperationDao.getExamine(pageNum,pageSize);
    }

    /**
     * 审核通过
     *
     * @param list 通过列表
     * @return 结果
     */
    public RestRecord examine(List<String> list){
        return parentsOperationDao.examine( list );
    }

    private String getAccountEncryptionCode( String str1, String str2 ) {
        StringBuilder sb = new StringBuilder( str1 );
        sb.append( str2 );
        return DigestUtils.md5DigestAsHex( sb.toString().getBytes() );
    }
}
