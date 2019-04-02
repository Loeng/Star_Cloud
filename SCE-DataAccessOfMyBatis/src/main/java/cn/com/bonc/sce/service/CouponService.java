package cn.com.bonc.sce.service;

import cn.com.bonc.sce.dao.CouponDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CouponService
 * @Description
 * @Author YQ
 * @Date 2019/4/2 9:56
 * @Version 1.0
 */
@Slf4j
@Service
public class CouponService {

    @Autowired
    private CouponDao couponDao;
}
