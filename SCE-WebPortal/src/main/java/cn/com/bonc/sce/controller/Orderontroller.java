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
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PostMapping("/add-order/{userId}")
    public RestRecord addNewOrder(@RequestBody Map param, @PathVariable String userId){
        return orderService.addNewOrder(param, userId);
    }


    @ApiOperation( value = "订单取消", notes = "订单取消", httpMethod = "PUT" )
    @ApiResponses( {
            @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
    } )
    @PutMapping("/cancel-order")
    public RestRecord cancelOrder(@RequestBody Map param){
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
}
