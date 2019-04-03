package cn.com.bonc.sce.bean;

import java.util.Date;

/**
 * @ClassName CouponBean
 * @Description 优惠码主表实体类
 * @Author YQ
 * @Date 2019/4/3 10:02
 * @Version 1.0
 */
public class CouponBean {
    String COUPON_CODE;  // 优惠码
    String USER_ID; //  申请人ID
    int USE_TIMES_FLAG; //  使用次数是否无限：  0 无限      1 有限使用次数
    int TOTAL_USE_TIMES; //  （使用次数有限制时）总的使用次数
    int VALID_USE_TIMES; //  （使用次数有限制时）有效的使用次数  （大于0 小于等于总使用次数）
    int VALID_DATE_FALG; //   优惠码使用时间是否无限： 0 无限      1  使用时间有限制
    Date START_DATE; //  （使用时间有限制时）  优惠码开始时间
    Date END_DATE; //   （使用时间有限制时）  优惠码结束时间
    int OVER_FLAG; //  是否可叠加：  0 不可以     1  可以
    int IS_DELETE; //  是否可用： 1表示可用，0表示不可用
    char STATE;  //  是否可用： D 不可用   E 可用
    Date SUBMIT_DATE;  //  优惠码提交时间
    char REVIEW_STATE;  //  优惠码审核状态： T 待审核      F 审核未通过     E 审核通过
    String REVIEW_COMMENT;  //  优惠码审核意见
    String REVIEW_USER_ID;  //  审核人ID
    Date REVIEW_DATE;  //  审核时间

    public CouponBean() {
    }

    public String getCOUPON_CODE() {
        return COUPON_CODE;
    }

    public void setCOUPON_CODE(String COUPON_CODE) {
        this.COUPON_CODE = COUPON_CODE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getUSE_TIMES_FLAG() {
        return USE_TIMES_FLAG;
    }

    public void setUSE_TIMES_FLAG(int USE_TIMES_FLAG) {
        this.USE_TIMES_FLAG = USE_TIMES_FLAG;
    }

    public int getTOTAL_USE_TIMES() {
        return TOTAL_USE_TIMES;
    }

    public void setTOTAL_USE_TIMES(int TOTAL_USE_TIMES) {
        this.TOTAL_USE_TIMES = TOTAL_USE_TIMES;
    }

    public int getVALID_USE_TIMES() {
        return VALID_USE_TIMES;
    }

    public void setVALID_USE_TIMES(int VALID_USE_TIMES) {
        this.VALID_USE_TIMES = VALID_USE_TIMES;
    }

    public int getVALID_DATE_FALG() {
        return VALID_DATE_FALG;
    }

    public void setVALID_DATE_FALG(int VALID_DATE_FALG) {
        this.VALID_DATE_FALG = VALID_DATE_FALG;
    }

    public Date getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(Date START_DATE) {
        this.START_DATE = START_DATE;
    }

    public Date getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(Date END_DATE) {
        this.END_DATE = END_DATE;
    }

    public int getOVER_FLAG() {
        return OVER_FLAG;
    }

    public void setOVER_FLAG(int OVER_FLAG) {
        this.OVER_FLAG = OVER_FLAG;
    }

    public int getIS_DELETE() {
        return IS_DELETE;
    }

    public void setIS_DELETE(int IS_DELETE) {
        this.IS_DELETE = IS_DELETE;
    }

    public char getSTATE() {
        return STATE;
    }

    public void setSTATE(char STATE) {
        this.STATE = STATE;
    }

    public Date getSUBMIT_DATE() {
        return SUBMIT_DATE;
    }

    public void setSUBMIT_DATE(Date SUBMIT_DATE) {
        this.SUBMIT_DATE = SUBMIT_DATE;
    }

    public char getREVIEW_STATE() {
        return REVIEW_STATE;
    }

    public void setREVIEW_STATE(char REVIEW_STATE) {
        this.REVIEW_STATE = REVIEW_STATE;
    }

    public String getREVIEW_COMMENT() {
        return REVIEW_COMMENT;
    }

    public void setREVIEW_COMMENT(String REVIEW_COMMENT) {
        this.REVIEW_COMMENT = REVIEW_COMMENT;
    }

    public String getREVIEW_USER_ID() {
        return REVIEW_USER_ID;
    }

    public void setREVIEW_USER_ID(String REVIEW_USER_ID) {
        this.REVIEW_USER_ID = REVIEW_USER_ID;
    }

    public Date getREVIEW_DATE() {
        return REVIEW_DATE;
    }

    public void setREVIEW_DATE(Date REVIEW_DATE) {
        this.REVIEW_DATE = REVIEW_DATE;
    }
}
