package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.OrderDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${OrderDeadline.OnLine}")
    private int OnLineDeadLine; // 线上支付 订单过期时间

    @Value("${OrderDeadline.OffLine}")
    private int OffLineDeadLine; // 线下支付 订单过期时间


    /* *
     * @Description 订单生成
     * @Date 9:28 2019/4/10
     * @param parm
     * @return cn.com.bonc.sce.rest.RestRecord
     */
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

        Date NOW_TIME=new Date(); // 当前时间
        parm.put("ORDER_CREATE_TIME",NOW_TIME);

        int PAYING_TYPE=Integer.valueOf(parm.get("PAYING_TYPE").toString()); // 1:线上支付 2:线下支付
        // 根据支付方式  设置订单过期时间
        if (PAYING_TYPE==1){
            parm.put("ORDER_DEADLINE_TIME",new Date(NOW_TIME.getTime()+OnLineDeadLine*60*60*1000));
        }else {
            parm.put("ORDER_DEADLINE_TIME",new Date(NOW_TIME.getTime()+OffLineDeadLine*60*60*1000));
        }

        parm.put("ORDER_STATUS",0); //订单状态0待支付1已经支付2取消3关闭

        parm.put("IS_DELETE",1); // 1:未删除 0:已删除



        // 插入订单表
        try {
            int count=orderDao.insertOrder(parm);

            if (count<1){
                return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
        }


        // 插入订单历史表   方便以后查看整个订单的状态变化流程
        long ID=idWorker.nextId(); // 订单历史表主键
        try {
            int count2=orderDao.insertOrderHistroy(ID,ORDER_ID,NOW_TIME,0);
            if (count2<1){
                return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
        }


        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,ORDER_ID);
    }


    /* *
     * @Description 根据订单号查询订单信息
     * @Date 16:29 2019/4/10
     * @param ORDER_ID
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    public RestRecord queryOrderByOrderID(String ORDER_ID){

        long ORDERID = Long.parseLong(ORDER_ID);
        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,orderDao.queryOrderByOrderID(ORDERID));
    }



    /* *
     * @Description 取消订单
     * @Date 14:33 2019/4/10
     * @param param
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    @Transactional(rollbackFor = Exception.class)
    public RestRecord cancelOrder(Map param){

        long ORDER_ID = Long.parseLong(param.get("ORDER_ID").toString());


        // 先查询该订单的信息 看有没有优惠码   若有则进一步判断
        Map temp=orderDao.queryOrderByOrderID(ORDER_ID);
        if (temp.get("COUPON_CODE")!=null && !"".equals(temp.get("COUPON_CODE").toString()) ){

            // 恢复优惠码使用次数
            RestRecord restRecord = couponService.addCouponUseTimesByCode(temp.get("COUPON_CODE").toString());

            if (restRecord.getCode()!=200){
                return restRecord;
            }
        }



        //修改订单的状态
        try {
            int count=orderDao.cancelOrder(ORDER_ID);
            if (count<1){
                return new RestRecord(421,WebMessageConstants.SCE_PORTAL_MSG_421);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new RestRecord(421,WebMessageConstants.SCE_PORTAL_MSG_421);
        }


        //为该订单在历史表中增加一条数据

        long ID=idWorker.nextId(); // 订单历史表主键
        try {
            //订单状态  0待支付1已经支付2取消3关闭
            int count2=orderDao.insertOrderHistroy(ID,ORDER_ID,new Date(),2);
            if (count2<1){
                return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
        }

        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200);
    }


    /* *
     * @Description 上传交易凭证
     * @Date 15:20 2019/4/10
     * @param param
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    public RestRecord insertOrderVoucher(Map param){
        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200);
    }



}
