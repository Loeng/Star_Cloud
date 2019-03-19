//package cn.com.bonc.sce.filter;
//
//import cn.hutool.json.JSONUtil;
//
//import javax.websocket.Encoder;
//import javax.websocket.EndpointConfig;
//import java.util.ArrayList;
//
//public class SocketList implements Encoder.Text<ArrayList> {
//    @Override
//    public String encode(ArrayList list){
//        return JSONUtil.toJsonStr(list);
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
