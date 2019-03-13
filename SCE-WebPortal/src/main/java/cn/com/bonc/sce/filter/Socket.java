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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint(value = "/ws/myWebSocket/{ticket}")
public class Socket {

    private Session session;

    //用户id与socket连接绑定
    private static Map<String, ArrayList<Socket>> allUser = new HashMap();

    //本机服务连接数
    private static int totalSocket = 0;

    /**
     * 新建连接
     * @param session 连接session
     * @param ticket 用户连接凭证
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("ticket") String ticket){
        String payloadsStr = Base64.decodeStr( ticket.split( "\\." )[ 1 ] );
        Object userId = JSONUtil.toBean( payloadsStr, Map.class ).get( "userId" );

        //找到该用户
        ArrayList<Socket> socketList = null;
        for(String user : allUser.keySet()){
            if(user.equals(userId)){
                socketList = allUser.get(user);
                break;
            }
        }
        if(socketList == null){
            socketList = new ArrayList<>();
        }
        this.session = session;
        socketList.add(this);
        totalSocket++;
        allUser.put(userId.toString(), socketList);
        log.info("有新的连接，用户ID：" + userId.toString());
        log.info("webSocket有新的连接，总数：" + totalSocket);
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(){
        for(ArrayList<Socket> socketList : allUser.values()){
            for(Socket socket : socketList){
                if(this.session.getId().equals(socket.session.getId())){
                    socketList.remove(this);
                    break;
                }
            }
        }
        log.info("webSocket连接断开，总数：" + totalSocket);
    }

    /**
     * 客户端发送消息过来
     * @param message 消息内容
     */
    @OnMessage
    public void onMessage(String message){
        log.info("webSocket收到客户端发来的消息：" + message);
    }

    /**
     * 消息发送错误时调用
     * @param session 当前session
     * @param error error
     */
    @OnError
    public void onError(Session session, Throwable error){
        log.warn("消息发送错误：" + error.getMessage() + session.getId());
        error.printStackTrace();
    }

    /**
     * 群发消息
     * @param message 消息内容
     */
    public void sendMessage(String message){
        log.info("广播消息：" + message);
        for (String user : allUser.keySet()){
            for(Socket socket : allUser.get(user)){
                try {
                    socket.session.getBasicRemote().sendText(message);
                }catch (Exception e){
                    e.printStackTrace();
                    log.info("广播消息发送失败，连接不到该用户");
                }
            }
        }
    }

    /**
     * 单独发消息
     * @param userId 用户id
     * @param message 消息内容
     * @return 消息发送状态
     * @throws IOException
     */
    public boolean sendMessage(String userId, String message) throws IOException {
        if(userId == null || message == null){
            return false;
        }

        boolean sendSuccess = false;
        for(String user : allUser.keySet()){
            if(user.equals(userId)){
                for(Socket socket : allUser.get(user)){
                    socket.session.getBasicRemote().sendText(message);
                }
                sendSuccess = true;
                log.info("消息发送完毕");
                break;
            }
        }
        return sendSuccess;
    }

    /**
     * 需要注册该bean
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
