package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.AgentModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.AgentService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author BTW
 */
@Slf4j
@Api( value = "代理信息接口", tags = "代理信息接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/agent" )
public class AgentController {

    private AgentService agentService;

    @Autowired
    public AgentController( AgentService agentService ) {
        this.agentService = agentService;
    }

    /**
     * 添加单个代理信息
     *
     * @param agentModel 用户输入的厂商信息
     * @return 返回是否添加成功
     */
    @ApiOperation( value = "添加单个代理信息", notes = "新建代理信息", httpMethod = "POST" )
    @PostMapping
    @ResponseBody
    public RestRecord addCompanyInfo(
            @RequestBody @ApiParam( name = "agentModel", value = "代理信息对象", required = true )
                    AgentModel agentModel ) {
        return agentService.saveAgentInfo( agentModel );
    }
}
