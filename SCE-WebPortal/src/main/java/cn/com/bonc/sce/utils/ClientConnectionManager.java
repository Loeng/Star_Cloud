package cn.com.bonc.sce.utils;


import java.nio.charset.CodingErrorAction;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClientConnectionManager {
	
	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpclient = null;
	private static Logger log = LoggerFactory.getLogger(ClientConnectionManager.class);
	//初始化httpclient
	static {

		try {

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.build();

			connManager = new PoolingHttpClientConnectionManager(
					socketFactoryRegistry);

			httpclient = HttpClients.custom()
					.setConnectionManager(connManager).build();

			// 创建套接字配置项

			SocketConfig socketConfig = SocketConfig.custom()
					.setTcpNoDelay(true)
					.setSoReuseAddress(true)
					.setSoTimeout(30000)
					.build();

			connManager.setDefaultSocketConfig(socketConfig);

			//创建消息约束

			MessageConstraints messageConstraints = MessageConstraints.custom()
					.setMaxHeaderCount(200)
					.setMaxLineLength(2000)
					.build();

			// 创建连接对象

			ConnectionConfig connectionConfig = ConnectionConfig.custom()
					.setMalformedInputAction(CodingErrorAction.IGNORE)
					.setUnmappableInputAction(CodingErrorAction.IGNORE)
					.setCharset(Consts.UTF_8)
					.setMessageConstraints(messageConstraints)
					.build();

			connManager.setDefaultConnectionConfig(connectionConfig);

			connManager.setMaxTotal(200);

			connManager.setDefaultMaxPerRoute(20);

		} catch (Exception e) {
			log.warn(e.getMessage());
		} 

	}

	/**
	 * 
	 * @param content
	 * @param ip
	 * @param port
	 * @param url
	 * @return
	 * @throws Exception 
	 * @throws ClientConnectionManagerException 
	 * @functionality 发送HTTP请求到指定URL
	 */
	public static String sendHttpRequest(String content, String url, Map<String, String> headers) throws Exception {
		CloseableHttpClient client = ClientConnectionManager.httpclient;
		HttpPost post = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(10000)
				.setConnectTimeout(10000)
				.setConnectionRequestTimeout(10000)
				.setExpectContinueEnabled(false)
				.build();
		post.setConfig(requestConfig);
		if(!headers.isEmpty() && headers.size() > 0){
			for(Map.Entry<String, String> map :headers.entrySet()){
				post.addHeader(map.getKey(), map.getValue());
			}
		}
		try {
			if (StringUtils.isNotBlank(content))
				post.setEntity(new StringEntity(content, "UTF-8"));
			
			HttpResponse response = client.execute(post);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String resp = EntityUtils.toString(entity, "UTF-8");
				return resp;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}
