package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.AuthorityDao;
import cn.com.bonc.sce.entity.Authority;
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

    public RestRecord insertAuthority(Authority authority){
        authority.setIsDelete( 1 );
        return new RestRecord(200, authorityDao.save( authority ));
    }

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    public RestRecord getAll(Integer pageNum, Integer pageSize) {
        pageNum--;
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page< Authority > page;
        page = authorityDao.findByIsDelete( 1,pageable );
        Map< String, Object > info = new HashMap<>();
        List< Authority > list = page.getContent();
        info.put( "total", page.getTotalElements() );
        info.put( "info", list );
        return new RestRecord( 200, info );
    }
}
