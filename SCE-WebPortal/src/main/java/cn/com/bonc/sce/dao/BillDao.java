package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
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

}
