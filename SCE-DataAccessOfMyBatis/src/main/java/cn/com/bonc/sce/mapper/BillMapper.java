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

    int updateBankCard(Map<String, Object> cardInfo);

    Map getTransactionRecordStatistics();

    List<Map> getTransactionRecordList(@Param("ID") String ID, @Param("ORDER_ID") String ORDER_ID,
                                       @Param("PAYING_TYPE") String PAYING_TYPE,
                                       @Param("start_time") String start_time, @Param("end_time") String end_time);

    Map getTransactionRecordDetail(@Param("ID") String ID, @Param("PAYING_TYPE") String PAYING_TYPE);

    int updateACCOUNT_ENTRY_TIME(Map<String, Object> transactionRecordInfo);

    int updateDEAL_REMARKS(Map<String, Object> transactionRecordInfo);

}
