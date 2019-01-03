package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@RestController
@RequestMapping( "/schools" )
public class SchoolApiController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAll( @RequestParam( value = "pageNum", required = false, defaultValue = "0" ) Integer pageNum,
                              @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        try {
            return schoolService.getAll( pageNum, pageSize );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 406, MessageConstants.SCE_MSG_406, e );
        }
    }
}
