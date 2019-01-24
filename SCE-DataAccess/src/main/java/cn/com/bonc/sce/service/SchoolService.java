package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.SchoolDao;
import cn.com.bonc.sce.entity.School;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    public RestRecord insertSchool(School school){
        school.setIsDelete( 1 );
        return new RestRecord(200, schoolDao.save( school ));
    }

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    public RestRecord getAll( Integer pageNum, Integer pageSize ) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< School > page;
        page = schoolDao.findByIsDelete( 1,pageable );
        Map< String, Object > info = new HashMap<>();
        List< School > list = page.getContent();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }
}
