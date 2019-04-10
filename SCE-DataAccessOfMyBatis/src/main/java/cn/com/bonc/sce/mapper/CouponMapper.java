package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.CouponBean;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName SouponMapper
 * @Description
 * @Author YQ
 * @Date 2019/4/2 9:57
 * @Version 1.0
 */
public interface CouponMapper {

    List<Map> queryCouponType();

    List<Map> queryGoodsType();

    int insertCoupon(CouponBean couponBean) throws SQLException;

    int insertCouponGoogsTypeRel(@Param("list")List<Map<String,String>> list) throws SQLException;

    int insertCouponRebateDetial(@Param("COUPON_CODE") String couponCode,
                                 @Param("COUPON_TYPE_CODE") int couponTypeCode,
                                 @Param("REBATE_DETAIL") BigDecimal rebateDetial,
                                 @Param("REBATE_CAL") BigDecimal rebateCal) throws SQLException;

    int deleteCoupon(@Param("COUPON_CODE") String couponCode) throws SQLException;

    int reviewCoupon(@Param("COUPON_CODE") String couponCode,
                     @Param("REVIEW_STATE") String reviewState,
                     @Param("REVIEW_COMMENT") String reviewComment,
                     @Param("REVIEW_USER_ID") String userID,
                     @Param("REVIEW_DATE") Date reviewDate) throws SQLException;

    List<Map> queryAgentCoupon(@Param("USER_ID") String userId);

    List<Map> queryAllCouponByCondition(@Param("COUPON_CODE") String COUPON_CODE,
                                        @Param("USER_NAME") String USER_NAME,
                                        @Param("COUPON_TYPE_CODE") String COUPON_TYPE_CODE,
                                        @Param("REVIEW_STATE") String REVIEW_STATE,
                                        @Param("OVER_FLAG") String OVER_FLAG,
                                        @Param("ORDER_BY") String ORDER_BY);

    Map queryCouponByCode(@Param("COUPON_CODE") String COUPON_CODE);

    int changeUnLimitedCouponUseTimes(@Param("COUPON_CODE") String COUPON_CODE,
                                      @Param("USED_TIMES") long USED_TIMES) throws SQLException;

    int changeLimitedCouponUseTimes(@Param("COUPON_CODE") String COUPON_CODE,
                                    @Param("VALID_USE_TIMES") long VALID_USE_TIMES,
                                    @Param("USED_TIMES") long USED_TIMES) throws SQLException;


}
