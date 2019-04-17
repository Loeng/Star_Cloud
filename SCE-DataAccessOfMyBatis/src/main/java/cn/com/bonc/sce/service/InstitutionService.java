package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.UserAuditBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.InstitutionDao;
import cn.com.bonc.sce.dao.UserDao;
import cn.com.bonc.sce.model.Institution;
import cn.com.bonc.sce.model.InstitutionInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import cn.com.bonc.sce.tool.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/4/14.
 */
@Service
@Slf4j
@Transactional( rollbackFor = Exception.class )
public class InstitutionService {

    @Autowired
    private InstitutionDao institutionDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    public int editInstitutionInfo(String user_id, Date work_time, Date entry_time, String job_profession, String work_number) {
        return institutionDao.editInstitutionInfo( user_id,work_time,entry_time,job_profession,work_number);
    }

    public InstitutionInfo getInstitutionInfoByUserId(String userId){
        return institutionDao.getInstitutionInfoByUserId(userId);
    }

    public int addInstitutionInfo(String user_id, Date work_time, Date entry_time, String job_profession,String work_number,Integer is_delete) {
        return institutionDao.addInstitutionInfo( user_id,work_time,entry_time,job_profession,work_number,is_delete);
    }

    public RestRecord addInstitution(Institution institution,String userId,Integer roleId){
        Long Id = idWorker.nextId();
        String INSTITUTION_NAME = institution.getInstitutionName() == null ? "" : institution.getInstitutionName();
        String ADDRESS = institution.getAddress() == null ? "":institution.getAddress();
        String POSTCODE = institution.getPostcode() == null ? "" :institution.getPostcode();
        String PROVINCE = institution.getProvince() == null ? "":institution.getProvince();
        String CITY = institution.getCity() == null ? "" : institution.getCity();
        String DISTRICT = institution.getDistrict() == null ?"":institution.getDistrict();
        String INSTITUTION_CODE = institution.getInstitutionCode() == null ? "" :institution.getInstitutionCode();
        String TELEPHONE = institution.getTelephone() == null ? "":institution.getTelephone();
        String EMAIL = institution.getEmail() == null ?"":institution.getEmail();
        String HOMEPAGE = institution.getHomepage() == null ? "":institution.getHomepage();
        String PARENT_INSTITUTION = institution.getParentInstitution() == null ? "":institution.getParentInstitution();
        Integer IS_DELETE = 1;
        institutionDao.addInstitution(Id,INSTITUTION_NAME,ADDRESS,POSTCODE,PROVINCE,CITY,DISTRICT,INSTITUTION_CODE,TELEPHONE,EMAIL,HOMEPAGE,PARENT_INSTITUTION,IS_DELETE);

        userDao.updateOrganizationIdByUserId(Id,userId);

        UserAuditBean userAudit = new UserAuditBean();
        userAudit.setId(UUID.getUUID());
        userAudit.setUserId(userId);
        userAudit.setUserType(roleId);
        userAudit.setEntityId(Id);
        userAudit.setAuditStatus(0);

        userDao.saveUserAudit(userAudit);

        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

    public RestRecord updateInstitutionById(Institution institution) {
        int status = 0;
        status = institutionDao.updateInstitutionById(institution);

        return new RestRecord(200, MessageConstants.SCE_MSG_0200,status);
    }

    public Institution getInstitutionById(Long id){
        return institutionDao.getInstitutionById(id);
    }

    public RestRecord updateInstitutionInfo(Institution institution){
        institutionDao.updateInstitutionInfo(institution);

        UserAuditBean audit = userDao.findByUserAuditEntityId(institution.getId());

        UserAuditBean userAudit = new UserAuditBean();

        userAudit.setId(audit.getId());
        userAudit.setAuditStatus(0);
        userAudit.setAuditUserId("");
        userAudit.setRejectOpinion("");

        userDao.updateUserAuditById(userAudit);

        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

    public List<Map> getInstitutionInfoList(String INSTITUTION_NAME, String AUDIT_STATUS) {
        return institutionDao.getInstitutionInfoList(INSTITUTION_NAME,AUDIT_STATUS);
    }

}
