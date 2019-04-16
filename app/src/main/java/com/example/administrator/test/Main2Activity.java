package com.example.administrator.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;

import java.util.List;
import java.util.Map;

/**
 * Created by qiu on 2018/12/26.
 */
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_msg;
    private EditText et_text;
    private Button btn_connect, btn_close, btn_send;
    private static final String TAG = "Main2Activity";
    private String domain = "ws://192.168.101.110:8081/websocket/android";
    private StringBuffer msg=new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initChat();
    }

    private void initChat() {
    WSManager.getInstance().init();
    WSManager.getInstance().setWsListener(new WsListener());
    }
    private class WsListener extends WebSocketAdapter{
        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            Log.e(TAG, "onConnected: 连接成功");
        }

        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.e(TAG, "onTextMessage: 接收消息："+text );
            msg.append(text+"\n");
            tv_msg.setText(msg.toString());
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
    private void initView() {
        tv_msg = findViewById(R.id.tv_msg);
        et_text = findViewById(R.id.et_text);
        btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);
        btn_connect = findViewById(R.id.btn_connect);
        btn_connect.setOnClickListener(this);
        btn_send = findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                Log.i(TAG, "onClick: close");
                WSManager.getInstance().disconnect();
                break;
            case R.id.btn_connect:
                Log.i(TAG, "onClick: connect");
                WSManager.getInstance().init();
                break;
            case R.id.btn_send:
                Log.i(TAG, "onClick: send");
                String msg = et_text.getText().toString();
                WSManager.getInstance().sendText(""+msg);
//                WSManager.getInstance().sendText("all@"+msg);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WSManager.getInstance().disconnect();
    }
}
