//package cn.com.bonc.sce.filter;
//
//import cn.hutool.json.JSONUtil;
//
//import javax.websocket.Encoder;
//import javax.websocket.EndpointConfig;
//import java.util.HashMap;
//
//public class SocketMap implements Encoder.Text<HashMap> {
//    @Override
//    public String encode(HashMap map){
//        return JSONUtil.toJsonStr(map);
//    }
//
//    @Override
//    public void init(EndpointConfig endpointConfig) {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
