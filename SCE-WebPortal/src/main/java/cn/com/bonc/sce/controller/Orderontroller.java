package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName Orderontroller
 * @Description 订单相关
 * @Author YQ
 * @Date 2019/4/9 17:36
 * @Version 1.0
 */
@Slf4j
@RestController
@Api( value = "订单相关", tags = "订单相关"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/order" )
public class Orderontroller {

    @Autowired
    private OrderService orderService;


    @ApiOperation( value = "生成订单", notes = "生成订单", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 421, message = WebMessageConstants.SCE_PORTAL_MSG_421, response = RestRecord.class ),
            @ApiResponse( code = 423, message = WebMessageConstants.SCE_PORTAL_MSG_423, response = RestRecord.class ),
            @ApiResponse( code = 431, message = WebMessageConstants.SCE_PORTAL_MSG_431, response = RestRecord.class ),
            @ApiResponse( code = 741, message = WebMessageConstants.SCE_PORTAL_MSG_741, response = RestRecord.class ),
            @ApiResponse( code = 742, message = WebMessageConstants.SCE_PORTAL_MSG_742, response = RestRecord.class ),
            @ApiResponse( code = 743, message = WebMessageConstants.SCE_PORTAL_MSG_743, response = RestRecord.class ),
            @ApiResponse( code = 744, message = WebMessageConstants.SCE_PORTAL_MSG_744, response = RestRecord.class )
    } )
    @PostMapping("/add-order/{userId}")
    public RestRecord addNewOrder(@RequestBody Map param, @PathVariable String userId){
        return orderService.addNewOrder(param, userId);
    }


    @ApiOperation( value = "订单取消", notes = "订单取消", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 421, message = WebMessageConstants.SCE_PORTAL_MSG_421, response = RestRecord.class ),
            @ApiResponse( code = 423, message = WebMessageConstants.SCE_PORTAL_MSG_423, response = RestRecord.class )
    } )
    @PutMapping("/cancel-order/{userId}")
    public RestRecord cancelOrder(@RequestBody Map param,@PathVariable String userId){
        return orderService.cancelOrder(param);
    }


    @ApiOperation( value = "订单详情查询", notes = "订单详情查询", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/query-order-by-orderid")
    public RestRecord queryOrderByOrderID(@RequestParam String ORDER_ID){
        return orderService.queryOrderByOrderID(ORDER_ID);
    }


    @ApiOperation( value = "上传线下支付凭证", notes = "上传线下支付凭证", httpMethod = "POST" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 421, message = WebMessageConstants.SCE_PORTAL_MSG_421, response = RestRecord.class ),
            @ApiResponse( code = 431, message = WebMessageConstants.SCE_PORTAL_MSG_431, response = RestRecord.class )
    } )
    @PostMapping("/upload-voucher/{userId}")
    public RestRecord uploadVoucher(@RequestBody Map param,@PathVariable String userId){
        return orderService.uploadVoucher(param,userId);
    }


    @ApiOperation( value = "支付凭证审核", notes = "支付凭证审核", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class ),
            @ApiResponse( code = 421, message = WebMessageConstants.SCE_PORTAL_MSG_421, response = RestRecord.class ),
            @ApiResponse( code = 423, message = WebMessageConstants.SCE_PORTAL_MSG_423, response = RestRecord.class )
    } )
    @PutMapping("/review-voucher/{userId}")
    public RestRecord reviewVoucher(@RequestBody Map param,@PathVariable String userId){
        return orderService.reviewVoucher(param,userId);
    }


    @ApiOperation( value = "订单列表查询（管理员）", notes = "订单列表查询（管理员）", httpMethod = "GET" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @GetMapping("/query-all-order")
    public RestRecord queryAllOrder(@RequestParam(required = false) String ORDER_ID,
                                    @RequestParam(required = false) String START_TIME,
                                    @RequestParam(required = false) String END_TIME,
                                    @RequestParam(required = false) String PRODUCT_TYPE_CODE,
                                    @RequestParam(required = false) String PAYING_TYPE,
                                    @RequestParam(required = false) String ORDER_STATUS,
                                    @RequestParam(defaultValue = "1") String ORDER_BY,
                                    @RequestParam(defaultValue = "1") int pageNum,
                                    @RequestParam(defaultValue = "10") int pageSize){
        return orderService.queryAllOrder(ORDER_ID, START_TIME, END_TIME, PRODUCT_TYPE_CODE, PAYING_TYPE, ORDER_STATUS, ORDER_BY, pageNum, pageSize);
    }

}
