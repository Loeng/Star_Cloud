package cn.com.bonc.sce.controller;


/**
 * Created by YueHaibo on 2018/12/12.
 *
 * @Author yuehaibo
 */

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.model.CompanyInfoModel;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CompanyInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api( value = "厂商信息接口", tags = "厂商信息接口" )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RestController
@RequestMapping( "/company" )
public class CompanyInfoController {

    private CompanyInfoService companyInfoService;

    @Autowired
    public CompanyInfoController( CompanyInfoService companyInfoService ) {
        this.companyInfoService = companyInfoService;
    }

    @ApiOperation( value = "查询厂商信息列表", notes = "通过厂商Id可以精确查询，通过厂商名称可以模糊查询", httpMethod = "GET" )
    @GetMapping
    @ResponseBody
    public RestRecord queryCompanyInfo(
            @RequestParam( value = "companyId", required = false ) @ApiParam( value = "厂商Id" ) Long companyId,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) @ApiParam( value = "厂商名称" ) String companyName,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        return companyInfoService.queryCompanyInfo( companyId, companyName, pageNum, pageSize );
    }

    /**
     * 查询单个厂商详细信息
     *
     * @param companyId 厂商Id
     * @return
     */
    @ApiOperation( value = "查询单个厂商详细信息", notes = "单个厂商的详细信息", httpMethod = "GET" )
    @GetMapping( "/one/{companyId}" )
    @ResponseBody
    public RestRecord queryOneCompanyInfo(
            @PathVariable( value = "companyId" ) @ApiParam( value = "厂商Id" ) Long companyId ) {
        return companyInfoService.queryOneCompanyInfo( companyId );
    }

    @ApiOperation( value = "通过厂商ID获取厂商信息接口", notes = "通过厂商ID获取厂商信息", httpMethod = "GET" )
    @GetMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord getCompanyByCompanyId(
            @PathVariable( value = "companyId" ) @ApiParam( value = "厂商Id" , required = true) Long companyId ) {
        return companyInfoService.getCompanyByCompanyId( companyId );
    }

    /**
     * 添加单个厂商信息
     *
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否添加成功
     */
    @ApiOperation( value = "添加单个厂商信息", notes = "新建厂商信息", httpMethod = "POST" )
    @PostMapping
    @ResponseBody
    public RestRecord addCompanyInfo(
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true )
                    CompanyInfoModel companyInfo ) {
        return companyInfoService.addCompanyInfo( companyInfo );
    }

    @ApiOperation( value = "新增厂商信息接口", notes = "新增厂商信息", httpMethod = "POST" )
    @PostMapping("/addCompany/{roleId}")
    @ResponseBody
    public RestRecord addCompany(
             @ApiParam( name = "company", value = "厂商信息对象", required = true )
             @RequestBody CompanyInfoModel company,@ApiParam(name = "userId", value = "用户ID", required = true) @RequestParam( "userId" ) String userId,@ApiParam(name = "roleId", value = "角色类型", required = true) @PathVariable( "roleId" ) Integer roleId ) {
        return companyInfoService.addCompany( company ,userId,roleId);
    }

    /**
     * 根据用户输入信息，在厂商信息表中修改对应厂商信息
     *
     * @param companyId   所需更新的厂商ID
     * @param companyInfo 用户输入的厂商信息
     * @return 返回是否更新成功
     */
    @ApiOperation( value = "修改对应厂商信息", notes = "修改对应厂商信息", httpMethod = "PUT" )
    @ApiImplicitParam( name = "companyId", value = "厂商ID", paramType = "path", required = true )
    @PutMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord updateCompanyInfo(
            @PathVariable( "companyId" ) Long companyId,
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true )
                    CompanyInfoModel companyInfo ) {
        return companyInfoService.updateCompanyInfo( companyId, companyInfo );
    }

    @ApiOperation( value = "通过厂商ID修改厂商信息接口", notes = "通过厂商ID修改厂商信息", httpMethod = "PUT" )
    @PutMapping( "/updateCompanyByCompanyId" )
    @ResponseBody
    public RestRecord updateCompanyByCompanyId(
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true )
                    CompanyInfoModel companyInfo ) {
        return companyInfoService.updateCompanyByCompanyId(companyInfo);
    }

    /**
     * 删除单个厂商信息
     * 在厂商信息表中将对应厂商状态改为已删除
     *
     * @param companyId 要删除的厂商ID
     * @return 返回是否删除成功
     */
    @ApiOperation( value = "删除单个厂商信息", notes = "删除单个厂商信息", httpMethod = "DELETE" )
    @ApiImplicitParam( name = "companyId", value = "厂商ID", paramType = "path", required = true )
    @DeleteMapping( "/{companyId}" )
    @ResponseBody
    public RestRecord deleteCompanyInfo(
            @PathVariable( "companyId" ) Long companyId ) {
        return companyInfoService.deleteCompanyInfo( companyId );
    }

    /**
     * 查询所有厂商用户
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation( value = "查询所有厂商用户", notes = "查询所有厂商用户", httpMethod = "GET" )
    @GetMapping( "/user-info" )
    @ResponseBody
    public RestRecord getAllUserInfo(
            @RequestParam( value = "loginName", required = false, defaultValue = "" ) @ApiParam( "模糊查询账号" ) String loginName,
            @RequestParam( value = "companyName", required = false, defaultValue = "" ) @ApiParam( "模糊查询厂商名" ) String companyName,
            @RequestParam( value = "enable", required = false ) @ApiParam( "是否允许登录" )String enable,
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) Integer pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) Integer pageSize ) {
        return companyInfoService.getAllUserInfo( loginName, companyName, enable, pageNum, pageSize );
    }

    @ApiOperation(value = "变更或驳回提交厂商信息接口",notes = "变更或驳回提交厂商信息",httpMethod = "PUT")
    @PutMapping( "/updateCompany" )
    @ResponseBody
    public RestRecord updateCompany(@RequestBody @ApiParam( "厂商信息对象" ) CompanyInfoModel companyInfo) {
        return companyInfoService.updateCompany(companyInfo);
    }

    @ApiOperation(value = "获取厂家认证审核列表接口",notes = "获取厂家认证审核列表信息",httpMethod = "GET")
    @GetMapping( "/getCompanyList/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord getCompanyList(@RequestParam(value = "companyName",required = false) @ApiParam( name = "companyName", value = "厂家名称") String companyName,@RequestParam(value = "property",required = false) @ApiParam( name = "property", value = "公司性质") String property, @RequestParam(value = "auditStatus",required = false) @ApiParam( name = "auditStatus", value = "审核状态") Integer auditStatus
            , @PathVariable("pageNum") @ApiParam( name = "pageNum", value = "当前页数", required = true) Integer pageNum,@PathVariable("pageSize") @ApiParam( name = "pageSize", value = "每页记录数",required = true) Integer pageSize ) {
        return companyInfoService.getCompanyList( companyName, property, auditStatus, pageNum, pageSize );
    }

}
