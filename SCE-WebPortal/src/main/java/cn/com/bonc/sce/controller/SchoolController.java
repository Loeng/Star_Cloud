package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.constants.MessageConstants;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.Banner;
import cn.com.bonc.sce.model.School;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.BannerService;
import cn.com.bonc.sce.service.SchoolService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 学校
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/25 8:00
 */
@Slf4j
@RestController
@Api( value = "学校", tags = "学校"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/schools" )
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    /**
     * 添加school
     *
     * @param school 信息
     * @return 是否添加成功
     */
    @ApiOperation( value = "添加school", notes = "添加school", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord insertSchool( @RequestBody @ApiParam( name = "school", value = "信息", required = true ) School school ) {
        return schoolService.insertSchool( school );
    }

    /**
     * 获取学校
     *
     * @return 获取学校
     */
    @ApiOperation( value = "获取学校", notes = "获取学校", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 406, message = MessageConstants.SCE_MSG_406, response = RestRecord.class )
    } )
    @GetMapping
    @ResponseBody
    public RestRecord getAll(@RequestParam( value = "pageNum", required = false, defaultValue = "1"  ) @ApiParam( name = "pageNum", value = "页码")Integer pageNum,
                             @RequestParam( value = "pageSize", required = false, defaultValue = "10"  ) @ApiParam( name = "pageSize", value = "数量")Integer pageSize) {
        return schoolService.getAll(pageNum,pageSize);
    }

    /**
     * 新增学校实体
     *
     * @return 新增学校实体
     */
    @ApiOperation( value = "新增学校实体", notes = "新增学校实体", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 409, message = MessageConstants.SCE_MSG_409, response = RestRecord.class )
    } )
    @PostMapping
    @ResponseBody
    public RestRecord saveSchool(@ApiParam(example = "{\"schoolName\":\"福建漳州市第一中学\" ,\"authorityName\":\"福建漳州市教育局\" ,\"schoolAddress\":\"福建漳州市龙岩区\" ,\"telephone\":\"18667667998\" ,\"institutionId\":\"2\" ,\"grade\":\"中小学\"}")  @RequestBody  Map map ) {
        return schoolService.saveSchool(map);
    }


}
