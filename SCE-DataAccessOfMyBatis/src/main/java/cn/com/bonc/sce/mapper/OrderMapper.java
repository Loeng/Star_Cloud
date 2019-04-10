package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * @InterfaceName OrderMapper
 * @Description 订单管理
 * @Author YQ
 * @Date 2019/4/9 10:26
 * @Version 1.0
 */
public interface OrderMapper {

    public int insertOrder(Map param) throws SQLException;

    public int insertOrderHistroy(@Param("ID") long ID,
                                  @Param("ORDER_ID") long ORDER_ID,
                                  @Param("STATUS_UPDATE_TIME") Date STATUS_UPDATE_TIME,
                                  @Param("ORDER_STATUS") int ORDER_STATUS) throws SQLException;

    public Map queryOrderByOrderID(@Param("ORDER_ID") long ORDER_ID);

    public int cancelOrder(@Param("ORDER_ID") long ORDER_ID) throws SQLException;


    public int insertOrderVoucher(Map param) throws SQLException;

}