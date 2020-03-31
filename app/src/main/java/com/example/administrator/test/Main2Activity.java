package com.example.administrator.test;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private LinearLayout ll_content;
    private EditText et_text;
    private Button btn_connect, btn_close, btn_send;
    private static final String TAG = "Main2Activity";
    private String domain = AppConstants.ws + "/websocket/android";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initChat();
    }

    private void initChat() {
        if (BaseApplication.currUser != null) {
            domain = AppConstants.ws + "/websocket/" + BaseApplication.currUser.getUserId();
        }
//        WSManager.getInstance().setWsListener(new WsListener());
        WSManager.getInstance().init(domain, new WsListener());
    }

    private class WsListener extends WebSocketAdapter {
        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
            Toast.makeText(Main2Activity.this, "连接成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onConnected: 连接成功");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_msg.setText("连接成功");
                }
            });

        }

        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
            Log.e(TAG, "onTextMessage: 接收消息：" + text);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textView = new TextView(Main2Activity.this);
                    textView.setText(text);
                    ll_content.addView(textView);
                }
            });
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
            super.onConnectError(websocket, exception);
            Log.e(TAG, "onConnectError: 连接错误", exception);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_msg.setText("连接错误");
                }
            });

        }

        @Override
        public void onDisconnected(WebSocket websocket, WebSocketFrame serverCloseFrame, WebSocketFrame clientCloseFrame, boolean closedByServer) throws Exception {
            super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
            Log.e(TAG, "onDisconnected: 断开连接");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_msg.setText("断开连接");
                }
            });
        }

        @Override
        public void onSendError(WebSocket websocket, WebSocketException cause, WebSocketFrame frame) throws Exception {
            super.onSendError(websocket, cause, frame);
            Log.e(TAG, "onSendError: 发送信息失败", cause);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_msg.setText("发送信息失败");
                }
            });

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
        ll_content = findViewById(R.id.ll_content);
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
                WSManager.getInstance().init(domain, new WsListener());
                break;
            case R.id.btn_send:
                Log.i(TAG, "onClick: send");
                String msg = et_text.getText().toString();
//                WSManager.getInstance().sendText(""+msg);
                WSManager.getInstance().sendText("all@" + msg);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WSManager.getInstance().disconnect();
    }
}
