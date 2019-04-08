package cn.com.bonc.sce.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

@Slf4j
public class UserPropertiesUtil {
                                          // 6位地区码   // 年YYYY           // 月MM             // 日DD    // 3位顺序码    // 校验码
    private static final String REGEX = "^\\d{6}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    // 加权因子
    private static final int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
    // 根据同余定理得到的校验码数组
    private static final String[] CHECK_NUMBER_ARRAY = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
    //手机号校验规则
    private static final String PHONE_REGEX = "^1[35678]{1}\\d{9}$";
    //邮箱验证规则
    private static final String MAIL_REGEX = "^[a-zA-Z0-9-_]+@[a-zA-Z0-9-_]+(\\.[a-zA-Z0-9-_]+)+$";
    //日期格式之身份证
    private static final SimpleDateFormat SDF = new SimpleDateFormat( "yyyyMMdd" );
    //excel的起始日期
    private static final Calendar CALENDAR = new GregorianCalendar( 1900, 0, -1 );

    /**
     * 检查身份证是否合法
     * @param certificateNumber 身份证18位
     * @return 是否合法
     */
    public static boolean checkCertificateNumber( String certificateNumber ){
        if(certificateNumber.length() != 18){
            return false;
        }
        return Pattern.matches( REGEX, certificateNumber )
                && UserPropertiesUtil.computeCheckNumber( certificateNumber ).equals( certificateNumber.substring( 17 ) );
    }

    /**
     * 验证用户手机号是否合法
     * @param phone 手机号
     * @return 是否合法
     */
    public static boolean checkPhone( String phone ){
        return Pattern.matches( PHONE_REGEX, phone );
    }

    /**
     * 验证用户邮箱是否合法
     * @param mail 邮箱
     * @return 是否合法
     */
    public static boolean checkMail( String mail ){
        return Pattern.matches( MAIL_REGEX, mail );
    }

    /**
     * 通过身份证获取用户生日
     * @param certificationNumber 身份证
     * @return 生日-date类型
     */
    public static Date getBirthDateByCer( String certificationNumber ){
        try {
            Date date = SDF.parse(certificationNumber.substring( 6, 14 ));
            return date;
        } catch ( ParseException e ){
            log.info( "通过身份证获取生日出错" );
        }
        return null;
    }

    public static Date getDateByExcelDate( String excelDate ){
        Date date = null;
        try{
            date = DateUtils.addDays( CALENDAR.getTime(),Integer.parseInt( excelDate ) );
        } catch ( NumberFormatException e ){
            log.info( "日期转换出错" );
        }
        return date;
    }

    /**
     * 验证身份证的最后一位校验位是否正确
     * @param masterNumber 身份证前17位
     * @return 校验位
     */
    private static String computeCheckNumber( String masterNumber ) {
        char[] masterNumberArray = masterNumber.toCharArray();
        int sum = 0;
        for ( int i = 0; i < W.length; i++ ) {
            sum += Integer.parseInt( String.valueOf( masterNumberArray[i] ) ) * W[ i ];
        }
        String checkNumber = CHECK_NUMBER_ARRAY[ sum % 11 ];
        return checkNumber;
    }

    public static void main( String[] args ) throws Exception {

        System.out.println( getBirthDateByCer( "511011199502227610" ) );

//        System.out.println(getDateByExcelDate(41883));
//
//        System.out.println(sdf.parse("20140924"));

//        String mail = "wangfei1@bonc.com.cn";
//        System.out.println(UserPropertiesUtil.checkMail(mail));

//        String phone = "12955555555";
//        System.out.println(UserPropertiesUtil.checkPhone(phone));

//        System.out.println(UserPropertiesUtil.computeCheckNumber("513026545XXXX565665"));

    }
}
