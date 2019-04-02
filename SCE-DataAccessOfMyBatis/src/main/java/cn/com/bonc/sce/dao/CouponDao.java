package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.mapper.CouponMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

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
}
