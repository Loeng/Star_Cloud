package cn.com.bonc.sce.api;

import cn.com.bonc.sce.repository.UserCollectRepository;
import cn.com.bonc.sce.rest.RestRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@Api( value = "应用收藏相关操作接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping("/app-collect")
public class AppCollectApiController {


    private UserCollectRepository userCollectRepository;

    @Autowired
    public AppCollectApiController ( UserCollectRepository userCollectRepository ) {
        this.userCollectRepository = userCollectRepository;
    }

    @GetMapping("/list")
    @ResponseBody
    public RestRecord getUserAppCollect ( @RequestParam( "userId" ) String userId) {
        List<Map<String, Object>> userAppCollect = userCollectRepository.getUserAppCollect(userId);
        return new RestRecord( 200, userAppCollect );
    }
}
