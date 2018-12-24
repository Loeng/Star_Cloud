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
            @RequestParam( value = "pageNum", required = false, defaultValue = "1" ) int pageNum,
            @RequestParam( value = "pageSize", required = false, defaultValue = "10" ) int pageSize ) {
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
    @ResponseBody//@RequestBody @ApiParam(name="用户对象",value="传入json格式",required=true) User user
    public RestRecord updateCompanyInfo(
            @PathVariable( "companyId" ) Long companyId,
            @RequestBody @ApiParam( name = "companyInfo", value = "厂商信息对象", required = true )
                    CompanyInfoModel companyInfo ) {
        return companyInfoService.updateCompanyInfo( companyId, companyInfo );
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
}
