package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.CouponBean;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.CouponDao;
import cn.com.bonc.sce.rest.RestRecord;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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


    //优惠方式查询
    public RestRecord queryCouponType(){
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200,couponDao.queryCouponType());
    }


    //商品类型查询
    public RestRecord queryGoodsType(){
        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200,couponDao.queryGoodsType());
    }



    // 新增优惠码
    @Transactional(rollbackFor = Exception.class)
    public RestRecord addNewCoupon(Map param,String userId){

        // 封装优惠码主规则
        CouponBean couponBean = null;
        try {
            couponBean = initCouponBean(param, userId);
        } catch (ParseException e) {
            e.printStackTrace();

            return new  RestRecord(740,WebMessageConstants.SCE_PORTAL_MSG_740);
        }


        // 插入优惠码主规则
        try {

            int count=couponDao.insertCoupon(couponBean);
            if (count<1){
                return new  RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }

        } catch (SQLException e) {
            e.printStackTrace();

            return new  RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
        }


        // 插入优惠码与商品类型的关系表
        List<Map<String,String>> templist=new ArrayList<Map<String, String>>();
        String [] GOODS_TYPE_CODES=param.get("GOODS_TYPE_CODE").toString().split(",");
        for (String str: GOODS_TYPE_CODES
             ) {
            Map<String,String> map=new HashMap<String, String>();
            map.put("COUPON_CODE",param.get("COUPON_CODE").toString());
            map.put("GOODS_TYPE_CODE",str);
            templist.add(map);
        }

        try {
            int count2=couponDao.insertCouponGoogsTypeRel(templist);
            if (count2<1){
                return new  RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return new  RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
        }


        // 根据优惠方式   插入 优惠码
        int COUPON_TYPE_CODE=Integer.valueOf(param.get("COUPON_TYPE_CODE").toString());
        if (COUPON_TYPE_CODE==1){ //  1 折扣
            BigDecimal rebate=new BigDecimal(param.get("REBATE_DETAIL").toString());
            BigDecimal rebate_cal=rebate.divide(new BigDecimal("10"));

            try {
                int count3 =couponDao.insertCouponRebateDetial(param.get("COUPON_CODE").toString(),COUPON_TYPE_CODE,rebate,rebate_cal);
                if (count3<1){
                    return new  RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
                }
            } catch (SQLException e) {
                e.printStackTrace();

                return new  RestRecord(423,WebMessageConstants.SCE_PORTAL_MSG_423);
            }
        }
        /* 后面若增加其它的优惠的方式  可在这继续实现 */


        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }


    /* *
     * @Description 优惠码删除（逻辑删除）
     * @Date 17:38 2019/4/3
     * @param couponCode
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    @Transactional(rollbackFor = Exception.class)
    public RestRecord deleteCoupon(String couponCode){

        try {
            int count=couponDao.deleteCoupon(couponCode);
            if (count<1){
                return new RestRecord(422, WebMessageConstants.SCE_PORTAL_MSG_422);
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return new RestRecord(422, WebMessageConstants.SCE_PORTAL_MSG_422);
        }

        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }


    /* *
     * @Description 优惠码审核操作
     * @Date 15:04 2019/4/4
     * @param reviewState
     * @param reviewComment
     * @param userId
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    @Transactional(rollbackFor = Exception.class)
    public RestRecord reviewCoupon(Map param, String userId){

        // 优惠码CODE
        String couponCode=param.get("COUPON_CODE").toString();

        //优惠码审核状态： T 待审核      F 审核未通过     E 审核通过
        String reviewState=param.get("REVIEW_STATE").toString();

        // 审核意见
        String reviewComment=param.get("REVIEW_COMMENT").toString();

        // 审核时间
        Date reviewDate=new Date();

        try {
            int count=couponDao.reviewCoupon(couponCode,reviewState,reviewComment,userId,reviewDate);
            if (count<1){
                return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
            }

        } catch (SQLException e) {
            e.printStackTrace();

            return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
        }

        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200);
    }


    /* *
     * @Description 优惠码查询（代理商）
     * @Date 17:14 2019/4/5
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    public RestRecord queryAgentCoupon(String userId,int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);

        PageInfo<Map> pageInfo=new PageInfo<>(couponDao.queryAgentCoupon(userId));

        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200,pageInfo);
    }


    /* *
     * @Description 优惠码查询（运维管理）
     * @Date 17:15 2019/4/5
     * @param COUPON_CODE
     * @param USER_NAME
     * @param COUPON_TYPE_CODE
     * @param REVIEW_STATE
     * @param OVER_FLAG
     * @param ORDER_BY
     * @param pageNum
     * @param pageSize
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    public RestRecord queryAllCouponByCondition(String COUPON_CODE,
                                                String USER_NAME,
                                                String COUPON_TYPE_CODE,
                                                String REVIEW_STATE,
                                                String OVER_FLAG,
                                                String ORDER_BY,
                                                int pageNum,
                                                int pageSize){

        PageHelper.startPage(pageNum, pageSize);

        PageInfo<Map> pageInfo=new PageInfo<>(couponDao.queryAllCouponByCondition(COUPON_CODE, USER_NAME, COUPON_TYPE_CODE, REVIEW_STATE, OVER_FLAG, ORDER_BY));

        return new RestRecord(200, WebMessageConstants.SCE_PORTAL_MSG_200,pageInfo);
    }



    /* *
     * @Description 优惠码的折扣实现
     * @Date 16:55 2019/4/9
     * @param COUPON_CODE
     * @param PRODUCT_TYPE_CODE
     * @param ORIGIN_PRICE
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    public RestRecord calCoupon(String COUPON_CODE,String PRODUCT_TYPE_CODE,String ORIGIN_PRICE){

        Map temp=couponDao.queryCouponByCode(COUPON_CODE);

        /******************** 判断该优惠码是否符合使用要求 *******************************/

        if ( temp==null|| temp.isEmpty()){
            return new RestRecord(741,WebMessageConstants.SCE_PORTAL_MSG_741);
        }

        PRODUCT_TYPE_CODE=","+PRODUCT_TYPE_CODE+","; // 两边添加逗号
        // 判断商品类型 是否符合该优惠码
        if (!",0,".equals(temp.get("GOODS_TYPE_CODE").toString())){
            if (temp.get("GOODS_TYPE_CODE").toString().indexOf(PRODUCT_TYPE_CODE)==-1){
                return new RestRecord(744,WebMessageConstants.SCE_PORTAL_MSG_744);
            }
        }

        //优惠码使用时间是否无限： 0 无限      1  使用时间有限制
        long VALID_DATE_FALG=Long.parseLong(temp.get("VALID_DATE_FALG").toString());
        if (VALID_DATE_FALG==1){
            Date NOW_TIME=new Date();
            java.sql.Date START_DATE= (java.sql.Date) temp.get("START_DATE");
            java.sql.Date END_DATE=(java.sql.Date) temp.get("END_DATE");

            // 判断优惠码使用时间是否过期
            if (NOW_TIME.before(START_DATE) || NOW_TIME.after(END_DATE)){
                return new RestRecord(742,WebMessageConstants.SCE_PORTAL_MSG_742);
            }
        }


        //使用次数是否无限：  0 无限      1 有限使用次数
        long USE_TIMES_FLAG=Long.parseLong(temp.get("USE_TIMES_FLAG").toString());
        long VALID_USE_TIMES=Long.parseLong(temp.get("VALID_USE_TIMES").toString());//该优惠码的可使用次数   为无限次时值为0
        if (USE_TIMES_FLAG==1){

            if (VALID_USE_TIMES<1){
                return new RestRecord(743,WebMessageConstants.SCE_PORTAL_MSG_743);
            }
        }


        /********************* 计算优惠金额 *****************************/
        // 目前只 计算折扣相关     后续有其它优惠方式   再进行代码修改

        // 转成BigDecimal类型进行计算  避免小数精度丢失
        BigDecimal TOTAL_MONEY=new BigDecimal(ORIGIN_PRICE); // 原价格
        BigDecimal REBATE_CAL=new BigDecimal(temp.get("REBATE_CAL").toString()); // 折扣率

        BigDecimal ACCOUNT_PAYABLE=TOTAL_MONEY.multiply(REBATE_CAL); // 实付金额
        BigDecimal DISCOUNT_PRICE=TOTAL_MONEY.subtract(ACCOUNT_PAYABLE); // 折扣金额



        Map result=new HashMap();
        result.put("TOTAL_MONEY",TOTAL_MONEY);
        result.put("ACCOUNT_PAYABLE",ACCOUNT_PAYABLE);
        result.put("DISCOUNT_PRICE",DISCOUNT_PRICE);



        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200,result);
    }


    /* *
     * @Description 优惠码使用次数减少
     * @Date 12:01 2019/4/9
     * @param COUPON_CODE
     * @return cn.com.bonc.sce.rest.RestRecord
     */
    public RestRecord reduceCouponUseTimesByCode(String COUPON_CODE,String PRODUCT_TYPE_CODE){

        Map temp = couponDao.queryCouponByCode(COUPON_CODE);

        if (temp.isEmpty() || temp==null){
            return new RestRecord(741,WebMessageConstants.SCE_PORTAL_MSG_741);
        }

        // 判断商品类型 是否符合该优惠码
        if (!",0,".equals(temp.get("GOODS_TYPE_CODE").toString())){
            if (temp.get("GOODS_TYPE_CODE").toString().indexOf(PRODUCT_TYPE_CODE)==-1){
                return new RestRecord(744,WebMessageConstants.SCE_PORTAL_MSG_744);
            }
        }

        //优惠码使用时间是否无限： 0 无限      1  使用时间有限制
        long VALID_DATE_FALG=Long.parseLong(temp.get("VALID_DATE_FALG").toString());
        if (VALID_DATE_FALG==1){
            Date NOW_TIME=new Date();
            java.sql.Date START_DATE= (java.sql.Date) temp.get("START_DATE");
            java.sql.Date END_DATE=(java.sql.Date) temp.get("END_DATE");

            // 判断优惠码使用时间是否过期
            if (NOW_TIME.before(START_DATE) || NOW_TIME.after(END_DATE)){
                return new RestRecord(742,WebMessageConstants.SCE_PORTAL_MSG_742);
            }
        }




        //使用次数是否无限：  0 无限      1 有限使用次数
        long USE_TIMES_FLAG=Long.parseLong(temp.get("USE_TIMES_FLAG").toString());
        long VALID_USE_TIMES=Long.parseLong(temp.get("VALID_USE_TIMES").toString());//该优惠码的可使用次数   为无限次时值为0
        long USED_TIMES=Long.parseLong(temp.get("USED_TIMES").toString()); // 优惠码已使用次数
        if (USE_TIMES_FLAG==1){

            if (VALID_USE_TIMES<1){
                return new RestRecord(743,WebMessageConstants.SCE_PORTAL_MSG_743);
            }
        }


        // 根据 使用次数是否无限  调用不同的修改方法(无限次时  有效使用次数无意义         有限次数时  有效使用次数才有意义)
        synchronized (this){
            if (USE_TIMES_FLAG==0){
                try {
                    int count = couponDao.reduceUnLimitedCouponUseTimes(COUPON_CODE,++USED_TIMES);
                    if (count<1){
                        return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
                }
            }else {

                try {
                    int count=couponDao.reduceLimitedCouponUseTimes(COUPON_CODE,--VALID_USE_TIMES,++USED_TIMES);
                    if (count<1){
                        return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new RestRecord(421, WebMessageConstants.SCE_PORTAL_MSG_421);
                }

            }
        }

        return new RestRecord(200,WebMessageConstants.SCE_PORTAL_MSG_200);
    }


    /* *
     * @Description  优惠码主表初始化
     * @Date 11:58 2019/4/3
     * @param
     * @return cn.com.bonc.sce.bean.CouponBean
     */
    private CouponBean initCouponBean(Map param,String userId) throws ParseException {

        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );

        CouponBean couponBean=new CouponBean();

        couponBean.setCOUPON_CODE(param.get("COUPON_CODE").toString());
        couponBean.setCOUPON_TYPE_CODE(Integer.valueOf(param.get("COUPON_TYPE_CODE").toString()));
        couponBean.setUSER_ID(userId);

        int USE_TIMES_FLAG=Integer.valueOf(param.get("USE_TIMES_FLAG").toString());
        couponBean.setUSE_TIMES_FLAG(USE_TIMES_FLAG);
        if (USE_TIMES_FLAG == 1){ //使用次数是否无限：  0 无限      1 有限使用次数
            //（使用次数有限制时）总的使用次数
            couponBean.setTOTAL_USE_TIMES(Integer.valueOf(param.get("TOTAL_USE_TIMES").toString()));
            //（使用次数有限制时）有效的使用次数  （大于0 小于等于总使用次数）    刚申请时 有效使用次数等于总次数
            couponBean.setVALID_USE_TIMES(Integer.valueOf(param.get("TOTAL_USE_TIMES").toString()));
        }


        int VALID_DATE_FALG=Integer.valueOf(param.get("VALID_DATE_FALG").toString());
        couponBean.setVALID_DATE_FALG(VALID_DATE_FALG);
        if (VALID_DATE_FALG==1){ //   优惠码使用时间是否无限： 0 无限      1  使用时间有限制

            // 转换日期时有可能报 ParseException    这里采用向上抛的方式
            Date START_DATE=sdf.parse(param.get("START_DATE").toString());
            Date END_DATE=sdf.parse(param.get("END_DATE").toString());
            couponBean.setSTART_DATE(START_DATE);
            couponBean.setEND_DATE(END_DATE);

        }


        couponBean.setOVER_FLAG(Integer.valueOf(param.get("OVER_FLAG").toString()));
        couponBean.setIS_DELETE(1);
        couponBean.setSTATE('E');
        couponBean.setSUBMIT_DATE(new Date()); // 提交时间
        couponBean.setREVIEW_STATE('T');  // 优惠码刚申请时  为待审批状态      优惠码审核状态： T 待审核      F 审核未通过     E 审核通过


        return couponBean;
    }
}
