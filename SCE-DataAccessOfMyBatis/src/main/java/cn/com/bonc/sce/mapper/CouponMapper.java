package cn.com.bonc.sce.mapper;

import cn.com.bonc.sce.bean.CouponBean;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.SQLException;
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

    int insertCoupon(CouponBean couponBean) throws SQLException;

    int insertCouponGoogsTypeRel(@Param("list")List<Map<String,String>> list) throws SQLException;

    int insertCouponRebateDetial(@Param("COUPON_CODE") String couponCode,
                                 @Param("COUPON_TYPE_CODE") int couponTypeCode,
                                 @Param("REBATE_DETAIL") BigDecimal rebateDetial,
                                 @Param("REBATE_CAL") BigDecimal rebateCal) throws SQLException;

    int deleteCoupon(@Param("COUPON_CODE") String couponCode) throws SQLException;
}
