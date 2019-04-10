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

import java.util.List;
import java.util.Map;

/**
 * 发票管理
 */
@Api( value = "发票管理接口", tags = "发票管理接口" )
@ApiResponses( { @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ) } )
@Slf4j
@RestController
@RequestMapping( "/invoice" )
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @ApiOperation( value = "查看发票资质信息", notes = "查看发票资质信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
    } )
    @GetMapping( "/info" )
    @ResponseBody
    public RestRecord getInvoiceInfo( @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return invoiceService.selectInvoiceInfoByOrganizationId( userId );
    }


    @ApiOperation( value = "查看收票地址信息", notes = "查看收票地址信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
    } )
    @GetMapping( "/address" )
    @ResponseBody
    public RestRecord getInvoiceAddress( @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return invoiceService.selectInvoiceAddressByOrganizationId( userId );
    }

    @ApiOperation( value = "查看历史信息", notes = "-查看历史信息", httpMethod = "GET" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @GetMapping( "/info-history/{pageNum}/{pageSize}" )
    @ResponseBody
    public RestRecord selectInvoiceHistory( @CurrentUserId @ApiParam( hidden = true ) String userId,
                                            @PathVariable( "pageNum" ) Integer pageNum,
                                            @PathVariable( "pageSize" ) Integer pageSize,
                                            @RequestParam Map map ) {
        return invoiceService.selectInvoiceHistory( userId, pageNum, pageSize, map );
    }

    @ApiOperation( value = "修改或者新增发票资质信息", notes = "修改或者新增发票资质信息", httpMethod = "PUT" )
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
        return invoiceService.updateInvoiceInfoByOrganizationId( invoiceInfo, userId );
    }

    @ApiOperation( value = "修改或者新增收票地址信息", notes = "修改或者新增收票地址信息", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PutMapping( "/update-address" )
    @ResponseBody
    public RestRecord updateInvoiceAddress( @RequestBody @ApiParam( "{\"RECIPIENTS\":\"BeJson\",\"POST_ADDRESS\":\"光华中心128号\",\"TELEPHONE_NUMBER\":10086,\"PROVINCE\":\"四川省\",\"CITY\":\"成都市\",\"AREA\":\"青羊区\"}" )
                                                    Map< String, Object > invoiceInfo, @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return invoiceService.updateInvoiceAddressByOrganizationId( invoiceInfo, userId );
    }


    @ApiOperation( value = "根据订单号查询开票相关信息（开票资质，收寄地址等）", notes = "根据订单号查询开票相关信息(开票资质，收寄地址等)", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PostMapping( "/billing-by-order-no" )
    @ResponseBody
    public RestRecord getBillingInfoByOrderNo( @RequestBody @ApiParam( "[\"103\",\"104\"]" )
                                                       List< String > orderNoList, @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return invoiceService.getBillingInfoByOrderNo( userId, orderNoList );
    }

    @ApiOperation( value = "增开发票(订单号用逗号分隔)", notes = "增开发票(订单号用逗号分隔)", httpMethod = "POST" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PostMapping( "/add-order-invoice" )
    @ResponseBody
    public RestRecord addBillingInfo( @RequestBody @ApiParam( "json格式" )
                                              Map< String, Object > invoiceInfo, @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return invoiceService.addBillingInfo( invoiceInfo, userId );
    }

    @ApiOperation( value = "编辑开票状态信息", notes = "编辑开票状态信息(1,未开票，2已开票，3已邮寄)", httpMethod = "PUT" )
    @ApiImplicitParams( {
            @ApiImplicitParam( name = "authentication", value = "用户信息", paramType = "header" )
    } )
    @PutMapping( "/update-order-invoice-state" )
    @ResponseBody
    public RestRecord editOrderInvoiceState( @RequestBody @ApiParam( "{\"ID\":2,\"INVOICE_CODE\":\"发票代码\",\"INVOICE_NO\":\"发票号码\",\"EXPRESS_COMPANY\":\"快递公司\",\"COURIER_NUMBER\":\"快递单号\",\"INVOICE_STATUS\":2}" )
                                                     Map< String, Object > stateInfo, @CurrentUserId @ApiParam( hidden = true ) String userId ) {
        return invoiceService.editOrderInvoiceState( stateInfo, userId );
    }

    @ApiOperation( value = "根据用户登陆账号查询用户开票信息（开票资质，收票地址，有效订单号）", notes = "根据用户登陆账号查询用户开票信息（开票资质，收票地址，有效订单号）", httpMethod = "GET" )
    @GetMapping( "/billing-by-loginName" )
    public RestRecord getBillingInfoByLoginName( @ApiParam( "用户账号" ) @RequestParam( "loginName" ) String loginName ) {
        return invoiceService.getBillingInfoByLoginName( loginName );
    }


}
