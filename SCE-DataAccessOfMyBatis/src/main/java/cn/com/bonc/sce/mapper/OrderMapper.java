package cn.com.bonc.sce.mapper;

import java.sql.SQLException;
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

}
