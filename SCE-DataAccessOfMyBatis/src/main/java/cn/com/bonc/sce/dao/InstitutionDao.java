package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.InstitutionMapper;
import cn.com.bonc.sce.model.InstitutionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Administrator on 2019/4/14.
 */
@Repository
public class InstitutionDao {

    @Autowired
    private InstitutionMapper institutionMapper;

    public int editInstitutionInfo(String user_id, Date work_time, Date entry_time, String job_profession, String work_number) {
        return institutionMapper.editInstitutionInfo( user_id,work_time,entry_time,job_profession,work_number);
    }

    public InstitutionInfo getInstitutionInfoByUserId(String userId){
        return institutionMapper.getInstitutionInfoByUserId(userId);
    }

    public int addInstitution(Long id,String institution_name,String address,String postcode,String province,String city,String district,String institution_code,String telephone,String email,String  homepage,String  parent_institution,Integer is_delete,String userId,Integer roleId){
        return institutionMapper.addInstitution(id,institution_name,address,postcode,province,city,district,institution_code,telephone,email,homepage,parent_institution,is_delete,userId,roleId);
    }
}
