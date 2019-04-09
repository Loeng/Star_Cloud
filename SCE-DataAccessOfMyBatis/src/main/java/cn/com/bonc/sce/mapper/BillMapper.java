package cn.com.bonc.sce.mapper;

import java.util.Map;

public interface BillMapper {

    int applyForRefund(Map<String, Object> refundInfo);

    int auditRefund(Map<String, Object> auditInfo);

    Map getRefundStatistics();

}
