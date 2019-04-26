package cn.com.bonc.sce.utils;

import java.io.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import sun.misc.Resource;


public class Run {

	private static final String url = "http://183.238.0.244:18110/api";//测试环境地址
	private static final String appSecret = "KxCfHIREQxYWORGdw4LcrS5e";// 
	// private key of yourself 测试私钥
	public static final String partnerPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJRqhgI+Sf4HYkfvrGU086gOcm7Uo9HpqhoX4F6fbyPoFlOIBOLyh69LFYqGi52UMwbS7cIXW0DAjzzgnLlVFG6s+ueuZ7GJgf5pmtCwpBA+uj3xvrDsVEN/W3fnd66RW8rce7Ysb03LoD/6EDzF3r+zRlZb11wja1sbNGenafDFAgMBAAECgYBlbguZuuIslvVG/niOTkr7Hqx8LBytWAjlfk3bvRBitLmVas8UHvY2CZV2L495KRBaaIsO65F0xmTeEQ9q5cdo9VigzNXIwi06msUVB3NlYuLlRc0+QO6t2WzzB2XbH3VRL2xFs2NXyAfjYES801tlGpCCYsDK+vdb5y+zzWgyvQJBAMyB4DzWwvIzIoJzeMnz95cq7Uk+Vups7Is2PGmSwz131aOj4lK/gYbHsdrLkhw7SEqhxYtvH2pJ6UCjUBIuKosCQQC5yR9H9smzxDtfQl5IUiMUDa02zsJURdmOfMKF37zkQrKORGQAgG/PT3TRYUAR+ijNFjvfKgAmUMV6FpOOPMvvAkEAxahpgKPZu5msqwTMI/UGwjadLQNxohYw8cQz054j0g82j3jgUDYEDK6JufyzdVHe5zM/6So35NUW+oBd7ZRtTQJAZJxRT2SLeMnFKWJEsr+gdYSukCbKQF8PYUWZBmzS235vOfI5gbgMVOV/qsAStWmW3PG0fLc/G4DUYLiHMhkkiwJAf1gW1HduJE2MC69iKTtQUyiW2wc+i8GdaYzLZIhFqn/WTxRQsVRRwNKQHUWOAXvw3ar67XZDA9ZF6y8579Za5Q==";
	
	/**
	 * 身份证号码3des加密
	 */
	public String idcardnoEncrypt(String idcard) {
		/**
			采用3DES加密。
			3DES加密的密钥使用应用授权码appsecret。
			应用授权码appsecret是长度为24的字符串。
			加密模式采用ECB，补位模式为：PKCS5。
			加密后密文为16进制字符串。
		**/
		String idcardOf3des = HexUtil.byteArrayToHexString(
				ThreeDes.encrypt(idcard.getBytes(),appSecret.getBytes())
				);
		return idcardOf3des;
	}
	/**
	 * 参数签名
	 */
	public Map<String, String> paramSign(SortedMap<String, String> signParamMap) {
		/**
		签名生成的通用步骤如下：
			第一步：设所有发送或者接收到的数据为集合M(公共签名参数及参数xml中root>body节点下数据)，将集合M内非空参数值的参数按照参数名ASCII码从小到大排序（字典序），使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串stringA。
				特别注意以下重要规则：
					 参数名ASCII码从小到大排序（字典序）；
					 如果参数的值为空不参与签名；
					 参数名区分大小写；
					 验证调用返回或平台主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。
					 平台接口可能增加字段，验证签名时必须支持增加的扩展字段
			第二步： 在stringA最后拼接上appsecret得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。
		 **/
//测试
		signParamMap.put("app_id", "app100000000042");
		signParamMap.put("msg_version", "1.0.0");
		signParamMap.put("msg_name", "core/auth");
		signParamMap.put("partner_id", "p100000000053");
		signParamMap.put("transaction_id", "10000000000000000000000000");

		String sign = AuthSignUtil.rsaBase64Sign(signParamMap, Base64.decodeBase64(partnerPrivateKey));
		signParamMap.put("sign", sign);
		return signParamMap;
	}





	private String readFileInputStream(InputStream is)
	{
		StringBuilder builder = new StringBuilder();

		try {

			InputStreamReader reader = new InputStreamReader( is , "UTF-8" );
			BufferedReader bfReader = new BufferedReader( reader );

			String tmpContent = null;

			while ( ( tmpContent = bfReader.readLine() ) != null ) {
				builder.append( tmpContent );
			}

			bfReader.close();

		} catch ( Exception e ) {
			// 忽略
		}

		return this.filter( builder.toString() );
	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter ( String input ) {

		return input.replaceAll( "/\\*[\\s\\S]*?\\*/", "" );

	}


	public JSONObject idCardValt(String name, String idcardno) throws IOException {
		String respContent = null, reqContent = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("xml/auth00.xml");
		reqContent = readFileInputStream(inputStream );


		try {
			String authmode = "00";
			
			SortedMap<String, String> signParamMap = new TreeMap<>();
			
			signParamMap.put("name", name);
			signParamMap.put("auth_mode", authmode);
			signParamMap.put("id_card_no", idcardnoEncrypt(idcardno));
			
			reqContent = MessageFormat.format(reqContent, name, idcardnoEncrypt(idcardno)); 
			System.out.println("请求报文:"+reqContent);
			respContent = ClientConnectionManager.sendHttpRequest(reqContent,
					url, paramSign(signParamMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("响应为：" + respContent);
		System.out.println(XmlToJsonUtils.xmlToJson(respContent));
		return XmlToJsonUtils.xmlToJson(respContent);
	}


	
}
