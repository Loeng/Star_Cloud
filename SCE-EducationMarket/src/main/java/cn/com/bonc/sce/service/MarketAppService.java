package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.MarketAppDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MarketAppService {

    private MarketAppDao marketAppDao;

    @Autowired
    public MarketAppService( MarketAppDao marketAppDao ) {
        this.marketAppDao = marketAppDao;
    }


}
