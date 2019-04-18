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


    public RestRecord uploadVoucher(Map param,String userId){
        param.put("UPLOAD_USER_ID",userId);
        return orderDao.uploadVoucher(param);
    }

    public RestRecord reviewVoucher(Map param,String userId){
        param.put("AUDITING_USER_ID",userId);
        return orderDao.reviewVoucher(param);
    }


    public RestRecord queryAllOrder( String ORDER_ID,
                                     String START_TIME,
                                     String END_TIME,
                                     String PRODUCT_TYPE_CODE,
                                     String PAYING_TYPE,
                                     String ORDER_STATUS,
                                     String ORDER_BY,
                                     int pageNum,
                                     int pageSize){
        return orderDao.queryAllOrder(ORDER_ID, START_TIME, END_TIME, PRODUCT_TYPE_CODE, PAYING_TYPE, ORDER_STATUS, ORDER_BY, pageNum, pageSize);
    }


    public RestRecord queryOrderByUserId(String KEYWORD,
                                         String ORDER_STATUS,
                                         String ORDER_BY,
                                         String USER_ID,
                                         int pageNum,
                                         int pageSize){
        return orderDao.queryOrderByUserId(KEYWORD, ORDER_STATUS, ORDER_BY, USER_ID, pageNum, pageSize);
    }
}
