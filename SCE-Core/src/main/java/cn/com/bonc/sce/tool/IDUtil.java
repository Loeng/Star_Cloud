package cn.com.bonc.sce.tool;

/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/26 22:04
 * @Description:
 */
public class IDUtil {

    private static byte[] lock = new byte[0];

    private final static long w = 100000000;

    public static String createID(StringBuilder pre) {
        long r;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }
        return   pre.append( String.valueOf(r).substring(1) ).toString();
    }
}
