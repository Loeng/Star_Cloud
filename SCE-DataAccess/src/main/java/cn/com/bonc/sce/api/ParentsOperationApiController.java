package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.ParentsInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.ParentsOperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家长操作
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 9:00
 */
@Slf4j
@RestController
@RequestMapping( "/parents-operation" )
public class ParentsOperationApiController {

    @Autowired
    private ParentsOperationService parentsOperationService;

    /**
     * 添加家长信息
     *
     * @param parentsInfo 家长信息
     * @return 添加结果
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord insertParentsInfo( @RequestBody ParentsInfo parentsInfo ) {
        try {
            return parentsOperationService.insertParentsInfo( parentsInfo );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    /**
     * 获取审核列表
     *
     * @return 结果
     */
    @GetMapping( "/examine" )
    @ResponseBody
    public RestRecord getExamine() {
        try {
            return parentsOperationService.getExamine();
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
    }

    /**
     * 审核通过
     *
     * @param list 通过列表
     * @return 结果
     */
    @PostMapping( "/examine" )
    @ResponseBody
    public RestRecord examine( @RequestBody List<String> list ) {
        try {
            return parentsOperationService.examine( list );
        } catch ( Exception e ) {
            log.error( e.getMessage(), e );
            return new RestRecord( 421, WebMessageConstants.SCE_PORTAL_MSG_421 );
        }
    }

}
