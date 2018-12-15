package cn.com.bonc.sce.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/12 15:21
 */
@SpringBootConfiguration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {
        /*
         * 统一添加 error response
         */
        List<ResponseMessage > responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").responseModel(new ModelRef("RestRecord")).build());
        // responseMessageList.add(new ResponseMessageBuilder().code(409).message("业务逻辑异常").responseModel(new ModelRef("ApiError")).build());
        // responseMessageList.add(new ResponseMessageBuilder().code(422).message("参数校验异常").responseModel(new ModelRef("ApiError")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("RestRecord")).build());
        //  responseMessageList.add(new ResponseMessageBuilder().code(503).message("Hystrix异常").responseModel(new ModelRef("ApiError")).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.com.bonc.sce.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SCE-WebPortal-API 在线文档")
                .description("福建教育云平台-门户网站后端接口文档")
                .termsOfServiceUrl("http://www.baidu.com")
                .contact( new Contact( "leucippus", "www.还没买.com.cn", "tangchenkai@bonc.com.cn" ) )
                .version("1.0")
                .build();
    }
}
