package cn.com.bonc.sce.api;

import cn.com.bonc.sce.dao.CouponDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName CouponController
 * @Description 优惠码
 * @Author YQ
 * @Date 2019/4/2 9:51
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/insertCoupon")
    public RestRecord addNewCoupon(@RequestBody Map param,@RequestParam String userId){

        return couponService.addNewCoupon(param, userId);
    }

}
