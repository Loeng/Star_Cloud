package cn.com.bonc.sce.service;

import cn.com.bonc.sce.bean.CouponBean;
import cn.com.bonc.sce.constants.WebMessageConstants;
import cn.com.bonc.sce.dao.CouponDao;
import cn.com.bonc.sce.rest.RestRecord;
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


        // 根据优惠方式   插入 优惠码  todo 待完善
        int COUPON_TYPE_CODE=Integer.valueOf(param.get("COUPON_TYPE_CODE").toString());
        if (COUPON_TYPE_CODE==0){
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
     * @Description  优惠码主表初始化
     * @Date 11:58 2019/4/3
     * @param
     * @return cn.com.bonc.sce.bean.CouponBean
     */
    private CouponBean initCouponBean(Map param,String userId) throws ParseException {

        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );

        CouponBean couponBean=new CouponBean();

        couponBean.setCOUPON_CODE(param.get("COUPON_CODE").toString());
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
