package cn.com.bonc.sce.service;

import cn.com.bonc.sce.mapper.AppMarketMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AppMarketService {

    @Autowired
    AppMarketMapper appMarketMapper;

    public List<Map> getAppCount(){
        return appMarketMapper.selectAppCount();
    }

    public List<Map> userToDo(String userId){
        return appMarketMapper.selectUserToDo(userId);
    }

}
