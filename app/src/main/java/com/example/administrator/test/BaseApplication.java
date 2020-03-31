package com.example.administrator.test;

import android.app.Application;

import com.example.administrator.test.data.model.LoginUserBean;

/**
 * Created by qiu on 2019/12/26 20:21.
 */
public class BaseApplication extends Application {
    public static LoginUserBean currUser = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
