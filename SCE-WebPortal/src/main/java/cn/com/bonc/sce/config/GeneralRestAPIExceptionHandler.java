package cn.com.bonc.sce.config;

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用 Rest API Exception 处理器
 *
 * @author : tlz
 * @author Leucippus
 * @version v1.1
 * @date : 2018/12/11 17:44
 */
//@Slf4j
//@ControllerAdvice
public class GeneralRestAPIExceptionHandler {

    @ExceptionHandler( value = Exception.class )
    @ResponseBody
    public RestRecord handle( Exception e ) {
//        log.error( MessageConstants.SCE_MSG_9999, e );
        return new RestRecord( PortalMessageConstants.SCE_PORTAL_MSG_501, "", e );
    }

    @Profile( "prod" )
    @ExceptionHandler( value = Exception.class )
    @ResponseBody
    public RestRecord handleAndPrint( Exception e ) {
//        log.error( MessageConstants.SCE_MSG_9999, e );
        return new RestRecord( PortalMessageConstants.SCE_PORTAL_MSG_500, "", e );
    }
}
