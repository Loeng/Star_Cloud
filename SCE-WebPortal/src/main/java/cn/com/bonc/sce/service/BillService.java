package cn.com.bonc.sce.service;


import cn.com.bonc.sce.dao.BillDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class BillService {

    @Autowired
    private BillDao billDao;

    public RestRecord applyForRefund(Map<String, Object> refundInfo) {
        return billDao.applyForRefund(refundInfo);
    }

    public RestRecord auditRefund(Map<String, Object> auditInfo) {
        return billDao.auditRefund(auditInfo);
    }

    public RestRecord getRefundStatistics() {
        return billDao.getRefundStatistics();
    }

    public RestRecord getRefundList(String ID, String ORDER_ID, String start_time, String end_time) {
        return billDao.getRefundList(ID, ORDER_ID, start_time, end_time);
    }

    public RestRecord getRefundDetails(String ID) {
        return billDao.getRefundDetails(ID);
    }

    public RestRecord getBankCard(String USER_ID) {
        return billDao.getBankCard(USER_ID);
    }

    public RestRecord addBankCard(Map<String, Object> cardInfo) {
        return billDao.addBankCard(cardInfo);
    }

    public RestRecord updateBankCard(Map<String, Object> cardInfo) {
        return billDao.updateBankCard(cardInfo);
    }

    public RestRecord getTransactionRecordStatistics() {
        return billDao.getTransactionRecordStatistics();
    }

    public RestRecord getTransactionRecordList(String ID, String ORDER_ID, String PAYING_TYPE, String start_time, String end_time) {
        return billDao.getTransactionRecordList(ID, ORDER_ID, PAYING_TYPE, start_time, end_time);
    }

    public RestRecord getTransactionRecordDetail(String ID, String PAYING_TYPE) {
        return billDao.getTransactionRecordDetail(ID, PAYING_TYPE);
    }

    public RestRecord updateTransactionRecord(Map<String, Object> transactionRecordInfo) {
        return billDao.updateTransactionRecord(transactionRecordInfo);
    }

}
