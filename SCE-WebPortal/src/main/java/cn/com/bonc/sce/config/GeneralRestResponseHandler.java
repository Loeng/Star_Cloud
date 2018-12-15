package cn.com.bonc.sce.config;

import cn.com.bonc.sce.rest.RestRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 通用 Response 处理器,会将没有封装为 RestRecord 的返回值封装到一个 RestRecord 中进行返回。
 * #TODO 需修复此类会造成 swagger 不能正常启动的问题
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/14 1:16
 */
@Slf4j
//@ControllerAdvice
public class GeneralRestResponseHandler implements ResponseBodyAdvice {

    ObjectMapper jsonMapper = new ObjectMapper();

    /**
     * <del>固定为 json 解析规则</del>
     */
    @Override
    public boolean supports( @Nullable MethodParameter methodParameter, @Nullable Class converterType ) {
        //  return converterType == JsonbHttpMessageConverter.class;
        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite( @Nullable Object o, @NonNull MethodParameter methodParameter, @NonNull MediaType mediaType, @NonNull Class selectedConverterType, @NonNull ServerHttpRequest serverHttpRequest, @NonNull ServerHttpResponse serverHttpResponse ) {
        if ( o != null ) {
            if ( o instanceof RestRecord ) {
                return o;
            }
            RestRecord restRecord = new RestRecord();
            if ( o instanceof Exception ) {
                restRecord.setException( ( Exception ) o );
            } else if ( o instanceof String ) {
                restRecord.setData( o );
                return restRecord.toString();
            } else {
                //数据
                restRecord.setData( o );
            }
            return restRecord;
        }
        return null;
    }

    private void removeException( RestRecord restRecord ) {

    }
}
