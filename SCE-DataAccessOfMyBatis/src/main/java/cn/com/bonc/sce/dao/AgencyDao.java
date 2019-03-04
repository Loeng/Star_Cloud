package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.AgencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Charles on 2019/3/4.
 */
@Repository
public class AgencyDao {

    @Autowired
    private AgencyMapper agencyMapper;

    public int editActivity(Integer id,Integer isActivate) {
        return agencyMapper.editActivity(id,isActivate);
    }

    public int editInfo(Integer id,String agentName,String province,String city, String area){
        return agencyMapper.editInfo(id,agentName,province,city,area);
    }
}
