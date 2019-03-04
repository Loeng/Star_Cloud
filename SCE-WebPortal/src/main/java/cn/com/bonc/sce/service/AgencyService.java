package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AgencyDao;
import cn.com.bonc.sce.rest.RestRecord;
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

    public RestRecord editActivity(Integer id,Integer isActivate) {
        return agencyDao.editActivity(id,isActivate);
    }

    public RestRecord editInfo(String json){
        return agencyDao.editInfo(json);
    }
}
