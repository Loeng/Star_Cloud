package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.bean.CouponBean;
import cn.com.bonc.sce.mapper.CouponMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SouponDao
 * @Description
 * @Author YQ
 * @Date 2019/4/2 9:57
 * @Version 1.0
 */

@Repository
public class CouponDao {

    @Resource
    private CouponMapper couponMapper;

    public List<Map> queryCouponType(){
        return couponMapper.queryCouponType();
    }

    public List<Map> queryGoodsType(){
        return couponMapper.queryGoodsType();
    }

    public int insertCoupon(CouponBean couponBean) throws SQLException {
        return couponMapper.insertCoupon(couponBean);
    }


    public int insertCouponGoogsTypeRel(List<Map<String,String>> list) throws SQLException {
        return couponMapper.insertCouponGoogsTypeRel(list);
    }


    public int insertCouponRebateDetial(String couponCode,
                                        int couponTypeCode,
                                        BigDecimal rebateDetial,
                                        BigDecimal rebateCal) throws SQLException {
        return couponMapper.insertCouponRebateDetial(couponCode, couponTypeCode, rebateDetial, rebateCal);
    }


    public int deleteCoupon(String couponCode) throws SQLException {
        return couponMapper.deleteCoupon(couponCode);
    }


    public int reviewCoupon(String couponCode,
                            String reviewState,
                            String reviewComment,
                            String userID,
                            Date reviewDate) throws SQLException{
        return couponMapper.reviewCoupon(couponCode,reviewState, reviewComment, userID, reviewDate);
    }


    public List<Map> queryAgentCoupon(String userId){
        return couponMapper.queryAgentCoupon(userId);
    }

    public List<Map> queryAllCouponByCondition(String COUPON_CODE,
                                               String USER_NAME,
                                               String COUPON_TYPE_CODE,
                                               String REVIEW_STATE,
                                               String OVER_FLAG,
                                               String ORDER_BY){
        return couponMapper.queryAllCouponByCondition(COUPON_CODE, USER_NAME, COUPON_TYPE_CODE, REVIEW_STATE, OVER_FLAG, ORDER_BY);
    }


    public Map queryCouponByCode(String COUPON_CODE){
        return couponMapper.queryCouponByCode(COUPON_CODE);
    }

    public int changeUnLimitedCouponUseTimes( String COUPON_CODE, long USED_TIMES) throws SQLException {
        return couponMapper.changeUnLimitedCouponUseTimes(COUPON_CODE, USED_TIMES);
    }

    public int changeLimitedCouponUseTimes( String COUPON_CODE, long VALID_USE_TIMES, long USED_TIMES) throws SQLException {
        return couponMapper.changeLimitedCouponUseTimes(COUPON_CODE, VALID_USE_TIMES, USED_TIMES);
    }


}
