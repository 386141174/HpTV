package com.hp.pojo;


import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint(value = "/websocket/{username}")
@Component
public class MyWebsocket {

//    private Logger logger = LoggerFactory.getLogger(this.getClass());


    //存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebsocket> webSocketSet = new CopyOnWriteArraySet<MyWebsocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //用户名称
    private String name;


    /**
     * 建立连接
     * @param name 用户名
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username")String name, Session session){
        this.session = session;
        this.name = name;
        webSocketSet.add(this);
        System.out.println("有新连接"+name+"加入！当前在线人数为" + webSocketSet.size());
        this.session.getAsyncRemote().sendText("您成功连接上WebSocket-->当前在线人数为："+webSocketSet.size());

    }


    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());

    }


    /**
     * 发送消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){

        System.out.println("来自客户端的消息:" + message);
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable error){

        System.out.println("发生错误");
        error.printStackTrace();

    }

    private void broadcast(String message) {

        for (MyWebsocket item : webSocketSet){

            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }

    }


}
