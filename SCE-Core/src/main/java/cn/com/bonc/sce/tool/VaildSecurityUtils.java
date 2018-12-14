package cn.com.bonc.sce.tool;

import java.util.*;

public abstract class VaildSecurityUtils {

    //验证码信息存储，String为手机号和安全码的MD5加密
    private static volatile Map< String, Long > vaildCacheMap = new HashMap< String, Long >();
    //安全码信息存储，String为手机号和安全码的MD5加密
    private static volatile Map< String, Long > codeCacheMap = new HashMap< String, Long >();
    //手机短信时间存储
    private static volatile Map< String, Long > phoneMinTimeCacheMap = new HashMap< String, Long >();

    //1分钟的时长
    private static final Long oneMinute = 1L * 60 * 1000;
    //30分钟的时长
    private static final Long thirtyMinute = 30L * 60 * 1000;
    //缓存清空间隔
    private static final int cacheClearInterval = 5000;

    private static final int codeLength = 8;

    /**
     * 清除指定map中数据
     * @param timer 计时器
     * @param timeMap 清除map
     * @param clearInterval 清除间隔
     * @param saveMaxTime 最大存储时间
     */
    private static void runTime( Timer timer, final Map< String, Long > timeMap, int clearInterval, final long saveMaxTime){
        timer.schedule( new TimerTask() {
            @Override
            public void run() {
                clearCacheMap( timeMap );
            }

            private void clearCacheMap( Map< String, Long > map ) {
                for ( Iterator< Map.Entry< String, Long > > it = map.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry< String, Long > entry = it.next();
                    if ( System.currentTimeMillis() - entry.getValue() < saveMaxTime ) it.remove();
                }
            }
        }, 0, clearInterval );
    }

    /**
     * 定时清除
     */
    static {
        Timer timer = new Timer();
        runTime(timer,vaildCacheMap,cacheClearInterval,thirtyMinute);
        runTime(timer,codeCacheMap,cacheClearInterval,thirtyMinute);
        runTime(timer,phoneMinTimeCacheMap,cacheClearInterval,oneMinute);
    }

    public static void addVaild(String str){
        vaildCacheMap.put( str, System.currentTimeMillis() );
    }

    public static void addCode(String str){
        vaildCacheMap.put( str, System.currentTimeMillis() );
    }

    public static void addPhoneMinTime(String str){
        phoneMinTimeCacheMap.put( str, System.currentTimeMillis() );
    }

    public static void delVaild(String str){
        vaildCacheMap.remove( str );
    }

    public static void delCode(String str){
        vaildCacheMap.remove( str );
    }

    public static void delPhoneMinTime(String str){
        phoneMinTimeCacheMap.remove( str );
    }

    /**
     * 检查验证码
     * @param str 手机号和验证码的md5加密
     * @return 验证结果
     */
    public static boolean checkVaild(String str){
        Long time = vaildCacheMap.get( str );
        if ( time != null && System.currentTimeMillis() - time < thirtyMinute )
            return true;
        return false;
    }

    /**
     * 检查安全码
     * @param str 手机号和安全码的md5加密
     * @return 验证结果
     */
    public static boolean checkCode(String str){
        Long time = codeCacheMap.get( str );
        if ( time != null && System.currentTimeMillis() - time < thirtyMinute )
            return true;
        return false;
    }

    /**
     * 检查手机短信间隔时长
     * @param str 手机号和安全码的md5加密
     * @return 验证结果
     */
    public static boolean checkPhoneMinTime(String str){
        Long time = phoneMinTimeCacheMap.get( str );
        if ( time == null || System.currentTimeMillis() - time > oneMinute )
            return true;
        return false;
    }

    public static String randomStr() {
        char[] str= new char[codeLength];
        int i = 0;
        int num=3;//数字的个数
        while (i < codeLength) {
            int f = (int) (Math.random() * num);
            if (f == 0)
                str[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                str[i] = (char) ('a' + Math.random() * 26);
            else
                str[i] = (char) ('0' + Math.random() * 10);
            i++;
        }
        String random_str = new String(str);
        return random_str;
    }

}
