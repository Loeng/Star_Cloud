package cn.com.bonc.sce.api;

import cn.com.bonc.sce.bean.ContentTypeBean;
import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.dao.AppTypeDao;
import cn.com.bonc.sce.dao.ContentTypeDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author BTW
 */
@Slf4j
@RestController
@RequestMapping( "/content-type" )
public class ContentTypeApiController {

    @Autowired
    private AppTypeDao appTypeDao;
    @Autowired
    private ContentTypeDao contentTypeDao;

    @GetMapping( "/all" )
    @ResponseBody
    public RestRecord getAllContentTypeList() {
        List< ContentTypeBean > contentTypeList = contentTypeDao.selectContentTypeList();
        return new RestRecord( 200, contentTypeList );
    }

    @PostMapping( "/type-info" )
    @ResponseBody
    public RestRecord addContentTypeInfo( @RequestParam( "contentType" ) String contentType,
                                          @RequestParam( "showOrder" ) Integer showOrder,
                                          @RequestParam( "isShown" ) Integer isShown ) {
        ContentTypeBean contentTypeBean = new ContentTypeBean();
        contentTypeBean.setContentType( contentType );
        contentTypeBean.setIsShown( isShown );
        contentTypeBean.setShowOrder( showOrder );
        contentTypeBean.setIsDelete( 1L );
        int count = contentTypeDao.insertContentTypeInfo( contentTypeBean );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 409, MessageConstants.SCE_MSG_409 );
        }
    }

    @PostMapping( "/new-type-info" )
    @ResponseBody
    public RestRecord updateContentTypeInfo( @RequestParam( "id" ) Integer id,
                                             @RequestParam( "contentType" ) String contentType,
                                             @RequestParam( "showOrder" ) Integer showOrder,
                                             @RequestParam( "isShown" ) Integer isShown ) {
        ContentTypeBean contentTypeBean = new ContentTypeBean();
        if ( StrUtil.isNotBlank( contentType ) ) {
            contentTypeBean.setContentType( contentType );
        }
        if ( isShown != null ) {
            contentTypeBean.setIsShown( isShown );
        }
        if ( showOrder != null ) {
            contentTypeBean.setShowOrder( showOrder );
        }
        contentTypeBean.setId( id );
        int count = contentTypeDao.updateContentTypeInfo( contentTypeBean );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 407, MessageConstants.SCE_MSG_407 );
        }
    }

    @DeleteMapping( "/{id}" )
    @ResponseBody
    public RestRecord deleteContentTypeInfo( @PathVariable( "id" ) Integer id ) {
        int count = contentTypeDao.deleteContentTypeInfo( id );
        if ( count == 1 ) {
            return new RestRecord( 200, MessageConstants.SCE_MSG_0200 );
        } else {
            return new RestRecord( 408, MessageConstants.SCE_MSG_408 );
        }
    }


}
