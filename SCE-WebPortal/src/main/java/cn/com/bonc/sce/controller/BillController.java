package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Api(value = "订单相关接口", tags = "订单相关接口")
@ApiResponses({@ApiResponse(code = 500, message = "服务器内部错误", response = RestRecord.class)})
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    /**
     * 发起退款
     * @param refundInfo
     * @return
     */
    @PostMapping("/applyForRefund")
    @ResponseBody
    public RestRecord applyForRefund(@RequestBody Map<String, Object> refundInfo) {
        return billService.applyForRefund(refundInfo);
    }

    @PutMapping("/auditRefund")
    @ResponseBody
    public RestRecord auditRefund(@RequestBody Map<String, Object> auditInfo) {
        return billService.auditRefund(auditInfo);
    }

    @GetMapping("/getRefundStatistics")
    @ResponseBody
    public RestRecord getRefundStatistics() {
        return billService.getRefundStatistics();
    }

}
