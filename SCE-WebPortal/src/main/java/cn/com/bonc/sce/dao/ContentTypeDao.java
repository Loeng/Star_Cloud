package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BTW
 */
@FeignClient( "sce-data-mybatis" )
public interface ContentTypeDao {

    /**
     * @return
     */
    @RequestMapping( value = "/content-type/all", method = RequestMethod.GET )
    RestRecord getAllContentTypeList();

    /**
     * @return
     */
    @RequestMapping( value = "/content-type/type-info", method = RequestMethod.POST )
    RestRecord addContentTypeInfo( @RequestParam( "contentType" ) String contentType,
                                   @RequestParam( "isShown" ) Integer isShown,
                                   @RequestParam( "showOrder" ) Integer showOrder );

    /**
     * @return
     */
    @RequestMapping( value = "/content-type/new-type-info", method = RequestMethod.POST )
    RestRecord updateContentTypeInfo( @RequestParam( "id" ) Integer id,
                                      @RequestParam( "contentType" ) String contentType,
                                      @RequestParam( "isShown" ) Integer isShown,
                                      @RequestParam( "showOrder" ) Integer showOrder );

    /**
     * @param id
     * @return
     */
    @RequestMapping( value = "/content-type/{id}", method = RequestMethod.DELETE )
    RestRecord deleteContentTypeInfo( @PathVariable( "id" ) Integer id );

}
