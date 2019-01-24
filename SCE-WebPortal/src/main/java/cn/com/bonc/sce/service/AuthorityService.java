package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AuthorityDao;
import cn.com.bonc.sce.model.Authority;
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
     * 添加authority
     *
     * @param authority 信息
     * @return 是否添加成功
     */
    public RestRecord insertAuthority(Authority authority) {
        return authorityDao.insertAuthority(authority);
    }

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    public RestRecord getAll(Integer pageNum,Integer pageSize) {
        return authorityDao.getAll(pageNum,pageSize);
    }
}
