package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.CouponDao;
import cn.com.bonc.sce.rest.RestRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName CouponService
 * @Description 优惠码
 * @Author YQ
 * @Date 2019/4/2 15:30
 * @Version 1.0
 */
@Slf4j
@Service
public class CouponService {

    @Autowired
    private CouponDao couponDao;

    public RestRecord addNewCoupon(Map param,String userId){

        return couponDao.addNewCoupon(param, userId);
    }


    public RestRecord deleteCoupon(String couponCode){
        return couponDao.deleteCoupon(couponCode);
    }

    public RestRecord reviewCoupon(Map param,String userID){
        return couponDao.reviewCoupon(param, userID);
    }
}
