package cn.com.bonc.sce.controller;


/**
 * Created by YueHaibo on 2018/12/12.
 */

import cn.com.bonc.sce.constants.PortalMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CompanyInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api( value = "厂商信息接口", tags = "厂商信息接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/company" )
public class CompanyInfoController {

    private CompanyInfoService companyInfoService;

    @Autowired
    public CompanyInfoController( CompanyInfoService companyInfoService ) {
        this.companyInfoService = companyInfoService;
    }

    @ApiOperation( value = "查询厂商信息", notes = "通过厂商Id可以精确查询，通过厂商名称可以模糊查询", httpMethod = "GET" )
    @GetMapping
    @ResponseBody
    public RestRecord queryCompanyInfo(
            @RequestParam( value = "companyId", required = false ) @ApiParam( value = "厂商Id" ) String companyId,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) @ApiParam( value = "厂商名称" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        companyInfoService.queryCompanyInfo( companyId, companyName, pageNum, pageSize );
        RestRecord restRecord = new RestRecord();
        restRecord.setData( companyInfoService.queryCompanyInfo( companyId, companyName, pageNum, pageSize ) );
        return restRecord;
    }

    /**
     * 添加单个厂商信息
     *
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否添加成功
     */
    @ApiOperation( value = "添加单个厂商信息", notes = "新建厂商信息", httpMethod = "PUT" )
    @PutMapping( "" )
    @ResponseBody
    public RestRecord addCompanyInfo(
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true )
                    Map< String, Object > companyInfo ) {

        return new RestRecord( 0, companyInfoService.addCompanyInfo( companyInfo ) );
    }

    /**
     * 根据用户输入信息，在厂商信息表中修改对应厂商信息
     *
     * @param companyId   所需更新的厂商ID
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否更新成功
     */
    @ApiOperation( value = "修改对应厂商信息", notes = "新建厂商信息", httpMethod = "PATCH" )
    @ApiImplicitParam( name = "companyId", value = "所需更新的厂商ID", paramType = "path", required = true )
    @PatchMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord updateCompanyInfo(
            @PathVariable( "companyId" ) String companyId,
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true )
                    Map< String, Object > companyInfo ) {
        return new RestRecord( 0, companyInfoService.updateCompanyInfo( companyId, companyInfo ) );
    }

    /**
     * 删除单个厂商信息
     * 在厂商信息表中将对应厂商状态改为已删除
     *
     * @param companyId 要删除的厂商ID
     * @return 返回是否删除成功
     */
    @ApiOperation( value = "删除单个厂商信息", notes = "删除单个厂商信息", httpMethod = "DELETE" )
    @ApiImplicitParam( name = "companyId", value = "需删除信息的厂商ID", paramType = "path", required = true )
    @DeleteMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord deleteCompanyInfo(
            @PathVariable( "companyId" ) String companyId ) {
        return new RestRecord( 0, companyInfoService.deleteCompanyInfo( companyId ) );
    }
}
