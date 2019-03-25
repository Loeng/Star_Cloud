package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.InvoiceDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.InvoiceService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 发票管理
 */
@Api( value = "发票管理相关接口相关接口", tags = "发票管理相关接口相关接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@Slf4j
@RestController
@RequestMapping( "/invoice" )
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @ApiOperation( value = "发票管理-查看发票资质信息", notes = "发票管理-查看发票资质信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
    } )
    @GetMapping( "/info" )
    @ResponseBody
    public RestRecord getInvoiceInfo( @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceService.selectInvoiceInfoByOrganizationId( userId ) );
    }

    @ApiOperation( value = "发票管理-查看历史信息", notes = "发票管理-查看历史信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @GetMapping( "/info-history" )
    @ResponseBody
    public RestRecord selectInvoiceHistory( @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceService.selectInvoiceHistory( userId ) );
    }

    @ApiOperation( value = "发票管理-修改或者新增发票资质信息", notes = "发票管理-修改或者新增发票资质信息", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )

    @PutMapping( "/update" )
    @ResponseBody
    public RestRecord updateInvoiceInfo( @RequestBody @ApiParam( "{\n" +
            "    \"INVOICE_TITLE\": \"发票抬头1\",\n" +
            "    \"OPEN_TYPE\": \"开具类型\",\n" +
            "    \"INVOICE_TYPE\": \"发票类型\",\n" +
            "    \"TAX_REGISTRATION_NO\": \"税务登记证号\",\n" +
            "    \"DEPOSIT_BANK\": \"开户银行\",\n" +
            "    \"BANK_ACCOUNT\": \"开户账号\",\n" +
            "    \"REGISTRATION_LOCATION\": \"注册场所地址\",\n" +
            "    \"REGISTRATION_TELEPHONE\": \"注册固定电话\"\n" +
            "}" ) Map< String, Object > invoiceInfo, @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return new RestRecord( 200, WebMessageConstants.SCE_PORTAL_MSG_200, invoiceService.updateInvoiceInfoByOrganizationId( invoiceInfo, userId ) );
    }
}
