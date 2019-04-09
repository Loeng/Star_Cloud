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
    @PostMapping("/")
    public RestRecord addNewOrder(@RequestBody Map param){
        return orderService.addNewOrder(param);
    }

}
