package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.rest.RestRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @InterfaceName CouponDao
 * @Description
 * @Author YQ
 * @Date 2019/4/2 15:56
 * @Version 1.0
 */
@Repository
@FeignClient(value = "sce-data-mybatis")
public interface CouponDao {


    @RequestMapping(value = "/coupon/queryCouponType",method = RequestMethod.GET)
    RestRecord queryCouponType();


    @RequestMapping(value = "/coupon/queryGoodsType",method = RequestMethod.GET)
    RestRecord queryGoodsType();


    @RequestMapping(value = "/coupon/queryAllCouponByCondition",method = RequestMethod.GET)
    RestRecord queryAllCouponByCondition(@RequestParam("COUPON_CODE") String COUPON_CODE,
                                                @RequestParam("USER_NAME") String USER_NAME,
                                                @RequestParam("COUPON_TYPE_CODE") String COUPON_TYPE_CODE,
                                                @RequestParam("REVIEW_STATE") String REVIEW_STATE,
                                                @RequestParam("OVER_FLAG") String OVER_FLAG,
                                                @RequestParam("ORDER_BY") String ORDER_BY,
                                                @RequestParam("pageNum") int pageNum,
                                                @RequestParam("pageSize") int pageSize);


    @RequestMapping(value = "/coupon/queryAgentCoupon",method = RequestMethod.GET)
    RestRecord queryAgentCoupon(@RequestParam("userId") String userId,
                                       @RequestParam("pageNum") int pageNum,
                                       @RequestParam("pageSize") int pageSize);

    @RequestMapping(value = "/coupon/insertCoupon",method = RequestMethod.POST)
    RestRecord addNewCoupon(@RequestBody Map param,
                                   @RequestParam("userId") String userId);


    @RequestMapping(value = "/coupon/deleteCoupon",method = RequestMethod.DELETE)
     RestRecord deleteCoupon(@RequestParam("couponCode") String couponCode);


    @RequestMapping(value = "/coupon/reviewCoupon",method = RequestMethod.PUT)
     RestRecord reviewCoupon(@RequestBody Map param,
                                   @RequestParam("userID") String userID);


    @RequestMapping(value = "/coupon/calCoupon",method = RequestMethod.GET)
     RestRecord calCoupon(@RequestParam("COUPON_CODE") String COUPON_CODE,
                                @RequestParam("PRODUCT_TYPE_CODE") String PRODUCT_TYPE_CODE,
                                @RequestParam("ORIGIN_PRICE") String ORIGIN_PRICE);

}
