package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.model.CompanyInfo;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CompanyInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api( value = "厂商信息接口", tags = "厂商信息接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@RestController
@RequestMapping( "/Company" )
public class CompanyInfoController {

    private CompanyInfoService companyInfoService;

    @Autowired
    public CompanyInfoController( CompanyInfoService companyInfoService ) {
        this.companyInfoService = companyInfoService;
    }

    /**
     * 查询全部商家信息
     *
     * @return
     */
    @ApiOperation( value = "查询全部商家信息", notes = "查询全部商家信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "pageNum", value = "页码", paramType = "query", required = false, defaultValue = "1" ),
            @ApiImplicitParam( name = "pageSize", value = "每页显示数量", paramType = "query", required = false, defaultValue = "10" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )

    @GetMapping
    @ResponseBody
    public RestRecord selectAllCompanyList(
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return new RestRecord( 0, companyInfoService.selectAllCompanyList() );
    }


    /**
     * 根据输入名字模糊搜索厂商信息
     *
     * @param companyName 用户输入搜索厂商名
     * @return 返回查询结果
     */
    @ApiOperation( value = "查询厂商信息", notes = "根据输入名字模糊搜索厂商信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "companyName", value = "厂商名称", paramType = "query", required = true ),
            @ApiImplicitParam( name = "pageNum", value = "页码", paramType = "query", required = false, defaultValue = "1" ),
            @ApiImplicitParam( name = "pageSize", value = "每页显示数量", paramType = "query", required = false, defaultValue = "10" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping( "/query" )
    @ResponseBody
    public RestRecord selectCompanyListByName(
            @RequestParam( value = "companyName" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return new RestRecord( 0, companyInfoService.selectCompanyListByName( companyName ) );
    }

    /**
     * 根据厂商ID查询厂商信息
     *
     * @param companyId 搜索的厂商ID
     * @return 返回查询结果
     */
    @ApiOperation( value = "查询厂商信息", notes = "通过厂商Id查询厂商信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "companyId", value = "厂商ID", paramType = "path", required = true ),
            @ApiImplicitParam( name = "pageNum", value = "页码", paramType = "query", required = false, defaultValue = "1" ),
            @ApiImplicitParam( name = "pageSize", value = "每页显示数量", paramType = "query", required = false, defaultValue = "10" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord selectCompanyById(
            @PathVariable( "companyId" ) String companyId,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) String pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) String pageSize ) {
        return new RestRecord( 0, companyInfoService.selectCompanyById( companyId ) );
    }

    /**
     * 添加单个厂商信息
     *
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否添加成功
     */
    @ApiOperation( value = "添加单个厂商信息", notes = "新建厂商信息", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PutMapping( "" )
    @ResponseBody
    public RestRecord addCompanyInfo(
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true ) CompanyInfo companyInfo ) {
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
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PatchMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord updateCompanyInfo(
            @PathVariable( "companyId" ) String companyId,
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true ) CompanyInfo companyInfo ) {
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
    @ApiResponses( {
            @ApiResponse( code = 200, message = PortalMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @DeleteMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord deleteCompanyInfo(
            @PathVariable( "companyId" ) String companyId ) {
        return new RestRecord( 0, companyInfoService.deleteCompanyInfo( companyId ) );
    }
}
