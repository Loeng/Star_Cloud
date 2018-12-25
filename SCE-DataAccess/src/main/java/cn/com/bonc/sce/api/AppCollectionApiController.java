package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.entity.AppCollectionEntity;
import cn.com.bonc.sce.repository.UserCollectionRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/app-collect" )
public class AppCollectionApiController {
    private UserCollectionRepository userCollectionRepository;

    @Autowired
    public AppCollectionApiController( UserCollectionRepository userCollectionRepository ) {
        this.userCollectionRepository = userCollectionRepository;
    }

    /**
     * 用户收藏应用信息添加表
     * @param appId
     * @param userId
     * @return
     */
    @PostMapping("/info")
    @ResponseBody
    public RestRecord addUserAppCollectionInfo ( @RequestParam( "userId" ) String userId,
                                                 @RequestParam( "appId" ) String appId ) {
        AppCollectionEntity appCollectionEntity = new AppCollectionEntity(  );
        appCollectionEntity.setAppId( appId );
        appCollectionEntity.setUserId( userId );
        userCollectionRepository.save( appCollectionEntity );
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200 );
    }

}
