package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AppStateListService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping( "/app-state" )
public class AppStateListController {

    @Autowired
    private AppStateListService appStateListService;

    @GetMapping( "/{state}" )
    public RestRecord getStateList( @PathVariable( "state" ) Integer state,
                                    @RequestParam( value = "auditStatus", required = false ) String auditStatus,
                                    @RequestParam( value = "typeId", required = false, defaultValue = "0" ) Integer typeId,
                                    @RequestParam( value = "keyword", required = false ) String keyword,
                                    @RequestParam( value = "orderBy", required = false ) String orderBy,
                                    @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
                                    @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize,
                                    @RequestParam( value = "userId" ) String userId
    ) {
        PageInfo pageInfo = null;
        switch ( state ) {
            case 1://运营中
                pageInfo = appStateListService.running( auditStatus, typeId, keyword, orderBy, pageNum, pageSize, userId );
                break;
            case 2://上架审核 1(审核中) 3(被驳回)
                pageInfo = appStateListService.onShelfAudit( auditStatus, typeId, keyword, orderBy, pageNum, pageSize, userId );
                break;
            case 3: //迭代审核 2(审核中) 6(被驳回)
                pageInfo = appStateListService.iterationAudit( auditStatus, typeId, keyword, orderBy, pageNum, pageSize, userId );

                break;
            case 4://下架审核 9(审核中) 10(被驳回)
                pageInfo = appStateListService.downShelfAudit( auditStatus, typeId, keyword, orderBy, pageNum, pageSize, userId );

                break;
            case 5://已下架 5（已停止） 7(运行中)
                pageInfo = appStateListService.alreadyDown( auditStatus, typeId, keyword, orderBy, pageNum, pageSize, userId );

                break;
            default:
                return new RestRecord( 420, WebMessageConstants.SCE_PORTAL_MSG_420 );
        }
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, pageInfo );
    }


}
