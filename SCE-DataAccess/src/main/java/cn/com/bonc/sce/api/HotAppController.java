package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.HotAppDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用推荐-热门应用接口
 * author jc_D
 */
@Slf4j
@RestController
@RequestMapping( "/hotApp" )
public class HotAppController {
    private HotAppDao hotAppDao;

    @Autowired
    public HotAppController( HotAppDao hotAppDao ) {
        this.hotAppDao = hotAppDao;
    }

    /**
     * 添加热门应用
     *
     * @param appIdList app的id用","分割
     * @param userId
     * @return
     */
    @PostMapping("/{userId}")
    public RestRecord addHotRecommendAppList( @RequestBody List< String > appIdList,
                                              @PathVariable( "userId" ) String userId ) {
        //调用dao...
        return new RestRecord( true );
    }


    /**
     * 查询所有热门应用
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    @GetMapping( "/{pageNum}/{pageSize}" )
    public RestRecord selectHotRecommendAppList( @PathVariable Integer pageNum,
                                                 @PathVariable Integer pageSize ) {
        // 查询应用表中热门推荐状态为1的应用
        return new RestRecord( 0, "查询应用表中热门推荐状态为true的应用" );
    }


}
