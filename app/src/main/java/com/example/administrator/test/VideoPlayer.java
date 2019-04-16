package com.example.administrator.test;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.List;

/**
 * Created by qiu on 2019/1/3.
 */
@TargetApi(Build.VERSION_CODES.FROYO)
public class VideoPlayer {
    public WebView webView;
    Context context;
    String htmlPre = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"></head><body style='margin:0; pading:0; background-color: black;'>";

    String htmlCode = " <embed style='width:100%; height:100%' src='http://www.platipus.nl/flvplayer/download/1.0/FLVPlayer.swf?fullscreen=true&video=@VIDEO@' "
            + "  autoplay='true' "
            + "  quality='high' bgcolor='#000000' "
            + "  name='VideoPlayer' align='middle'"
            + // width='640' height='480'
            "  allowScriptAccess='*' allowFullScreen='false'"
            + "  type='application/x-shockwave-flash' "
            + "  pluginspage='http://www.macromedia.com/go/getflashplayer' />"
            + "";
    String swfhtmlCode = " <embed style='width:100%; height:100%' src='@VIDEO@' "
            + "  autoplay='true' "
            + "  quality='high' bgcolor='#000000' "
            + "  name='VideoPlayer' align='middle'"
            + // width='640' height='480'
            "  allowScriptAccess='*' allowFullScreen='true'"
            + "  type='application/x-shockwave-flash' "
            + "  pluginspage='http://www.macromedia.com/go/getflashplayer' />"
            + "";

    String htmlPost = "</body></html>";
    String phtml="<embed src='http://www.3464.com/tools/FLVLyplayer/Lyplayer.swf?path=http://fms.cntv.lxdns.com/live/flv/channel84.flv&type=flv&fullscreen=true&autoplay=true&backcolor=99ff33&frontcolor=ffffff' type='application/x-shockwave-flash' width='550' height='400' quality='high' allowFullscreen='true' />";

    public VideoPlayer(WebView webView,Context context){
        this.webView=webView;
        this.context=context;
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
//        webView.getSettings().setPluginsEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setDisplayZoomControls(true);
        WebSettings settings=webView.getSettings();
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setJavaScriptEnabled(true);
    }

    public void stopPlayer(){
        webView.destroyDrawingCache();
        webView.clearView();
        webView.clearCache(true);
        webView.clearHistory();
        webView.loadDataWithBaseURL("fake://fake/fake","about:blank","text/html", "UTF-8", null);
        webView.destroyDrawingCache();
    }
    /***
     * 检查android设备有不有flash
     *
     * @return
     */
    private boolean check() {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> infoList = pm
                .getInstalledPackages(PackageManager.GET_SERVICES);
        for (PackageInfo info : infoList) {
            if ("com.adobe.flashplayer".equals(info.packageName)) {
                return true;
            }
        }
        return false;
    }
    /***
     *
     * 播放视频
     *
     * @param playerUrl
     * @param typeid
     */
    public void startPlayer(String playerUrl, int typeid) {
        stopPlayer();

//        if (check() == true) {
            Log.e("-------我有---------", playerUrl + "----------------");
            if (typeid == 1) {
                htmlCode = swfhtmlCode.replaceAll("@VIDEO@", playerUrl);
                webView.loadDataWithBaseURL("fake://fake/fake", htmlPre
                        + htmlCode + htmlPost, "text/html", "UTF-8", null);
            } else if (typeid == 3) {
                htmlCode = htmlCode.replaceAll("@VIDEO@", playerUrl);
//                webView.loadDataWithBaseURL("fake://fake/fake", htmlPre
//                        + htmlCode + htmlPost, "text/html", "UTF-8", null);
                webView.loadDataWithBaseURL("fake://fake/fake", htmlPre+phtml+htmlPost, "text/html", "UTF-8", null);
            }
//        } else {
//            Log.e("-------我没有有---------", "----------------");
//            install();
//        }
    }
    /***
     *
     * 安装信息
     *
     */
    @SuppressLint("JavascriptInterface")
    private void install() {
//        webView.addJavascriptInterface(new AndroidBridge(), "android");
//        webView.loadUrl("file:///android_asset/installplugs.html");
    }

    private class AndroidBridge {
        public void goMarket() {
            new Handler().post(new Runnable() {
                public void run() {
                    Intent installIntent = new Intent(
                            "android.intent.action.VIEW");
                    installIntent.setData(Uri
                            .parse("market://details?id=com.adobe.flashplayer"));
                    context.startActivity(installIntent);
                }
            });
        }
    }
}
