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


    @RequestMapping(value = "/coupon/insertCoupon",method = RequestMethod.POST)
    public RestRecord addNewCoupon(@RequestBody Map param,
                                   @RequestParam("userId") String userId);


    @RequestMapping(value = "/coupon/deleteCoupon",method = RequestMethod.DELETE)
    public RestRecord deleteCoupon(@RequestParam("couponCode") String couponCode);
}
