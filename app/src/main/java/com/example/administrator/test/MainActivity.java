package com.example.administrator.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.net.URI;
import java.net.URISyntaxException;

//import io.crossbar.autobahn.websocket.WebSocketConnection;
//import io.crossbar.autobahn.websocket.WebSocketConnectionHandler;
//import io.crossbar.autobahn.websocket.exceptions.WebSocketException;
//import io.crossbar.autobahn.websocket.interfaces.IWebSocketConnectionHandler;
//import io.crossbar.autobahn.websocket.types.ConnectionResponse;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_msg;
    private EditText et_text;
    private Button btn_connect, btn_close, btn_send;
    private static final String TAG = "MainActivity";
    private String domain = "ws://192.168.101.110:8081/websocket/android";
//    private WebSocketConnection connection = new WebSocketConnection();
    private StringBuffer text=new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initChat();
    }

    private void initChat() {

//        try {
//            connection.connect(domain, new IWebSocketConnectionHandler() {
//                @Override
//                public void onConnect(ConnectionResponse response) {
//                    Log.i(TAG, "onConnect: ");
//                }
//
//                @Override
//                public void onOpen() {
//                    Log.i(TAG, "onOpen: ");
//                }
//
//                @Override
//                public void onClose(int code, String reason) {
//                    Log.i(TAG, "onClose: " + code + reason);
//                }
//
//                @Override
//                public void onMessage(String payload) {
//                    Log.i(TAG, "onMessage: " + payload);
//                    text.append(payload+"\n");
//                    tv_msg.setText(text.toString());
//                }
//
//                @Override
//                public void onMessage(byte[] payload, boolean isBinary) {
//
//                }
//
//                @Override
//                public void onPing() {
//
//                }
//
//                @Override
//                public void onPing(byte[] payload) {
//
//                }
//
//                @Override
//                public void onPong() {
//
//                }
//
//                @Override
//                public void onPong(byte[] payload) {
//
//                }
//
//                @Override
//                public void setConnection(WebSocketConnection connection) {
//                    Log.i(TAG, "setConnection: ");
//                }
//            });
//        } catch (WebSocketException e) {
//            e.printStackTrace();
//        }


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
//                if (connection.isConnected())
//                    connection.sendClose();
                break;
            case R.id.btn_connect:
                Log.i(TAG, "onClick: connect");
//                connection.reconnect();
                break;
            case R.id.btn_send:
                Log.i(TAG, "onClick: send");
                String msg = et_text.getText().toString();
//                connection.sendMessage("all@"+msg);
                tv_msg.setText(text.toString());
                break;
        }
    }


}
