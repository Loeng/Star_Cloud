package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.InstitutionDao;
import cn.com.bonc.sce.model.Institution;
import cn.com.bonc.sce.model.InstitutionInfo;
import cn.com.bonc.sce.model.School;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/4/14.
 */
@Service
@Slf4j
public class InstitutionService {

    @Autowired
    InstitutionDao institutionDao;

    public RestRecord editInstitutionInfo(InstitutionInfo info){
        return institutionDao.editInstitutionInfo(info);
    }

    public RestRecord getInstitutionInfoByUserId(String userId){
        return institutionDao.getInstitutionInfoByUserId(userId);
    }

    /**
     * 添加教育局信息
     * @param institution 信息
     * @return 是否添加成功
     */
    public RestRecord addInstitution(Institution institution, String userId, Integer roleId ) {
        return institutionDao.addInstitution(institution,userId,roleId);
    }
}
