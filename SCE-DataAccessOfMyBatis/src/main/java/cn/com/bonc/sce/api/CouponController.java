package cn.com.bonc.sce.api;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.dao.CouponDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CouponService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "新增优惠码", notes="新增优惠码", httpMethod = "POST")
    @PostMapping("/insertCoupon")
    public RestRecord addNewCoupon(@RequestBody Map param,@RequestParam String userId){

        return couponService.addNewCoupon(param, userId);
    }


    @ApiOperation(value = "删除优惠码", notes="删除优惠码", httpMethod = "DELETE")
    @DeleteMapping("/deleteCoupon")
    public RestRecord deleteCoupon(@RequestParam String couponCode){
        return couponService.deleteCoupon(couponCode);
    }


    @ApiOperation(value = "优惠码审核", notes="优惠码审核", httpMethod = "PUT")
    @PutMapping("/reviewCoupon")
    public RestRecord reviewCoupon(@RequestBody Map param,
                                   @RequestParam String userID){
        return couponService.reviewCoupon(param, userID);
    }

}
