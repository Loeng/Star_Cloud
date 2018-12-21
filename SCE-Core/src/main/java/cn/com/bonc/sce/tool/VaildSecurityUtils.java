package cn.com.bonc.sce.tool;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 安全验证相关
 *
 * @author wzm
 * @version 0.1
 * @since 2018/12/21 12:00
 */
public abstract class VaildSecurityUtils {

    private static volatile Map< String, Long > validCacheMap = new HashMap< String, Long >();
    private static volatile Map< String, Long > codeCacheMap = new HashMap< String, Long >();
    private static volatile Map< String, Long > phoneMinTimeCacheMap = new HashMap< String, Long >();

    private static final Long ONE_MINUTE = 1L * 60 * 1000;
    private static final Long THIRTY_MINUTE = 30L * 60 * 1000;
    private static final int CACHE_CLEAR_INTERVAL = 5000;

    private static final int CODE_LENGTH = 8;

    /**
     * 清除指定map中数据
     *
     * @param ses           计时器
     * @param timeMap       清除map
     * @param clearInterval 清除间隔
     * @param saveMaxTime   最大存储时间
     */
    private static void runTime( ScheduledExecutorService ses, final Map< String, Long > timeMap, int clearInterval, final long saveMaxTime ) {
        ses.scheduleAtFixedRate( new Runnable(){
            @Override
            public void run() {
                clearCacheMap( timeMap );
            }

            private void clearCacheMap( Map< String, Long > map ) {
                for ( Iterator< Map.Entry< String, Long > > it = map.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry< String, Long > entry = it.next();
                    if ( System.currentTimeMillis() - entry.getValue() < saveMaxTime ) {
                        it.remove();
                    }
                }
            }
        }, 0, clearInterval, TimeUnit.SECONDS);
    }

    /**
     * 定时清除
     */
    static {
        ScheduledExecutorService ses = new ScheduledThreadPoolExecutor(1);
        runTime( ses, validCacheMap, CACHE_CLEAR_INTERVAL, THIRTY_MINUTE );
        runTime( ses, codeCacheMap, CACHE_CLEAR_INTERVAL, THIRTY_MINUTE );
        runTime( ses, phoneMinTimeCacheMap, CACHE_CLEAR_INTERVAL, ONE_MINUTE );
    }

    public static void addValid( String str ) {
        validCacheMap.put( str, System.currentTimeMillis() );
    }

    public static void addCode( String str ) {
        validCacheMap.put( str, System.currentTimeMillis() );
    }

    public static void addPhoneMinTime( String str ) {
        phoneMinTimeCacheMap.put( str, System.currentTimeMillis() );
    }

    public static void delValid( String str ) {
        validCacheMap.remove( str );
    }

    public static void delCode( String str ) {
        validCacheMap.remove( str );
    }

    public static void delPhoneMinTime( String str ) {
        phoneMinTimeCacheMap.remove( str );
    }

    /**
     * 检查验证码
     *
     * @param str 手机号和验证码的md5加密
     * @return 验证结果
     */
    public static boolean checkValid( String str ) {
        Long time = validCacheMap.get( str );
        if ( time != null && System.currentTimeMillis() - time < THIRTY_MINUTE ){
            return true;
        }
        return false;
    }

    /**
     * 检查安全码
     *
     * @param str 手机号和安全码的md5加密
     * @return 验证结果
     */
    public static boolean checkCode( String str ) {
        Long time = codeCacheMap.get( str );
        if ( time != null && System.currentTimeMillis() - time < THIRTY_MINUTE )
            return true;
        return false;
    }

    /**
     * 检查手机短信间隔时长
     *
     * @param str 手机号和安全码的md5加密
     * @return 验证结果
     */
    public static boolean checkPhoneMinTime( String str ) {
        Long time = phoneMinTimeCacheMap.get( str );
        if ( time == null || System.currentTimeMillis() - time > ONE_MINUTE )
            return true;
        return false;
    }

    public static String randomStr() {
        char[] str = new char[ CODE_LENGTH ];
        int i = 0;
        //数字的个数
        int num = 3;
        while ( i < CODE_LENGTH ) {
            int f = ( int ) ( Math.random() * num );
            if ( f == 0 ) {
                str[ i ] = ( char ) ( 'A' + Math.random() * 26 );
            } else if ( f == 1 ) {
                str[ i ] = ( char ) ( 'a' + Math.random() * 26 );
            } else {
                str[ i ] = ( char ) ( '0' + Math.random() * 10 );
            }
            i++;
        }
        String random_str = new String( str );
        return random_str;
    }

}
