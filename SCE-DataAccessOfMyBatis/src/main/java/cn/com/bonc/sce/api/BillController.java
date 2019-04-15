package cn.com.bonc.sce.api;


import cn.com.bonc.sce.dao.BillDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
        refundInfo.put("ID", idWorker.nextId());
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

    @GetMapping("/getRefundList/{ID}/{ORDER_ID}/{start_time}/{end_time}")
    @ResponseBody
    public RestRecord getRefundList(@PathVariable("ID") String ID, @PathVariable("ORDER_ID") String ORDER_ID,
                                    @PathVariable("start_time") String start_time, @PathVariable("end_time") String end_time) {
        return new RestRecord(200, billDao.getRefundList(ID, ORDER_ID, start_time, end_time));
    }

    @GetMapping("/getRefundDetails/{ID}")
    @ResponseBody
    public RestRecord getRefundDetails(@PathVariable("ID") String ID) {
        return new RestRecord(200, billDao.getRefundDetails(ID));
    }

    @GetMapping("/getBankCard/{USER_ID}")
    @ResponseBody
    public RestRecord getBankCard(@PathVariable("USER_ID") String USER_ID) {
        return new RestRecord(200, billDao.getBankCard(USER_ID));
    }

    @PostMapping("/addBankCard/{ID}")
    @ResponseBody
    public RestRecord addBankCard(@RequestBody Map<String, Object> cardInfo) {
        cardInfo.put("ID", idWorker.nextId());
        return new RestRecord(200, billDao.addBankCard(cardInfo));
    }

    @PutMapping("/updateBankCard/{ID}")
    @ResponseBody
    @Transactional
    public RestRecord updateBankCard(@RequestBody Map<String, Object> cardInfo) {
        billDao.updateBankCard(cardInfo);
        cardInfo.put("ID",idWorker.nextId());
        return new RestRecord(200, billDao.addBankCard(cardInfo));
    }

    @GetMapping("/getTransactionRecordStatistics")
    @ResponseBody
    public RestRecord getTransactionRecordStatistics() {
        return new RestRecord(200, billDao.getTransactionRecordStatistics());
    }

    @GetMapping("/getTransactionRecordList/{ID}/{ORDER_ID}/{start_time}/{end_time}")
    @ResponseBody
    public RestRecord getTransactionRecordList(@PathVariable("ID") String ID, @PathVariable("ORDER_ID") String ORDER_ID,
                                               @PathVariable("PAYING_TYPE") String PAYING_TYPE,
                                               @PathVariable("start_time") String start_time, @PathVariable("end_time") String end_time) {
        return new RestRecord(200, billDao.getTransactionRecordList(ID,ORDER_ID,PAYING_TYPE,start_time,end_time));
    }

    @GetMapping("/getTransactionRecordDetail/{ID}/{PAYING_TYPE}")
    @ResponseBody
    public RestRecord getTransactionRecordDetail(@PathVariable("ID") String ID, @PathVariable("PAYING_TYPE") String PAYING_TYPE) {
        return new RestRecord(200, billDao.getTransactionRecordDetail(ID,PAYING_TYPE));
    }

    @PutMapping("/updateTransactionRecord/")
    @ResponseBody
    public RestRecord updateTransactionRecord(@RequestBody Map<String, Object> transactionRecordInfo) {
        return new RestRecord(200, billDao.updateTransactionRecord(transactionRecordInfo));
    }

}
