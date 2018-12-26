package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AuthorityDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@Service
public class AuthorityService {
    @Autowired
    private AuthorityDao authorityDao;

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    public RestRecord getAll() {
        return authorityDao.getAll();
    }
}
