package cn.com.bonc.sce.api;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.entity.AppTypeEntity;
import cn.com.bonc.sce.repository.AppTypeRepository;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/app-type" )
public class AppTypeApiController {

    private AppTypeRepository appTypeRepository;

    @Autowired
    public AppTypeApiController ( AppTypeRepository appTypeRepository ) {
        this.appTypeRepository = appTypeRepository;
    }

    @GetMapping("/all")
    @ResponseBody
    public RestRecord getAllAppTypeList() {
        List< Map< String,Object > > appTypeList = appTypeRepository.getAllAppTypeList();
        return new RestRecord( 200, appTypeList);
    }

    @PostMapping
    @ResponseBody
    public RestRecord addAppTypeInfo( @RequestParam( "appTypeName" ) String appTypeName ) {
        AppTypeEntity appTypeEntity = new AppTypeEntity(  );
        appTypeEntity.setAppTypeName( appTypeName );
        AppTypeEntity saveEntity = appTypeRepository.save( appTypeEntity );
        if ( StringUtils.equalsIgnoreCase( saveEntity.getAppTypeName(), appTypeName ) ) {
            return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200 );
        } else {
            return new RestRecord( 500, MessageConstants.SCE_MSG_409);
        }

    }

    @PostMapping("/new-type-info")
    @ResponseBody
    public RestRecord updateAppTypeInfo( @RequestParam( "appTypeId" ) Integer appTypeId,
                                         @RequestParam( "appTypeName" ) String appTypeName ) {
        int count = appTypeRepository.updateAppTypeInfo( appTypeId, appTypeName );
        if ( count == 1 ) {
            return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200 );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_409 );
        }
    }

    @DeleteMapping("/{appTypeId}")
    @ResponseBody
    public RestRecord deleteAppTypeInfo( @PathVariable( "appTypeId" ) Integer appTypeId ) {
        int count = appTypeRepository.deleteAppTypeInfo( appTypeId );
        if ( count == 1 ) {
            return new RestRecord( 200, PortalMessageConstants.SCE_PORTAL_MSG_200 );
        } else {
            return new RestRecord( 200, MessageConstants.SCE_MSG_409 );
        }
    }


}
