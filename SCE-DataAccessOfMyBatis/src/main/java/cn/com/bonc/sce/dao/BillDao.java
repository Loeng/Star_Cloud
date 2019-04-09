package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class BillDao {

    @Autowired(required = false)
    private BillMapper billMapper;

    public int applyForRefund(Map<String, Object> refundInfo) {
        return billMapper.applyForRefund(refundInfo);
    }

    public int auditRefund(Map<String, Object> auditInfo) {
        return billMapper.auditRefund(auditInfo);
    }

    public Map getRefundStatistics() {
        return billMapper.getRefundStatistics();
    }

}
