package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @InterfaceName OrderDao
 * @Description 订单相关
 * @Author YQ
 * @Date 2019/4/9 17:42
 * @Version 1.0
 */
@Repository
@FeignClient(value = "sce-data-mybatis")
public interface OrderDao {

    @RequestMapping(value = "/order-manage/insertOrder",method = RequestMethod.POST)
    public RestRecord addNewOrder(@RequestBody Map param);


    @RequestMapping(value = "/order-manage/cancelOrder",method = RequestMethod.PUT)
    public RestRecord cancelOrder(@RequestBody Map param);

    @RequestMapping(value = "/order-manage/queryOrderByOrderID",method = RequestMethod.GET)
    public RestRecord queryOrderByOrderID(@RequestParam("ORDER_ID") String ORDER_ID);
}
