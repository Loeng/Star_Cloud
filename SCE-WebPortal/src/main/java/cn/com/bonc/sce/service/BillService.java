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
}
