package cn.com.bonc.sce.service;

import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.OrderDao;
import cn.com.bonc.sce.rest.RestRecord;
import cn.com.bonc.sce.tool.IdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

        parm.put("STATE_UPDATE_TIME",NOW_TIME); // 订单最近状态更新时间

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
            Map sendMap = new HashMap();
            sendMap.put("ORDER_ID",ORDER_ID);
            sendMap.put("ORDER_STATUS",2);
            sendMap.put("STATE_UPDATE_TIME",new Date());

            int count=orderDao.updateOrderByOrderID(sendMap);
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
    public RestRecord uploadVoucher(Map param){

        // 数据校验

        if(param==null || !param.containsKey("UPLOAD_USER_ID") || !param.containsKey("PAYING_VOUCHER")){
            return new RestRecord(431,WebMessageConstants.SCE_PORTAL_MSG_431);
        }


        // 先删除凭证表里对应ORDER_ID的数据（逻辑删除）   再新增凭证
        // 再次上传凭证时  之前的凭证作废
        orderDao.deleteOrderVoucher(Long.parseLong(param.get("ORDER_ID").toString()));

        // 初始化一些数据
        long ID = idWorker.nextId(); // 主键ID
        param.put("ID",ID);
        param.put("UPLOAD_TIME",new Date());

        // 插入数据到线下支付凭证表
        try {
            int count = orderDao.insertOrderVoucher(param);
            if (count<1){
                return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
        }

        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200);
    }



    /* *
     * @Description 线下凭证审核
     * @Date 10:44 2019/4/11
     * @param param
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    public RestRecord reviewVoucher(Map param){

        param.put("REVIEW_TIME",new Date()); // 审核时间

        // 修改凭证表里对应数据的状态
        try {
            int count = orderDao.updateOrderVoucher(param);
            if (count<1){
                return new RestRecord(421,WebMessageConstants.SCE_PORTAL_MSG_421);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new RestRecord(421,WebMessageConstants.SCE_PORTAL_MSG_421);
        }


        // 如果支付凭证审核通过   还需修改订单表里对应数据的支付状态为已支付

        //审核状态 0待审核 1审核通过 2审核未通过
        int AUDITING_STATUS=Integer.parseInt(param.get("AUDITING_STATUS").toString());
        if (AUDITING_STATUS==1){

            Map sendMap=new HashMap();
            sendMap.put("ORDER_ID",param.get("ORDER_ID"));
            sendMap.put("ORDER_STATUS",1);
            sendMap.put("STATE_UPDATE_TIME",new Date());

            // 修改order_info表里对应数据的状态
            try {
                int count2=orderDao.updateOrderByOrderID(sendMap);
                if (count2<1){
                    return new RestRecord(421,WebMessageConstants.SCE_PORTAL_MSG_421);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new RestRecord(421,WebMessageConstants.SCE_PORTAL_MSG_421);
            }


            // 订单的状态发生变化   往order_histroy 表里增加对应数据
            long ID = idWorker.nextId(); // order_histroy 主键
            try {
                int count3 = orderDao.insertOrderHistroy(ID,
                        Long.parseLong(param.get("ORDER_ID").toString()),
                        new Date(), 1);
                if (count3<1){
                    return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }

        }

        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200);
    }



    public RestRecord queryAllOrderByCondition(String ORDER_ID,
                                               String START_TIME,
                                               String END_TIME,
                                               String PRODUCT_TYPE_CODE,
                                               String PAYING_TYPE,
                                               String ORDER_STATUS,
                                               String ORDER_BY,
                                               int pageNum,
                                               int pageSize){

        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        Date START_TIME_TEMP = null,END_TIME_TEMP=null;

        if (START_TIME!=null && END_TIME!=null && !"".equals(START_TIME) && !"".equals(END_TIME)){

            try {
                START_TIME_TEMP = sdf.parse(START_TIME);
                END_TIME_TEMP = sdf.parse(END_TIME);
            } catch (ParseException e) {
                e.printStackTrace();
                return new  RestRecord(740,WebMessageConstants.SCE_PORTAL_MSG_740);
            }

        }


        PageHelper.startPage(pageNum, pageSize);

        PageInfo<Map> pageInfo=new PageInfo<>(orderDao.queryAllOrderByCondition(ORDER_ID,START_TIME_TEMP,END_TIME_TEMP,PRODUCT_TYPE_CODE,PAYING_TYPE,ORDER_STATUS,ORDER_BY));

        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,pageInfo);

    }



    public RestRecord queryOrderByUserId( String KEYWORD,
                                          String ORDER_STATUS,
                                          String ORDER_BY,
                                          String USER_ID,
                                          int pageNum,
                                          int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Map> pageInfo=new PageInfo<>(orderDao.queryOrderByUserId(KEYWORD, ORDER_STATUS, ORDER_BY, USER_ID));
        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,pageInfo);
    }


    /* *
     * @Description 已支付 已取消的订单变更为已关闭
     * @Date 16:27 2019/4/19
     * @param
     * @return void
     */
    public void updateOrderStatus(){
        //// TODO
        System.out.println("定时启动没---------------------");
    }



}
