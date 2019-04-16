package com.example.administrator.test;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;

import java.util.List;
import java.util.Map;

/**
 * Created by qiu on 2018/12/26.
 */
public class WSManager {
    private static WSManager manager = null;
    private final String TAG = this.getClass().getSimpleName();
    private String url = "ws://192.168.101.110:8081/websocket/android";
    private WebSocket ws;
    private WebSocketListener wsListener;

    private WSManager() {
    }

    public static WSManager getInstance() {
        if (manager == null) {
            synchronized (WSManager.class) {
                if (manager == null) {
                    manager = new WSManager();
                }
            }
        }
        return manager;
    }

    public void init() {
        try {
            if (wsListener==null){
                wsListener=new WSListener();
            }
            ws = new WebSocketFactory().createSocket(url, 5000).setFrameQueueSize(5)
                    .setMissingCloseFrameAllowed(false).addListener(wsListener)
                    .connectAsynchronously();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setWsListener(WebSocketListener wsListener){
    this.wsListener=wsListener;
    }
     class WSListener extends WebSocketAdapter {
        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            Log.e(TAG, "onConnected: 连接成功");
        }

        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.e(TAG, "onTextMessage: 接收消息："+text );
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
            super.onConnectError(websocket, exception);
            Log.e(TAG, "onConnectError: 连接错误",exception );
        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Log.e(TAG, "onDisconnected: 断开连接" );
        }

        @Override
        public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {
            super.onSendError(websocket, cause, frame);
            Log.e(TAG, "onSendError: 发送信息失败", cause);
        }
    }

    public void sendText(String text){
        if (ws != null) {
            ws.sendText(text);
        }
    }
    public void disconnect(){
        if (ws!=null){
            ws.disconnect();
        }
    }
}
