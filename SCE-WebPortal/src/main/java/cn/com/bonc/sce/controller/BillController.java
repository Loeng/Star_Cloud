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
     *
     * @param refundInfo
     * @return
     */
    @PostMapping("/applyForRefund")
    @ResponseBody
    public RestRecord applyForRefund(@RequestBody Map<String, Object> refundInfo) {
        return billService.applyForRefund(refundInfo);
    }

    /**
     * 审核退款
     *
     * @param auditInfo
     * @return
     */
    @PutMapping("/auditRefund")
    @ResponseBody
    public RestRecord auditRefund(@RequestBody Map<String, Object> auditInfo) {
        return billService.auditRefund(auditInfo);
    }

    /**
     * 获取退款当月统计
     *
     * @return
     */
    @GetMapping("/getRefundStatistics")
    @ResponseBody
    public RestRecord getRefundStatistics() {
        return billService.getRefundStatistics();
    }

    /**
     * 退款列表
     */
    @GetMapping("/getRefundList/{ID}/{ORDER_ID}/{start_time}/{end_time}")
    @ResponseBody
    public RestRecord getRefundList(@PathVariable("ID") String ID, @PathVariable("ORDER_ID") String ORDER_ID,
                                    @PathVariable("start_time") String start_time, @PathVariable("end_time") String end_time) {
        return billService.getRefundList(ID, ORDER_ID, start_time, end_time);
    }

    /**
     * 退款审核详情
     */
    @GetMapping("/getRefundDetails/{ID}")
    @ResponseBody
    public RestRecord getRefundDetails(@PathVariable("ID") String ID) {
        return billService.getRefundDetails(ID);
    }

    /**
     * 查看我的银行卡
     */
    @GetMapping("/getBankCard/{USER_ID}")
    @ResponseBody
    public RestRecord getBankCard(@PathVariable("USER_ID") String USER_ID) {
        return billService.getBankCard(USER_ID);
    }

    /**
     * 添加我的银行卡
     */
    @PostMapping("/addBankCard")
    @ResponseBody
    public RestRecord addBankCard(@RequestBody Map<String, Object> cardInfo) {
        return billService.addBankCard(cardInfo);
    }

    /**
     * 修改我的银行卡
     */
    @PutMapping("/updateBankCard")
    @ResponseBody
    public RestRecord updateBankCard(@RequestBody Map<String, Object> cardInfo) {
        return billService.updateBankCard(cardInfo);
    }


}
