package cn.com.bonc.exception;

import cn.com.bonc.sce.rest.RestRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: tlz
 * @Date: 2018/12/11 17:44
 * @Description: 全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RestRecord handle( Exception e) {
            logger.error("【系统异常】{}", e);
            return new RestRecord(500,"",e);
    }

}
