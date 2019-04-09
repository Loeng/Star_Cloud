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

    // 优惠类型查询
    public RestRecord queryCouponType(){
        return couponDao.queryCouponType();
    }

    // 商品类型查询
    public RestRecord queryGoodsType(){
        return couponDao.queryGoodsType();
    }

    public RestRecord addNewCoupon(Map param,String userId){

        return couponDao.addNewCoupon(param, userId);
    }


    public RestRecord deleteCoupon(String couponCode){
        return couponDao.deleteCoupon(couponCode);
    }

    public RestRecord reviewCoupon(Map param,String userID){
        return couponDao.reviewCoupon(param, userID);
    }

    public RestRecord queryAllCouponByCondition(String COUPON_CODE,
                                                String USER_NAME,
                                                String COUPON_TYPE_CODE,
                                                String REVIEW_STATE,
                                                String OVER_FLAG,
                                                String ORDER_BY,
                                                int pageNum,
                                                int pageSize){
        return couponDao.queryAllCouponByCondition(COUPON_CODE, USER_NAME, COUPON_TYPE_CODE, REVIEW_STATE, OVER_FLAG, ORDER_BY, pageNum, pageSize);
    }

    public RestRecord queryAgentCoupon(String userId, int pageNum, int pageSize){
        return couponDao.queryAgentCoupon(userId,pageNum,pageSize);
    }

    public RestRecord calCoupon(String COUPON_CODE, String PRODUCT_TYPE_CODE, String ORIGIN_PRICE){
        return couponDao.calCoupon(COUPON_CODE, PRODUCT_TYPE_CODE, ORIGIN_PRICE);
    }
}
