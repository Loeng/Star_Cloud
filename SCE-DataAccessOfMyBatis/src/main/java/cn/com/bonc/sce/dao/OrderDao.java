package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
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

    public int insertOrder(Map param) throws SQLException {
        return orderMapper.insertOrder(param);
    }
}
