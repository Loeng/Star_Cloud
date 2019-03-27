package cn.com.bonc.sce.tool;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *  author wf
 *  by 2019/03/25
 */
public class MD5Util {

    public static String getMd5String(String str){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        }catch (NoSuchAlgorithmException e){
            return null;
        }
        md5.update(str.getBytes());
        return new BigInteger( 1, md5.digest() ).toString( 16 );
    }


}
