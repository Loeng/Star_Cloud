package cn.com.bonc.sce.tool;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: Vloader
 * @Auther: 管理员
 * @Date: 2018/12/24 19:18
 * @Description:
 */

public class SendMessage {

    public static String postMsgToPhone( String msg, String phone ) throws UnsupportedEncodingException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout( 1000 );
        requestFactory.setReadTimeout( 1000 );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_FORM_URLENCODED );
        RestTemplate restTemplate = new RestTemplate( requestFactory );
        HttpEntity request = new HttpEntity<>( getXmlInfo( msg, phone ), headers );
        restTemplate.postForEntity( "http://userinterface.vcomcn.com/Opration.aspx", request, String.class );

        return "SUCCESS";
    }

    private static String getXmlInfo( String msg, String phone ) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        Date now = new Date();
        DateFormat bf = new SimpleDateFormat( "yyyy-MM-dd  HH:mm:ss" );
        String sendtime = bf.format( now );
        sb.append(
                "<Group Login_Name=\"fjxydsj\" Login_Pwd=\"A811F841A5C62BD7D3025BB28C6A1A3D\" OpKind=\"0\" InterFaceID=\"0\" SerType=\"Smit\">" );
        sb.append( "<E_Time>" + sendtime + "</E_Time>" );
        sb.append( "<Item>" );
        sb.append( "<Task>" );
        sb.append( "<Recive_Phone_Number>" + phone + "</Recive_Phone_Number>" );
        sb.append( "<Content><![CDATA[" +  msg + "]]></Content>" );
        sb.append( "<Search_ID>test</Search_ID>" );
        sb.append( "</Task>" );
        sb.append( "</Item>" );
        sb.append( "</Group>" );
        System.out.println( sb.toString() );
        return new String(sb.toString().getBytes(),"gbk" );
    }

    public static void main( String[] args ) throws UnsupportedEncodingException {

        String str = "验证码:6462";

        System.out.println( postMsgToPhone(new String(str.getBytes(),"gbk"), "18227587920" ) );

    }


}




