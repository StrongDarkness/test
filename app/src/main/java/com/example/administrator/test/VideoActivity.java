package com.example.administrator.test;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by qiu on 2019/1/3.
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class VideoActivity extends AppCompatActivity {
    private WebView webView;
    private VideoPlayer videoPlayer;
    private String flvurl = "http://fms.cntv.lxdns.com/live/flv/channel84.flv";
    private String swfurl = "	http://59.175.233.118:9091/doc_book_res/edu_ylsource/doc/2014/03/20/12/23a6485d6f0f40989617c685c27055ad.swf";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.video_player);
        webView=findViewById(R.id.webview);
        FullscreenableChromeClient chromeClient=new FullscreenableChromeClient(this);
        webView.setWebChromeClient(chromeClient);
//        webView.setWebChromeClient(new WebChromeClient());
        VideoPlayer videoPlayer=new VideoPlayer(webView,this);
        //1表示swf，3表示flv
        videoPlayer.startPlayer(flvurl,3);
//        webView.loadUrl("file:///android_asset/videodemo.html");
    }

    @Override
    protected void onPause() {
        super.onPause();
        callHiddenWebViewMethod("onPause");
        webView.pauseTimers();
        if (isFinishing()){
            webView.loadUrl("about:blank");
            setContentView(new FrameLayout(this));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        callHiddenWebViewMethod("onResume");
        webView.resumeTimers();
    }

    private void callHiddenWebViewMethod(String name) {
        // credits:
        // http://stackoverflow.com/questions/3431351/how-do-i-pause-flash-content-in-an-android-webview-when-my-activity-isnt-visible
        if (webView != null) {
            try {
                Method method = WebView.class.getMethod(name);
                method.invoke(webView);
            } catch (NoSuchMethodException e) {
                // Log.e("No such method: " + name + e+"");
            } catch (IllegalAccessException e) {
                // Lo.g("Illegal Access: " + name + e);
            } catch (InvocationTargetException e) {
                // Lo.g("Invocation Target Exception: " + name + e);
            }
        }
    }
}
