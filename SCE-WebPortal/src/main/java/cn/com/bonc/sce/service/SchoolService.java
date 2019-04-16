package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.SchoolDao;
import cn.com.bonc.sce.model.School;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@Service
public class SchoolService {
    @Autowired
    private SchoolDao schoolDao;

    /**
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    public RestRecord insertSchool(School school) {
        return schoolDao.insertSchool(school);
    }

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    public RestRecord getAll(Integer pageNum,Integer pageSize) {
        return schoolDao.getAll(pageNum,pageSize);
    }

    public RestRecord saveSchool( Map map ) {

         return schoolDao.saveSchool(map);
    }

    /**
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    public RestRecord addSchool( School school,String userId,Integer roleId ) {
        return schoolDao.addSchool(school,userId,roleId);
    }

    public RestRecord getSchoolById(Integer id){
        return schoolDao.getSchoolById(id);
    }

    public RestRecord updateSchoolById(School school){
        return schoolDao.updateSchoolById(school);
    }

    public RestRecord updateSchoolInfo(School school,String userId,Integer roleId){
        return schoolDao.updateSchoolInfo(school,userId,roleId);
    }

    public RestRecord getSchoolInfoList(String SCHOOL_NAME,String SCHOOL_TYPE,String AUDIT_STATUS,Integer pageNum ,Integer pageSize){
        return schoolDao.getSchoolInfoList(SCHOOL_NAME,SCHOOL_TYPE,AUDIT_STATUS,pageNum,pageSize);
    }

}
