package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppInfoEntity;
import cn.com.bonc.sce.repository.HotAppRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用推荐-热门应用接口api
 * @author jc_D
 *
 * @version 2.0
 * @update 添加列表查询
 * @updateFrom 2018/12/26 11:20
 * @updateAuthor wzm
 */
@Slf4j
@RestController
@RequestMapping( "/hot-app" )
public class HotAppController {
    @Autowired
    private HotAppRepository hotAppRepository;

    /**
     * 添加热门应用
     *
     * @param appIdList
     * @param userId
     * @return
     */
    @PostMapping( "/{userId}" )
    public RestRecord addHotRecommendAppList( @RequestBody List< String > appIdList,
                                              @PathVariable( "userId" ) String userId ) {
        //从数据库获取所有热门应用id
        List< String > appidList1 = hotAppRepository.getAllHotAppId();
        //将热门改为非热门
        hotAppRepository.updateHotApp( appidList1, 0L, userId );
        //将用户选择的应用改为热门
        Integer i = hotAppRepository.updateHotApp( appIdList, 1L, userId );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }


    /**
     * 查询所有热门应用
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @GetMapping( "/{pageNum}/{pageSize}")
    public RestRecord selectHotRecommendAppList( @PathVariable Integer pageNum,
                                                 @PathVariable Integer pageSize ) {
        Pageable pageable = PageRequest.of( pageNum - 1, pageSize );
        Page< AppInfoEntity > appInfoList = hotAppRepository.findByIsHotRecommend( 1L, pageable );
        // 查询应用表中热门推荐状态为1的应用
        return new RestRecord( 200, appInfoList );
    }

    /**
     * 查询所有热门应用列表
     *
     * @return
     */
    @GetMapping( "" )
    public RestRecord selectHotAppList() {
        return new RestRecord( 200, hotAppRepository.selectHotAppList() );
    }

    /**
     * 添加热门应用
     *
     * @param appId
     * @param userId
     * @return
     */
    @PostMapping( "/new-one" )
    public RestRecord addHotRecommendAppList( @RequestParam( "appId" ) String appId,
                                              @RequestParam( "userId" ) String userId ) {
        Integer i = hotAppRepository.addHotApp( appId, 1L, userId );
        if ( i >= 0 ) {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } else {
            return new RestRecord( 500,WebMessageConstants.SCE_PORTAL_MSG_421 );
        }

    }

    /**
     * 取消热门应用
     *
     * @param appId
     * @param userId
     * @return
     */
    @PostMapping( "/sub-one" )
    public RestRecord cancelHotRecommendAppList( @RequestParam( "appId" ) String appId,
                                              @RequestParam( "userId" ) String userId ) {
        Integer i = hotAppRepository.addHotApp( appId, 0L, userId );
        if ( i >= 0 ) {
            return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
        } else {
            return new RestRecord( 500,WebMessageConstants.SCE_PORTAL_MSG_421 );
        }

    }

}
