package cn.com.bonc.sce.utils;

/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/22 14:53
 * @Description:  获取UUID作为主键
 */
public class UUID {

    public static String getUUID() {

        return java.util.UUID.randomUUID().toString().replace( "-", "" ).toLowerCase();
    }

}
