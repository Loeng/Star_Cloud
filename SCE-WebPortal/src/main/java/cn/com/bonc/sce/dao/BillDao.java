package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@FeignClient(value = "sce-data-mybatis")
@Repository
public interface BillDao {

    @RequestMapping(value = "/bill/applyForRefund", method = RequestMethod.POST)
    RestRecord applyForRefund(@RequestBody Map<String, Object> refundInfo);

    @RequestMapping(value = "/bill/auditRefund", method = RequestMethod.PUT)
    RestRecord auditRefund(@RequestBody Map<String, Object> auditInfo);

    @RequestMapping(value = "/bill/getRefundStatistics", method = RequestMethod.GET)
    RestRecord getRefundStatistics();

    @RequestMapping(value = "/bill/getRefundList/{ID}/{ORDER_ID}/{start_time}/{end_time}", method = RequestMethod.GET)
    RestRecord getRefundList(@PathVariable("ID") String ID, @PathVariable("ORDER_ID") String ORDER_ID,
                             @PathVariable("start_time") String start_time, @PathVariable("end_time") String end_time);

    @RequestMapping(value = "/bill/getRefundDetails/{ID}", method = RequestMethod.GET)
    RestRecord getRefundDetails(@PathVariable("ID") String ID);

    @RequestMapping(value = "/bill/getBankCard/{USER_ID}", method = RequestMethod.GET)
    RestRecord getBankCard(@PathVariable("USER_ID") String USER_ID);

    @RequestMapping(value = "/bill/addBankCard", method = RequestMethod.POST)
    RestRecord addBankCard(@RequestBody Map<String, Object> cardInfo);

    @RequestMapping(value = "/bill/updateBankCard", method = RequestMethod.PUT)
    RestRecord updateBankCard(@RequestBody Map<String, Object> cardInfo);

}
