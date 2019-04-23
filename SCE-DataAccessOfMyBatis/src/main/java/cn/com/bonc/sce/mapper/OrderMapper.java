package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName OrderMapper
 * @Description 订单管理
 * @Author YQ
 * @Date 2019/4/9 10:26
 * @Version 1.0
 */
public interface OrderMapper {

     int insertOrder(Map param) throws SQLException;

     int insertOrderHistroy(@Param("ORDER_ID") long ORDER_ID,
                            @Param("STATUS_UPDATE_TIME") Date STATUS_UPDATE_TIME,
                            @Param("ORDER_STATUS") int ORDER_STATUS) throws SQLException;

     Map queryOrderByOrderID(@Param("ORDER_ID") long ORDER_ID);

     int updateOrderByOrderID(Map param) throws SQLException;

     int updateOrderScheduled() throws SQLException;

     int updateOrderOverdue() throws SQLException;

     int insertOrderVoucher(Map param) throws SQLException;

     int updateOrderVoucher(Map param) throws SQLException;

     int deleteOrderVoucher(@Param("ORDER_ID") long ORDER_ID);

     List<Map> queryAllOrderByCondition(@Param("ORDER_ID") String ORDER_ID,
                                        @Param("START_TIME") Date START_TIME,
                                        @Param("END_TIME") Date END_TIME,
                                        @Param("PRODUCT_TYPE_CODE") String PRODUCT_TYPE_CODE,
                                        @Param("PAYING_TYPE") String PAYING_TYPE,
                                        @Param("ORDER_STATUS") String ORDER_STATUS,
                                        @Param("ORDER_BY") String ORDER_BY);

     List<Map> queryOrderByUserId(@Param("KEYWORD") String KEYWORD,
                                  @Param("ORDER_STATUS") String ORDER_STATUS,
                                  @Param("ORDER_BY") String ORDER_BY,
                                  @Param("USER_ID") String USER_ID);

}
