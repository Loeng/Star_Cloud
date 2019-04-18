package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderDao
 * @Description 订单管理
 * @Author YQ
 * @Date 2019/4/9 10:26
 * @Version 1.0
 */
@Repository
public class OrderDao {

    @Resource
    private OrderMapper orderMapper;

    // 订单表插入数据
    public int insertOrder(Map param) throws SQLException {
        return orderMapper.insertOrder(param);
    }


    // 订单历史表插入数据
    public int insertOrderHistroy(long ID, long ORDER_ID, Date STATUS_UPDATE_TIME,int ORDER_STATUS) throws SQLException{
        return orderMapper.insertOrderHistroy(ID, ORDER_ID, STATUS_UPDATE_TIME, ORDER_STATUS);
    }


    // 通过订单ID 查询订单详细信息
    public Map queryOrderByOrderID(long ORDER_ID){
        return orderMapper.queryOrderByOrderID(ORDER_ID);
    }

    public int updateOrderByOrderID(Map param) throws SQLException {
        return orderMapper.updateOrderByOrderID(param);
    }


    public int insertOrderVoucher(Map param) throws SQLException {
        return orderMapper.insertOrderVoucher(param);
    }


    public int updateOrderVoucher(Map param) throws SQLException {
        return orderMapper.updateOrderVoucher(param);
    }

    public int deleteOrderVoucher(long ORDER_ID){
        return orderMapper.deleteOrderVoucher(ORDER_ID);
    }

    public List<Map> queryAllOrderByCondition(String ORDER_ID,
                                              Date START_TIME,
                                              Date END_TIME,
                                              String PRODUCT_TYPE_CODE,
                                              String PAYING_TYPE,
                                              String ORDER_STATUS,
                                              String ORDER_BY){
        return orderMapper.queryAllOrderByCondition(ORDER_ID, START_TIME, END_TIME, PRODUCT_TYPE_CODE, PAYING_TYPE, ORDER_STATUS, ORDER_BY);
    }

    public List<Map> queryOrderByUserId(String KEYWORD,
                                        String ORDER_STATUS,
                                        String ORDER_BY,
                                        String USER_ID){
        return orderMapper.queryOrderByUserId(KEYWORD, ORDER_STATUS, ORDER_BY, USER_ID);
    }
}
