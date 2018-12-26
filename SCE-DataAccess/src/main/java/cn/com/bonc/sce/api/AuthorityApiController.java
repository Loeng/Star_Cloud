package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AuthorityDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 机构
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@RestController
@RequestMapping( "/authoritys" )
public class AuthorityApiController {
    @Autowired
    private AuthorityDao authorityDao;

    /**
     * 获取机构
     *
     * @return 获取机构
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAll() {
        try {
            return new RestRecord( 200, authorityDao.findByIsDelete(0) );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}
