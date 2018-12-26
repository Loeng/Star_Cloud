/*
package cn.com.bonc.sce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/26 13:49
 *//*

@Configuration
public class JsonSerializerConfig extends WebMvcConfigurerAdapter {
    */
/**
     * 修改自定义消息转换器
     *
     * @param converters 消息转换器列表
     *//*

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?> > converters) {
        System.out.println("fastjson配置读取");
        //调用父类的配置
        super.configureMessageConverters(converters);
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        converters.add(fastConverter);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }
}
*/
