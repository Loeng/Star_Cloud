package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.OrderDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName OrderService
 * @Description 订单相关服务
 * @Author YQ
 * @Date 2019/4/9 17:41
 * @Version 1.0
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public RestRecord addNewOrder(Map param,String userId){
        param.put("BUYING_USER_ID",userId);
        return orderDao.addNewOrder(param);
    }

    public RestRecord cancelOrder(Map param){
        return orderDao.cancelOrder(param);
    }


    public RestRecord queryOrderByOrderID(String ORDER_ID){
        return orderDao.queryOrderByOrderID(ORDER_ID);
    }
}
