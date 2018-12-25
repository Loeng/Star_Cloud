package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppOpenEntity;
import cn.com.bonc.sce.repository.UserOpenRepository;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yinming on 2018/12/25.
 */
@Slf4j
@RestController
@RequestMapping("/app-open")
public class AppOpenController {

    private UserOpenRepository userOpenRepository;

    @Autowired
    public AppOpenController ( UserOpenRepository userOpenRepository ) {
        this.userOpenRepository = userOpenRepository;
    }


    /**
     * 用户应用开通历史查询接口
     * @param userId 查询的用户Id
     * @return 用户开通应用历史记录
     */
    @GetMapping
    @ResponseBody
    public RestRecord getUserAppOpenList ( @RequestParam( "userId" ) String userId) {
        List< Map< String,Object> > listPage = userOpenRepository.getUserOpenList( userId);
        return new RestRecord( 200, listPage );
    }

    /**
     * 用户开通应用信息添加表
     * @param appId
     * @param userId
     * @return
     */
    @PostMapping("/info")
    @ResponseBody
    public RestRecord addUserAppOpenInfo ( @RequestParam( "userId" ) String userId,
                                           @RequestParam( "appId" ) String appId ) {
        AppOpenEntity appOpenEntity = new AppOpenEntity(  );
        appOpenEntity.setAppId( appId );
        appOpenEntity.setUserId( userId );
        userOpenRepository.save( appOpenEntity );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

}
