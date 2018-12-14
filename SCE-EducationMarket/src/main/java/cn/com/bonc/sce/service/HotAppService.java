package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.HotAppDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HotAppService {

    private HotAppDao hotAppDao;

    @Autowired
    public HotAppService( HotAppDao hotAppDao ) {
        this.hotAppDao = hotAppDao;
    }




}
