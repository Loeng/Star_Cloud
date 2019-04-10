package cn.com.bonc.sce.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillMapper {

    int applyForRefund(Map<String, Object> refundInfo);

    int auditRefund(Map<String, Object> auditInfo);

    Map getRefundStatistics();

    List<Map> getRefundList(@Param("ID") String ID, @Param("ORDER_ID") String ORDER_ID,
                            @Param("start_time") String start_time, @Param("end_time") String end_time);

    Map getRefundDetails(String ID);

    Map getBankCard(String USER_ID);

    int addBankCard(Map<String, Object> cardInfo);

    int deleteBankCard(Map<String, Object> cardInfo);

    int updateBankCard(Map<String, Object> cardInfo);

}
