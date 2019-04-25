package cn.com.bonc.sce.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RsaUtils {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";
	private static Logger logger = LoggerFactory.getLogger(RsaUtils.class);
	public static KeyFactory keyFactory = null;
	public static Exception throwable;
	public static Cipher cipher = null;
	static {
		try {
			java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
			throwable = new Exception("算法不支持", e);
		} catch (NoSuchPaddingException e) {
			logger.error(e.getMessage(), e);
			throwable = new Exception("不支持的数据填充规范", e);
		}
	}

	// private static final String PUBLIC_KEY = "RSAPublicKey";
	// private static final String PRIVATE_KEY = "RSAPrivateKey";

	/*********************** PublicKey ************************************************/
	/**
	 * 使用RSA的publicKey对data加密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static byte[] rsaPublicKeyEncryptHex(String hexData, byte[] keyBytes) throws Exception {
		return rsaPublicKeyEncrypt(HexUtil.hexStringToByteArray(hexData), keyBytes);
	}

	public static byte[] rsaPublicKeyEncrypt(String data, byte[] keyBytes) throws Exception {
		return rsaPublicKeyEncrypt(data.getBytes(), keyBytes);
	}

	/**
	 * 使用RSA的publicKey对data加密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static byte[] rsaPublicKeyEncrypt(byte[] data, byte[] keyBytes) throws Exception {
		return parentPubKey(data, keyBytes);
	}

	public synchronized static byte[] rsaPublicKeyDecrypt(byte[] data, byte[] keyBytes) throws Exception {
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			// Cipher cipher=Cipher.getInstance("RSA/ECB/PKCS1Padding");

			cipher.init(Cipher.DECRYPT_MODE, publicKey);

			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("无效的密钥规范", e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception("不合法的加密或解密数据块", e);
		} catch (BadPaddingException e) {
			throw new Exception("无效的数据填充", e);
		} catch (InvalidKeyException e) {
			throw new Exception("无效的密钥", e);
		} catch (Exception e) {
			logger.info("Data:" + HexUtil.byteArrayToHexString(data));
			logger.info("Key:" + HexUtil.byteArrayToHexString(keyBytes));
			throw new Exception(e.getClass().getName(), e);
		}

	}

	private static byte[] parentPubKey(byte[] data, byte[] keyBytes) throws Exception {
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicKey = keyFactory.generatePublic(x509KeySpec);
			// Key publicKey = generateRSAPublicKey(keyBytes,DataConvert
			// .StringToBytes("010001"));
			// 对数据解密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("无效的密钥规范", e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception("不合法的加密或解密数据块", e);
		} catch (BadPaddingException e) {
			throw new Exception("无效的数据填充", e);
		} catch (InvalidKeyException e) {
			throw new Exception("无效的密钥", e);
		} catch (NoSuchPaddingException e) {
			throw new Exception("不支持的数据填充规范", e);
		}

	}

	/*********************** PrivateKey ************************************************/
	/**
	 * 使用RSA的PrivateKey对data加密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static byte[] rsaPrivateKeyEncryptHex(String hexData, byte[] keyBytes) throws Exception {

		return parentPrivateKey(HexUtil.hexStringToByteArray(hexData), keyBytes, false);
	}

	/**
	 * 使用RSA的PrivateKey对data加密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static byte[] rsaPrivateKeyEncrypt(byte[] data, byte[] keyBytes) throws Exception {

		return parentPrivateKey(data, keyBytes, false);
	}

	/**
	 * 使用RSA的PrivateKey对data解密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static byte[] rsaPrivateKeyDecrypt(String data, byte[] keyBytes) throws Exception {

		return parentPrivateKey(HexUtil.hexStringToByteArray(data), keyBytes, true);
	}

	/**
	 * 使用RSA的PrivateKey对data解密
	 * 
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public static byte[] rsaPrivateKeyDecrypt(byte[] data, byte[] keyBytes) throws Exception {

		return parentPrivateKey(data, keyBytes, true);
	}

	private static byte[] parentPrivateKey(byte[] data, byte[] keyBytes, boolean isDecrypt) throws Exception {

		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		try {
			// KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			if (isDecrypt) {
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
			} else {
				cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			}

			return cipher.doFinal(data);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("无效的密钥规范", e);
		} catch (IllegalBlockSizeException e) {
			throw new Exception("不合法的加密或解密数据块", e);
		} catch (BadPaddingException e) {
			throw new Exception("无效的数据填充", e);
		} catch (InvalidKeyException e) {
			throw new Exception("无效的密钥", e);
		} catch (NoSuchPaddingException e) {
			throw new Exception("不支持的数据填充规范", e);
		}

	}

	public static String generateBase64Sign(byte[] data, byte[] privateKeyBytes) throws Exception {
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

		try {
			// KEY_ALGORITHM 指定的加密算法
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// 取私钥匙对象
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 用私钥对信息生成数字签名
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(priKey);
			signature.update(data);
			return encryptBASE64(signature.sign());
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("无效的密钥规范", e);

		} catch (InvalidKeyException e) {
			throw new Exception("无效的密钥", e);

		} catch (SignatureException e) {
			throw new Exception("数据签名失败", e);
		}

	}

	/***********************
	 * verify and sign
	 ************************************************/
	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 * @throws Exception
	 *
	 * 
	 */
	public static String generateBase64Sign(byte[] data, String privateKeyBase64) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKeyBase64);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

		try {
			// KEY_ALGORITHM 指定的加密算法
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// 取私钥匙对象
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 用私钥对信息生成数字签名
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(priKey);
			signature.update(data);
			return encryptBASE64(signature.sign());
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("无效的密钥规范", e);

		} catch (InvalidKeyException e) {
			throw new Exception("无效的密钥", e);

		} catch (SignatureException e) {
			throw new Exception("数据签名失败", e);
		}

	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		// 解密由base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);
		return verify(data, keyBytes, decryptBASE64(sign));

	}

	public static boolean verify(byte[] data, byte[] publicKey, byte sign[]) throws Exception {

		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);

		try {
			// KEY_ALGORITHM 指定的加密算法
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// 取公钥匙对象
			PublicKey pubKey = keyFactory.generatePublic(keySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(pubKey);
			signature.update(data);
			// 验证签名是否正常
			return signature.verify(sign);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);
		} catch (InvalidKeySpecException e) {
			throw new Exception("无效的密钥规范", e);
		} catch (InvalidKeyException e) {
			throw new Exception("无效的密钥", e);
		} catch (SignatureException e) {
			throw new Exception("数据签名失败", e);
		}

	}

	public static byte[] decryptBASE64(String data) {
		return Base64.decodeBase64(data);
	}

	public static String encryptBASE64(byte[] data) {
		return Base64.encodeBase64String(data);
	}

	/**
	 * 生成公钥
	 * 
	 * @param modulus
	 * @param publicExponent
	 * @return RSAPublicKey
	 * @throws Exception
	 * @throws EncryptException
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
		BigInteger n = new BigInteger(HexUtil.byteArrayToHexString(modulus), 16);
		BigInteger e = new BigInteger(HexUtil.byteArrayToHexString(publicExponent), 16);

		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(n, e);

		try {
			return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(pubKeySpec);
		} catch (InvalidKeySpecException e1) {
			throw new Exception("无效的密钥规范", e1);
		} catch (NoSuchAlgorithmException e1) {
			throw new Exception("算法不支持", e1);
		}
	}

	/**
	 * 生成公钥
	 * 
	 * @param modulus
	 * @param publicExponent
	 * @return RSAPublicKey
	 * @throws EncryptException
	 */
	public static int getRSAPublicKeyBytesLen(byte[] keyBytes) throws Exception {
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);

		try {
			if (keyFactory == null) {
				throw throwable;
			}
			RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
			return publicKey.getModulus().bitLength() / 8;
		} catch (InvalidKeySpecException e1) {
			throw new Exception("无效的密钥规范", e1);
		}

	}

	/**
	 * 生成私钥
	 * 
	 * @param modulus
	 * @param privateExponent
	 * @return RSAPrivateKey
	 * @throws Exception
	 * @throws NoSuchAlgorithmException
	 * @throws EncryptException
	 */
	public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {

		try {
			KeyFactory keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
			RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus),
					new BigInteger(privateExponent));
			return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
		} catch (InvalidKeySpecException e1) {
			throw new Exception("无效的密钥规范", e1);
		} catch (NoSuchAlgorithmException e1) {
			throw new Exception("算法不支持", e1);
		}

	}

	public static KeyPair generateKeyPair() throws Exception {

		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA",
					new org.bouncycastle.jce.provider.BouncyCastleProvider());
			final int KEY_SIZE = 1024;
			keyPairGen.initialize(KEY_SIZE, new SecureRandom());
			KeyPair keyPair = keyPairGen.genKeyPair();
			return keyPair;
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);
		}

	}

	public static byte[] unpadV15(byte[] padded) throws Exception {
		int k = 0;
		if (padded[k++] != 0) {
			throw new Exception("Data must start with zero");
		}
		if (padded[k++] != 2) {
			throw new Exception("Blocktype mismatch: " + padded[1]);
		}
		while (true) {
			int b = padded[k++] & 0xff;
			if (b == 0) {
				break;
			}
			if (k == padded.length) {
				throw new Exception("Padding string not terminated");
			}

		}
		int n = padded.length - k;
		int maxDataSize = 128 - 11;
		if (n > maxDataSize) {
			throw new Exception("Padding string too short");
		}
		byte[] data = new byte[n];
		System.arraycopy(padded, padded.length - n, data, 0, n);
		return data;
	}

	public static byte[] decrypt_(Key key, byte[] raw) throws Exception {

		try {
			Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.DECRYPT_MODE, key);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;

			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("算法不支持", e);

		} catch (IllegalBlockSizeException e) {
			throw new Exception("不合法的加密或解密数据块", e);
		} catch (BadPaddingException e) {
			throw new Exception("无效的数据填充", e);
		} catch (InvalidKeyException e) {
			throw new Exception("无效的密钥", e);
		} catch (NoSuchPaddingException e) {
			throw new Exception("字节流数据处理失败", e);
		} catch (IOException e) {
			throw new Exception("不支持的数据填充规范", e);
		}

	}

	public static byte[] signByHash(byte[] privateKey, byte[] hash) throws Exception {
		return rsaPrivateKeyEncrypt(privateKey,
				ArrayUtils.addAll(HexUtil.hexStringToByteArray("3021300906052B0E03021A05000414"), hash));
	}

	public static boolean checkHexString(String s) {
		if (!s.matches("[0-9a-fA-F]+")) {
			return false;
		}
		return (s.length() % 2) == 0;
	}

	/**
	 * 组成公钥 传入16进制参数
	 * 
	 * @param hexModulus
	 * @param hexPublicExponent
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey getPublicKey(String hexModulus, String hexPublicExponent)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		BigInteger bigIntModulus = new BigInteger(hexModulus, 16);
		BigInteger bigIntPrivateExponent = new BigInteger(hexPublicExponent, 16);
		RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	public static PublicKey getPublicKey(byte[] keyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		return publicKey;
	}

	public static void main(String args[]) throws Exception {
		// String hexModulus =
		// "A10929AE007807E22F748C4F616FA0742033DB9DD95B07852021CAA6596DF7A5B1C2E1E5E08ED060819293750BD209A97F254BF0A4E98C43B81D7F046CEE47E83EB461FE44F1F5FFC1C585794EEF7B8142B160789F3E869C93539E0354D01B6E05AE0CD14FD05AB70C06857C4216E2E7FCCEAD138070EC5C5EE10F18C448FF7D";
		// String hexExponent = "00010001";
		// //
		// RSAPublicKey k = RsaUtils.generateRSAPublicKey(
		// HexUtil.hexStringToByteArray(hexModulus),
		// HexUtil.hexStringToByteArray(hexExponent));
		//
		// String pkkk = HexUtil.byteArrayToHexString(k.getEncoded());
		// System.out.println(pkkk);
		// String sss =
		// "30819F300D06092A864886F70D010101050003818D0030818902818100C41A9C7EBADBE9991E9CFFFB4C3169F2653B9A54040C002AF8629D89BC7401C069AE907A3F7C52B2F56897C1574C48857D5A7C0D3534307910FC55BCC016E7DC98C964CA84DF994E7B31CA9A72E508C841E2FC12A3D5522C93BFDA6BD3A6BC495317458C8840214BD9547F986C30679758EE9CC3CD8340B01B68A4A5E22FBB910203010001";
		// System.out.println(pkkk.equals(sss));

		//
		// PublicKey pppk = RsaUtils.getPublicKey(k.getEncoded());
		// System.out.println(HexUtil.byteArrayToHexString(pppk.getEncoded()));
		//
		// BigInteger n = new BigInteger(hexModulus, 16);
		// BigInteger e = new BigInteger(hexExponent, 16);
		//
		// RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(n, e);
		//
		// RSAPublicKey rsapbk = (RSAPublicKey) KeyFactory.getInstance("RSA")
		// .generatePublic(pubKeySpec);
		// System.out.println(HexUtil.byteArrayToHexString(rsapbk.getEncoded()));
		//
		// PublicKey pk = getPublicKey(hexModulus, hexExponent);
		// System.out.println(HexUtil.byteArrayToHexString(pk.getEncoded()));

		// byte[] data = HexUtil
		// .hexStringToByteArray("795BCEBFA476E2DEE5644733D381DFE202C92CFA426503063BB0EB56D4FB79AF50803084D97DC69AB3561C70BD2EA878366D3A62E66CDBBBF49572F958A33D53BE6680ACFB2ED8C9C8840AFEFD0B90868E5F9E0EEDE7CBB4D49A4FB66C7A3CC2858C5E04EF40612CE1420935C67D6290A7538F7F3B1F4DBA311513F3B2D88A2A");
		try {
			// byte[] pkb= rsapbk.getEncoded();
			// byte[] pkb = HexUtil
			// .hexStringToByteArray("30819F300D06092A864886F70D010101050003818D0030818902818100C41A9C7EBADBE9991E9CFFFB4C3169F2653B9A54040C002AF8629D89BC7401C069AE907A3F7C52B2F56897C1574C48857D5A7C0D3534307910FC55BCC016E7DC98C964CA84DF994E7B31CA9A72E508C841E2FC12A3D5522C93BFDA6BD3A6BC495317458C8840214BD9547F986C30679758EE9CC3CD8340B01B68A4A5E22FBB910203010001");
			// String origin = "58385A524D445A36584749524A383658";
			//
			// System.out.println(RsaUtils.verify(
			// HexUtil.hexStringToByteArray(origin), pkb, data));
			//
			// origin = "XR42B2ZESJILCVKANJDQZ0PLMGTZT6PZ";

			// String s =
			// "3082025D02010002818100DDBC8B497BDBDFF0E1B012654282748732A864CB4DEBC0C069779D42DD836C5948937BB2D867D8FE5A82B1239FA31964630B5EEC1F1AF7559D58957408824DE9DE83442525E48EB3F50945AA52A02E82643BDE5C2FFE36E9DAA69846EC0B103345A2F7008AA9355CF11C14E0B90545F59E649173E75521FE77B3A6FF89D64F6D02030100010281810083B1D3D8552406217F29A0035C3937148555D6679964081B3F88B7725B360F4807FEF05D0C82C132707766D60D9E451A8668DE7BC70DB110BC5F3C84A9F91A644D8DAE05F04E7FB4F0F7063ABA8203C1F78F32571B23FE6A9070085C95B95CE4B5737977A34A554DEEF5CF208B924F50A2CD4EEA8DDD9691D476F720E8E73481024100F5566FD0F18AF8921BF1163A9ACB09B4ABA9D013D3F55F4ADBBFB83DFD7D34D6AC49E3E9B55F93B9D03F4BBDAF95DF928FA159EA55C7D4642919B5E4D2A11AB9024100E75F86EF2AEDCDC6D1AD0A18228ED5B25018580F7FB6B31019964A1A77217A74F887257BEDB6E8D9B051E4CC0BC1FCB08BD104656AAB149FC90EEF4A3A23F05502407349DC1FB6F2145F2CAC3D42EAECD0B1CB1D707F70D254A66BDF8B27EE50011D8436F0D82978318656FAF78FBACC56149C543959AA57CE52071262EC95A2495902403866E915D47933F88B948B2967033AC4C511AA25DD144482603A6AC618D4686DAB4806B33737A77BA9A7CD0690B7314A384B33AF25599F257475D78F58ACFA210241008EDB3F11419E700DCA4D8D31EDDFDBB4F66C6905D5EB4950BB2F4A01711C3C243C54F842F05CD34661AB6F12769CFF453D95D49DC16E81AC31D5BA5CF7D4B6B2";
			// String text = "202f";
			// PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(
			// HexUtil.hexStringToByteArray(s));
			// //
			// // // KEY_ALGORITHM 指定的加密算法
			// KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// // // 取私钥匙对象
			// PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// // // 用私钥对信息生成数字签名
			// Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			// signature.initSign(priKey);
			// signature.update(text.getBytes());
			// System.out.println(encryptBASE64(signature.sign()));
			// System.out.println(RsaUtils.RSAPrivateKeyEncrypt(text,
			// HexUtil.hexStringToByteArray(s)));
			String publicKey = "3081A0300D06092A864886F70D010101050003818E0030818A0281800100C181809E6109FDCEC37BC773AFFB4152B1944AD3A6B207FC37171E916A96E10528D4A942830759F82749EBF506C7F81B30D9756633F0D44BC41A0C4BCD9577EAEE3DD118FCA773B1FD5895DEA2B55E55FF3BC7D69591D49E8CC6E934BAAC949A61971E88886DFD14434ED726543A6AC46E58A119D11FDB1ED32AEB72DAFA020500EC4BC975";
			// publicKey="30819F300D06092A864886F70D010101050003818D0030818902818100C5874B76A679A90D0EDA8FE35226E686E323581A0CDA5219CF50B74553EBF445DC31CC1782F5912B0FECFC1CEE689D7642F238843CF9081B57667471E216873047DDE2BE1BD037BECAE96F9A3FEEFF186389D7F353831BA237E3629E7BB5157EC8BD8E8864E4611A2E26A6D318A43183EDC38EFF1D3A41BBB85D771DD24031750203010001";
			// PKCS8EncodedKeySpec keySpec = new
			// PKCS8EncodedKeySpec(HexUtil.hexStringToByteArray(publicKey));
			// KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
			// RSAPrivateKey privateKey = (RSAPrivateKey)
			// keyFactory.generatePrivate(keySpec);
			// String privateKeyExponent =
			// privateKey.getPrivateExponent().toString();
			// String privateKeyModulus = privateKey.getModulus().toString();
			// System.out.println("私钥指数:" + privateKeyExponent);
			// System.out.println("私钥模:" + privateKeyModulus);
			String data = "88810000035E10ADC3949BA59ABBE56E057F20F883E3bff77115120";
			// 3081A0300D06092A864886F70D010101050003818E0030818A0281800100C181809E6109FDCEC37BC773AFFB4152B1944AD3A6B207FC37171E916A96E10528D4A942830759F82749EBF506C7F81B30D9756633F0D44BC41A0C4BCD9577EAEE3DD118FCA773B1FD5895DEA2B55E55FF3BC7D69591D49E8CC6E934BAAC949A61971E88886DFD14434ED726543A6AC46E58A119D11FDB1ED32AEB72DAFA020500EC4BC975
			String sign = "836dedde0af9b8139254eda68e9f8e7bc17c07916602f18f5403624c49aa335c2925a39446a5365b7759ef31b18b05dc9c756c609d83f1b4baf985c200d3dcaeb7287597d7132835eb345597074da167f89f7da2a9f3e44bbfe8c12e6ad9290551e106a0ad8ba4a915a9b267a3f8773e14560c6923dd6aea8a9feae5bbe434cf";

			boolean verify = verify(data.getBytes(), HexUtil.hexStringToByteArray(publicKey),
					HexUtil.hexStringToByteArray(sign));
			System.out.println(verify);
			// ec4bc975
			// 0100c181809e6109fdcec37bc773affb4152b1944ad3a6b207fc37171e916a96e10528d4a942830759f82749ebf506c7f81b30d9756633f0d44bc41a0c4bcd9577eaee3dd118fca773b1fd5895dea2b55e55ff3bc7d69591d49e8cc6e934baac949a61971e88886dfd14434ed726543a6ac46e58a119d11fdb1ed32aeb72dafa
			RSAPublicKey k = RsaUtils.generateRSAPublicKey(
					HexUtil.hexStringToByteArray(
							"0100c181809e6109fdcec37bc773affb4152b1944ad3a6b207fc37171e916a96e10528d4a942830759f82749ebf506c7f81b30d9756633f0d44bc41a0c4bcd9577eaee3dd118fca773b1fd5895dea2b55e55ff3bc7d69591d49e8cc6e934baac949a61971e88886dfd14434ed726543a6ac46e58a119d11fdb1ed32aeb72dafa"),
					HexUtil.hexStringToByteArray("ec4bc975"));
			System.out.println(HexUtil.byteArrayToHexString(k.getEncoded()));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static PublicKey getPublicKeyByBase64(String base64Str) throws Exception {
		byte[] keyBytes = decryptBASE64(base64Str);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		return (RSAPublicKey) keyFactory.generatePublic(keySpec);
	}
}
