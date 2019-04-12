package cn.com.bonc.sce.api;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName OrderController
 * @Description 订单管理相关
 * @Author YQ
 * @Date 2019/4/9 9:48
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/order-manage")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "购买应用-生成订单", notes="购买应用-生成订单", httpMethod = "POST")
    @PostMapping("/insertOrder")
    public RestRecord addNewOrder(@RequestBody Map param){
        return orderService.addNewOrder(param);
    }


    @ApiOperation(value = "取消订单", notes="取消订单", httpMethod = "PUT")
    @PutMapping("/cancelOrder")
    public RestRecord cancelOrder(@RequestBody Map pram){
        return orderService.cancelOrder(pram);
    }


    @ApiOperation(value = "订单详情查询", notes="订单详情查询", httpMethod = "GET")
    @GetMapping("/queryOrderByOrderID")
    public RestRecord queryOrderByOrderID(@RequestParam String ORDER_ID){
        return orderService.queryOrderByOrderID(ORDER_ID);
    }


    @ApiOperation(value = "上传线下支付凭证", notes="上传线下支付凭证", httpMethod = "POST")
    @PostMapping("/uploadVoucher")
    public RestRecord uploadVoucher(@RequestBody Map param){
        return orderService.uploadVoucher(param);
    }


    @ApiOperation(value = "支付凭证审核", notes="支付凭证审核", httpMethod = "PUT")
    @PutMapping("/reviewVoucher")
    public RestRecord reviewVoucher(@RequestBody Map param){
        return orderService.reviewVoucher(param);
    }

}
