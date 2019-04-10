package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public List<Map> getRefundList(String ID, String ORDER_ID, String start_time, String end_time) {
        return billMapper.getRefundList(ID, ORDER_ID, start_time, end_time);
    }

    public Map getRefundDetails(String ID) {
        return billMapper.getRefundDetails(ID);
    }

    public Map getBankCard(String USER_ID) {
        return billMapper.getBankCard(USER_ID);
    }

    public int addBankCard(Map<String, Object> cardInfo) {
        return billMapper.addBankCard(cardInfo);
    }

    public int updateBankCard(Map<String, Object> cardInfo) {
        return billMapper.updateBankCard(cardInfo);
    }

}
