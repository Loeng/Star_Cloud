package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.ParentsOperationDao;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //RestRecord rr = parentsOperationDao.getSecurityVaildInfo( phone );
        return null;
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
}
