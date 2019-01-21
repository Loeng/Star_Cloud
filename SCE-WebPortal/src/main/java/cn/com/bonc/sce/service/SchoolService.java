package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.SchoolDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@Service
public class SchoolService {
    @Autowired
    private SchoolDao schoolDao;

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    public RestRecord getAll(Integer pageNum,Integer pageSize) {
        return schoolDao.getAll(pageNum,pageSize);
    }
}
