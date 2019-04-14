package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.InstitutionDao;
import cn.com.bonc.sce.model.Institution;
import cn.com.bonc.sce.model.InstitutionInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/14.
 */
@Service
@Slf4j
@Transactional( rollbackFor = Exception.class )
public class InstitutionService {

    @Autowired
    private InstitutionDao institutionDao;


    public int editInstitutionInfo(String user_id, Date work_time, Date entry_time, String job_profession, String work_number) {
        return institutionDao.editInstitutionInfo( user_id,work_time,entry_time,job_profession,work_number);
    }

    public InstitutionInfo getInstitutionInfoByUserId(String userId){
        return institutionDao.getInstitutionInfoByUserId(userId);
    }

    public RestRecord addInstitution(Institution institution,String userId,Integer roleId){
        IdWorker idWorker = new IdWorker();
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
        institutionDao.addInstitution(Id,INSTITUTION_NAME,ADDRESS,POSTCODE,PROVINCE,CITY,DISTRICT,INSTITUTION_CODE,TELEPHONE,EMAIL,HOMEPAGE,PARENT_INSTITUTION,IS_DELETE,userId,roleId);




        return new RestRecord(200, MessageConstants.SCE_MSG_0200);
    }

}
