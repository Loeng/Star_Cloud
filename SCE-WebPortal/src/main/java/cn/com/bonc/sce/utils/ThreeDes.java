package cn.com.bonc.sce.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ThreeDes {

	// key 根据实际情况对应的修改
	// keybyte为加密密钥，长度为24字节
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
														// DES,DESede,Blowfish

	// 加密
	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			SecretKey deskey = new SecretKeySpec(key, Algorithm);
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			return cipher.doFinal(data);
		} catch (Exception ex) {
			// 加密失败，打日志
			ex.printStackTrace();
		}
		return null;
	}

	// 解密
	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			SecretKey deskey = new SecretKeySpec(key, Algorithm);
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			return cipher.doFinal(data);
		} catch (Exception ex) {
			// 解密失败，打日志
			ex.printStackTrace();
		}
		return null;
	}

}