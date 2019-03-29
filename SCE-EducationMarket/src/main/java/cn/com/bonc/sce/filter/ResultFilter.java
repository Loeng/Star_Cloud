package cn.com.bonc.sce.filter;


import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.exception.InvalidTokenException;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  author wf
 *  date 2019/03/29
 */
@Slf4j
@ControllerAdvice
public class ResultFilter {

    /**
     * 暂时只处理token验证不通过时的返回
     * @return 解决token验证不通过返回的状态码 根据需求是否需要处理所有异常
     */
    @ExceptionHandler( value = InvalidTokenException.class )
    @ResponseBody
    public Object handle( InvalidTokenException e ){
        log.warn( e.getMessage() );
        return new RestRecord( 150, WebMessageConstants.SCE_WEB_MSG_150 );
    }

}
