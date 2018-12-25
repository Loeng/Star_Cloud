package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.CountDao;
import cn.com.bonc.sce.repository.AppCountRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 应用下载统计接口
 */
@Slf4j
@RestController
@RequestMapping( "/count" )
public class DownloadCountApiController {
    private CountDao countDao;
    private AppCountRepository appCountRepository;

    @Autowired
    public DownloadCountApiController( CountDao countDao, AppCountRepository appCountRepository ) {
        this.countDao = countDao;
        this.appCountRepository = appCountRepository;
    }


    /**
     * 单个应用下载统计接口
     * 统计指定应用的下载量
     *
     * @param appId 应用ID
     * @return
     */
    @GetMapping( "/one" )
    @ResponseBody
    public RestRecord countSingleAppDownload( @RequestParam( "appId" ) String appId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 指定种类应用下载量统计接口
     *
     * @param appType 应用类型
     * @return
     */
    @GetMapping( "/type" )
    @ResponseBody
    public RestRecord countAppDownloadByType( @RequestParam( "appType" ) String appType ) {
        // 根据应用类型查找对应的应用，统计下载量
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );

    }

    /**
     * 查询指定厂商的应用下载量
     * 根据厂商Id查找对应的应用，统计下载量
     *
     * @param companyId 厂商Id
     * @return
     */
    @GetMapping( "/company" )
    @ResponseBody
    public RestRecord countAppDownloadByCompanyId( @RequestParam( "companyId" ) Long companyId ) {
        // 根据厂商名查找对应的应用，统计下载量
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

    /**
     * 查询全部应用下载排名
     * @param pageSize 分页条数
     * @param pageNum 分页页码
     * @return
     */
    @GetMapping( "/download-ranking" )
    @ResponseBody
    public RestRecord getAppDownloadRanking( @RequestParam( "pageSize" ) Integer pageSize,
                                             @RequestParam( "pageNum" ) Integer pageNum ) {
        Pageable pageable = PageRequest.of( pageNum, pageSize );
        Page<List<Map<String,Object> > > downloadRankingList = appCountRepository.getAppDownloadRankingList( pageable );
        return new RestRecord( 200, downloadRankingList );
    }

}
