package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.CompanyDao;
import cn.com.bonc.sce.dao.UserInfoRepository;
import cn.com.bonc.sce.entity.CompanyInfo;
import cn.com.bonc.sce.entity.UserAudit;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.utils.UUID;
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
        String uuid = UUID.getUUID();
        company.setCompanyId(uuid);
        companyDao.save(company);
        userInfoRepository.updateOrganizationIdByUserId(uuid,userId);
        UserAudit userAudit = new UserAudit();
        userAudit.setUserId(userId);
        userAudit.setUserType(roleId);
        userAudit.setEntityId(uuid);
        userAudit.setAuditStatus(0);
        userInfoRepository.save(userAudit);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

    public RestRecord updateCompanyByCompanyId(CompanyInfo companyInfo){
        int status = 0;
        String companyId = companyInfo.getCompanyId() == null ? "":companyInfo.getCompanyId();
        String companyAddress = companyInfo.getCompanyAddress() == null ? "":companyInfo.getCompanyAddress();
        String postcode = companyInfo.getPostcode() == null ? "" : companyInfo.getPostcode();
        String phone = companyInfo.getPhone() == null ? "" : companyInfo.getPhone();
        String companyEmail = companyInfo.getCompanyEmail() == null ? "" : companyInfo.getCompanyEmail();
        String companyWebsite = companyInfo.getCompanyWebsite() == null ? "" : companyInfo.getCompanyWebsite();
        status = companyDao.updateCompanyByCompanyId(companyId,companyAddress,postcode,phone,companyEmail,companyWebsite);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200,status);
    }

    public CompanyInfo getCompanyByCompanyId(String companyId){
        return companyDao.findByCompanyId(companyId);
    }

    public RestRecord updateCompany(CompanyInfo company) {
        company.setIsDelete(1L);
        companyDao.save(company);
        UserAudit audit = userInfoRepository.findByEntityId(company.getCompanyId());
        UserAudit userAudit = new UserAudit();
        userAudit.setId(audit.getId());
        userAudit.setUserId(audit.getUserId());
        userAudit.setUserType(audit.getUserType());
        userAudit.setEntityId(company.getCompanyId());
        userAudit.setAuditStatus(0);
        userInfoRepository.save(userAudit);
        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

}
