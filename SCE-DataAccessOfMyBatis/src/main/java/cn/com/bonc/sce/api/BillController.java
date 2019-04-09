package cn.com.bonc.sce.api;


import cn.com.bonc.sce.dao.BillDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BillDao billDao;

    @PostMapping("/applyForRefund")
    @ResponseBody
    public RestRecord applyForRefund(@RequestBody Map<String, Object> refundInfo) {
        refundInfo.put("ID",idWorker.nextId());
        return new RestRecord(200, billDao.applyForRefund(refundInfo));
    }

    @PostMapping("/auditRefund")
    @ResponseBody
    public RestRecord auditRefund(@RequestBody Map<String, Object> auditInfo) {
        return new RestRecord(200, billDao.auditRefund(auditInfo));
    }

    @GetMapping("/getRefundStatistics")
    @ResponseBody
    public RestRecord getRefundStatistics() {
        return new RestRecord(200, billDao.getRefundStatistics());
    }

}
