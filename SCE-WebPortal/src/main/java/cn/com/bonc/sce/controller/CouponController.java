package cn.com.bonc.sce.controller;

import cn.com.bonc.sce.annotation.CurrentUserId;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName CouponController
 * @Description 优惠码相关操作
 * @Author YQ
 * @Date 2019/3/29 17:22
 * @Version 1.0
 */

@Slf4j
@RestController
@Api( value = "优惠码", tags = "优惠码"  )
@ApiResponses( {
        @ApiResponse( code = 500, message = "服务器内部错误", response = RestRecord.class ),
        @ApiResponse( code = 200, message = WebMessageConstants.SCE_PORTAL_MSG_200, response = RestRecord.class )
} )
@RequestMapping( "/soupon" )
public class CouponController {

    @Autowired
    private CouponService couponService;


    @PostMapping("/add-coupon")
    @ResponseBody
    public RestRecord addNewCoupon(@RequestBody Map param, @CurrentUserId String userId){
        return couponService.addNewCoupon(param,userId);
    }

    @GetMapping("/query-coupons")
    @ResponseBody
    public RestRecord queryCouponByCondition(){
        return null;
    }


    @PutMapping("/review-coupon")
    @ResponseBody
    public RestRecord reviewCoupon(){
        return null;
    }

    @DeleteMapping("/delete-coupon/{couponCode}")
    @ResponseBody
    public RestRecord deleteCoupon(@PathVariable String couponCode){
        return couponService.deleteCoupon(couponCode);
    }

}
