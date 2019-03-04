package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AgencyDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Charles on 2019/3/4.
 */
@Service
@Slf4j
public class AgencyService {

    @Autowired
    private AgencyDao agencyDao;

    public int editActivity(Integer id, Integer isActivate){
        return agencyDao.editActivity(id,isActivate);
    }

    public int editInfo(Integer id,String agentName, String province, String city, String area){
        return agencyDao.editInfo(id,agentName,province,city,area);
    }
}
