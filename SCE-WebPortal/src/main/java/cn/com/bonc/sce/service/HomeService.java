package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.FeignUserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:54
 */
@Slf4j
@Service
public class HomeService {
    private FeignUserDao userDao;

    @Autowired
    public HomeService( FeignUserDao userDao ) {
        this.userDao = userDao;
    }
}
