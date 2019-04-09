package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.OrderDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName OrderService
 * @Description 订单管理
 * @Author YQ
 * @Date 2019/4/9 10:25
 * @Version 1.0
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private CouponService couponService;

    @Autowired
    private OrderDao orderDao;

    @Transactional(rollbackFor = Exception.class)
    public RestRecord addNewOrder(Map parm){

        // 校验所需参数是否齐全
        if (!parm.containsKey("PAYING_TYPE")
                || !parm.containsKey("BUYING_USER_ID")
                || !parm.containsKey("PRODUCT_NAME")
                || !parm.containsKey("PRODUCT_ID")
                || !parm.containsKey("PRODUCT_TYPE_CODE")
                || !parm.containsKey("PRICE_CALCULATION_METHOD")
                || !parm.containsKey("ORIGIN_PRICE")
                || !parm.containsKey("DISCOUNT_PRICE")
                || !parm.containsKey("ACCOUNT_PAYABLE")
                || !parm.containsKey("ACTUAL_PAYMENT")){
            return new RestRecord(431,WebMessageConstants.SCE_PORTAL_MSG_431);
        }


        // 若有优惠码  先判断优惠码是否可用   使用次数是否用完 等
        if (parm.containsKey("COUPON_CODE")){

            RestRecord restRecord=couponService.reduceCouponUseTimesByCode(parm.get("COUPON_CODE").toString(),
                    ","+parm.get("PRODUCT_TYPE_CODE")+",");

            if (restRecord.getCode()!=200){
                return restRecord;
            }

        }


        // 为一些参数初始化
        long ORDER_ID=idWorker.nextId();
        parm.put("ORDER_ID",ORDER_ID);

        parm.put("ORDER_CREATE_TIME",new Date());

        parm.put("ORDER_STATUS",0);

        parm.put("IS_DELETE",1);


        try {
            int count=orderDao.insertOrder(parm);

            if (count<1){
                return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
        }

        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,ORDER_ID);
    }
}
