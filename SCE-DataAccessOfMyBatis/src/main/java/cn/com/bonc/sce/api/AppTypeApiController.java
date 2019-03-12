package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.AppTypeBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AppTypeDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/app-type" )
public class AppTypeApiController {

    @Autowired
    private AppTypeDao appTypeDao;

    @GetMapping( "/all" )
    @ResponseBody
    public RestRecord getAllAppTypeList() {
        List< AppTypeBean > appTypeList = appTypeDao.selectAppTypeList();
        return new RestRecord( 200, appTypeList );
    }

    @PostMapping
    @ResponseBody
    public RestRecord addAppTypeInfo( @RequestParam( "appTypeName" ) String appTypeName ) {
        AppTypeBean appTypeBean = new AppTypeBean();
        appTypeBean.setAppTypeName( appTypeName );
        appTypeBean.setIsDelete( 1L );
        int count = appTypeDao.insertAppTypeInfo( appTypeBean );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @PostMapping( "/new-type-info" )
    @ResponseBody
    public RestRecord updateAppTypeInfo( @RequestParam( "appTypeId" ) Integer appTypeId,
                                         @RequestParam( "appTypeName" ) String appTypeName ) {
        int count = appTypeDao.updateAppTypeInfo( appTypeId, appTypeName );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @DeleteMapping( "/{appTypeId}" )
    @ResponseBody
    public RestRecord deleteAppTypeInfo( @PathVariable( "appTypeId" ) Integer appTypeId ) {
        int count = appTypeDao.deleteAppTypeInfo( appTypeId );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 408, MessageConstants.SCE_MSG_408 );
        }
    }


}
