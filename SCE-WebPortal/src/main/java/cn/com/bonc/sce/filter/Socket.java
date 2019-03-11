package cn.com.bonc.sce.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint(value = "/ws/myWebSocket/{ticket}")
public class Socket {

    private Session session;

    private static Map<String, String> allUser = new HashMap();

    private static CopyOnWriteArraySet<Socket> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("ticket") String ticket){
        String payloadsStr = Base64.decodeStr( ticket.split( "\\." )[ 1 ] );
        Object userId = JSONUtil.toBean( payloadsStr, Map.class ).get( "userId" );
        allUser.put(session.getId(), userId.toString());
        this.session = session;
        copyOnWriteArraySet.add(this);
        log.info("有新的连接，连接id：" + session.getId());
        log.info("websocket有新的连接，总数：" + copyOnWriteArraySet.size());
    }

    @OnClose
    public void onClose(){
        copyOnWriteArraySet.remove(this);
        allUser.remove(this.session.getId());
        log.info("websocket连接断开，总数：" + copyOnWriteArraySet.size());
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("websocket收到客户端发来的消息：" + message);
    }

    @OnError
    public void onError(Session session, Throwable error){
        log.warn("发送错误：" + error.getMessage() + session.getId());
        error.printStackTrace();
    }


    public void sendMessage(String message){
        for(Socket myWebSocket : copyOnWriteArraySet){
            log.info("websocket广播消息：" + message);
            try {
                myWebSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public String sendMessage(String userId, String message) throws IOException {
        if(userId == null || message == null){
            return "参数不能为空";
        }
        Session session = null;
        Socket tempWebSocket = null;
        String sessionId = null;
        for (String oneUser : allUser.keySet()){
            if(allUser.get(oneUser).equals(userId)){
                sessionId = oneUser;
                break;
            }
        }
        if(sessionId == null){
            return "该用户不在连接中";
        }
        for (Socket webSocket : copyOnWriteArraySet) {
            if (webSocket.session.getId().equals(sessionId)) {
                tempWebSocket = webSocket;
                session = webSocket.session;
                break;
            }
        }
        if (session == null) {
            System.out.println("没有找到你指定ID的会话：{}" + userId);
        }
        tempWebSocket.session.getBasicRemote().sendText(message);
        return "消息发送成功";
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
