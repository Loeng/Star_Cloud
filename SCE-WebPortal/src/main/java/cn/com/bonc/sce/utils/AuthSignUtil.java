package cn.com.bonc.sce.utils;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AuthSignUtil {

	private static Logger log = LoggerFactory.getLogger(AuthSignUtil.class);
	
	public static String partnerPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIQ6NolDw9BLjmaTuZT6StE4TCwql3YjKFOVlIeR/WXjHvt2yXtVcE4IcTfTp/XImt4vE/e85kikUCYipJR6SOe5D4mp/oSVF+uMMDM79XihXMLolpDZ1NhLiqXENODoA96cCSTLZ8CuovfYwc0LuuKqsFKnYmw2LTqCpFW/RfhDAgMBAAECgYBb/5y+OHXfURKM8C9pJhQ0KXVmLWQggCmoriQfh0XFRBbkYyugMjvnLxyG+S6dZavK9vpvnfCjjh9tySC1Zbee7wcyOBceQf4j+cAX6nHfSsJrW48z+gZs1kG+tO0x55tV5ujfeDT48KgvCuwCT7D6bMMS4bBYXr/fQm4D7Us20QJBANc/noe/T4+0Lb/Y9bBwiwfDLq8qSPBTRH2w3gAN2thHWntNBJGaiBZiIoqP8MMnepqQvneTRKcXlMvD1uYC42cCQQCdQtX40TXNAj50qBOjERWjM7ObtvQVgjZpjc1u0N+cQUBw0ugQ0J3sYe/MdDygUd1ehiR98R7fg23OP2W2OPbFAkAGUbPRwGgRi/Sb4T61Q1iRjcVdFxy1EXy9Q+gAkGeQWefqHsS3kZXVMmRZNQiYchxDVWSCFiy5yxNvN/yjHAqZAkB4bDq/yyzArSw4VpCa+TUxgSDTZ7jnU4vpLl4WsrnYxJrdCpQMdEPKRIDFdmkKd0Xl4SDGlEKI8/K80g1ICbzlAkEAkLCtipvLkmXP/472W6rS0Cg/RqLIXGpbbQRnRxCa+ArvzHXAYqrfPOLsj4Tu56UYLRvaopknfiAaawqtI7t+QA==";
	
	/**
	 * 拼装待加密字符串
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String createLinkString(SortedMap<String, String> paramMap) {
		StringBuffer strBuf = new StringBuffer();

		Set<Entry<String, String>> entrySet = paramMap.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		Entry<String, String> entry = null;
		while (iterator.hasNext()) {
			entry = iterator.next();
			if (StringUtils.isBlank(entry.getValue())) {
				continue;
			}
			strBuf.append("&").append(entry.getKey()).append("=").append(entry.getValue());
		}

		if (strBuf.toString().startsWith("&")) {
			return strBuf.toString().substring(1);
		}

		return strBuf.toString();
	}

	public static String md5Sign(SortedMap<String, String> paramMap, String appsecret) {
		String linkStr = createLinkString(paramMap);
		linkStr = linkStr + "&appsecret=" + appsecret;
		log.debug("签名字符串:" + linkStr);
		return DigestUtils.md5Hex(linkStr).toUpperCase();
	}
	
	/**
	 * 生成base64编码签名
	 * 
	 * @param paramMap
	 * @param privateKey
	 * @return
	 */
	public static String rsaBase64Sign(SortedMap<String, String> paramMap, byte[] privateKey) {
		String linkStr = createLinkString(paramMap);
		
		System.out.println(linkStr);
		String encryptStr = null;
		try {
			encryptStr = RsaUtils.generateBase64Sign(linkStr.getBytes(), privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptStr;
	}
	
	/**
	 * 生成base64编码签名
	 * 
	 * @param paramMap
	 * @param privateKey
	 * @return
	 */
	public static String rsaBase64Sign(String linkStr) {
		byte[] privateKey = Base64.decodeBase64(partnerPrivateKey);
		String encryptStr = null;
		try {
			encryptStr = RsaUtils.generateBase64Sign(linkStr.getBytes(), privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptStr;
	}
	
	public static boolean verifySign(String originData, String signatureBase64, String publicKeyBase64)
			throws Exception {
		boolean pass = false;
		pass = RsaUtils.verify(originData.getBytes(), publicKeyBase64, signatureBase64);
		return pass;
	}
	
	public static boolean verifySign(SortedMap<String, String> paramMap, String signatureBase64, String publicKeyBase64)
			throws Exception {
		boolean pass = false;
		String originData = createLinkString(paramMap);
		pass = RsaUtils.verify(originData.getBytes(), publicKeyBase64, signatureBase64);
		return pass;
	}


}
