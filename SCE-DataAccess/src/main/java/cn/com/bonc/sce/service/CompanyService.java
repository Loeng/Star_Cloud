package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.CompanyDao;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.entity.CompanyInfo;
import cn.com.bonc.sce.entity.UserAudit;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2019/4/16.
 */
@Slf4j
@Service
@Transactional( rollbackFor = Exception.class )
public class CompanyService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private CompanyDao companyDao;

    public RestRecord addCompany(CompanyInfo company, String userId, Integer roleId) {
        company.setIsDelete(1L);
        companyDao.save(company);
        userInfoRepository.updateOrganizationIdByUserId(String.valueOf(company.getCompanyId()),userId);
        UserAudit userAudit = new UserAudit();
        userAudit.setUserId(userId);
        userAudit.setUserType(roleId);
        userAudit.setEntityId(String.valueOf(company.getCompanyId()));
        userAudit.setAuditStatus(0);
        userInfoRepository.save(userAudit);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }
}
