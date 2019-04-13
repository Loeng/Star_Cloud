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
    RestRecord addNewOrder(@RequestBody Map param);


    @RequestMapping(value = "/order-manage/cancelOrder",method = RequestMethod.PUT)
    RestRecord cancelOrder(@RequestBody Map param);

    @RequestMapping(value = "/order-manage/queryOrderByOrderID",method = RequestMethod.GET)
    RestRecord queryOrderByOrderID(@RequestParam("ORDER_ID") String ORDER_ID);

    @RequestMapping(value = "/order-manage/uploadVoucher",method = RequestMethod.POST)
    RestRecord uploadVoucher(@RequestBody Map param);

    @RequestMapping(value = "/order-manage/reviewVoucher",method = RequestMethod.PUT)
    RestRecord reviewVoucher(@RequestBody Map param);


    @RequestMapping(value = "/order-manage/queryAllOrder",method = RequestMethod.GET)
    RestRecord queryAllOrder(@RequestParam("ORDER_ID") String ORDER_ID,
                             @RequestParam("START_TIME") String START_TIME,
                             @RequestParam("END_TIME") String END_TIME,
                             @RequestParam("PRODUCT_TYPE_CODE") String PRODUCT_TYPE_CODE,
                             @RequestParam("PAYING_TYPE") String PAYING_TYPE,
                             @RequestParam("ORDER_STATUS") String ORDER_STATUS,
                             @RequestParam("ORDER_BY") String ORDER_BY,
                             @RequestParam("pageNum") int pageNum,
                             @RequestParam("pageSize") int pageSize);
}
