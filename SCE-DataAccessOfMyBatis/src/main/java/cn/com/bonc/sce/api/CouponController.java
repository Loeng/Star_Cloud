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


    @ApiOperation(value = "优惠码优惠方式查询", notes="优惠码优惠方式查询", httpMethod = "GET")
    @GetMapping("/queryCouponType")
    public RestRecord queryCouponType(){
        return couponService.queryCouponType();
    }


    @ApiOperation(value = "商品类型查询", notes="商品类型查询", httpMethod = "GET")
    @GetMapping("/queryGoodsType")
    public RestRecord queryGoodsType(){
        return couponService.queryGoodsType();
    }




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


    @ApiOperation(value = "优惠码查询（运维管理）", notes="优惠码查询（运维管理）", httpMethod = "GET")
    @GetMapping("/queryAllCouponByCondition")
    public RestRecord queryAllCouponByCondition(@RequestParam(required = false) String COUPON_CODE,
                                                @RequestParam(required = false) String USER_NAME,
                                                @RequestParam(required = false) String COUPON_TYPE_CODE,
                                                @RequestParam(required = false) String REVIEW_STATE,
                                                @RequestParam(required = false) String OVER_FLAG,
                                                @RequestParam String ORDER_BY,
                                                @RequestParam int pageNum,
                                                @RequestParam int pageSize){
        return couponService.queryAllCouponByCondition(COUPON_CODE, USER_NAME, COUPON_TYPE_CODE, REVIEW_STATE, OVER_FLAG, ORDER_BY, pageNum, pageSize);
    }


    @ApiOperation(value = "优惠码查询（代理商）", notes="优惠码查询（代理商）", httpMethod = "GET")
    @GetMapping("/queryAgentCoupon")
    public RestRecord queryAgentCoupon(@RequestParam String userId,
                                       @RequestParam int pageNum,
                                       @RequestParam int pageSize){
        return couponService.queryAgentCoupon(userId, pageNum, pageSize);
    }


    @ApiOperation(value = "优惠码优惠方式计算", notes="优惠码优惠方式计算", httpMethod = "GET")
    @GetMapping("/calCoupon")
    public RestRecord calCoupon(@RequestParam String COUPON_CODE,
                                @RequestParam String PRODUCT_TYPE_CODE,
                                @RequestParam String ORIGIN_PRICE){
        return couponService.calCoupon(COUPON_CODE, PRODUCT_TYPE_CODE, ORIGIN_PRICE);
    }


    @ApiOperation(value = "优惠码详情查询", notes="优惠码详情查询", httpMethod = "GET")
    @GetMapping("/queryCouponByCode")
    public RestRecord queryCouponByCode(@RequestParam String COUPON_CODE){
        return couponService.queryCouponByCode(COUPON_CODE);
    }

}
