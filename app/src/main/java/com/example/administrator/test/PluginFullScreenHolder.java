package com.example.administrator.test;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by qiu on 2019/1/3.
 */
public class PluginFullScreenHolder extends Dialog {
    private final WebView mWebView;
    private final int mNpp;
    private View mContentView;

    PluginFullScreenHolder(@NonNull WebView webView, int npp) {
        super(webView.getContext(), android.R.style.Theme_NoTitleBar_Fullscreen);
        mWebView = webView;
        mNpp = npp;
    }

    @Override
    public void setContentView(@NonNull View view) {
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        if (view instanceof SurfaceView) {
            final SurfaceView sview = (SurfaceView) view;
        }
        super.setContentView(view);
        mContentView = view;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (event.isSystem()) {
            return super.onKeyDown(keyCode, event);
        }
        mWebView.onKeyDown(keyCode, event);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if (event.isSystem()) {
            return super.onKeyUp(keyCode, event);
        }
        mWebView.onKeyUp(keyCode, event);
        return true;
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        return true;
    }

    @Override
    public boolean onTrackballEvent(@NonNull MotionEvent event) {
        mWebView.onTrackballEvent(event);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mContentView != null && mContentView.getParent() != null) {
            ViewGroup vg = (ViewGroup) mContentView.getParent();
            vg.removeView(mContentView);
        }
    }
}
