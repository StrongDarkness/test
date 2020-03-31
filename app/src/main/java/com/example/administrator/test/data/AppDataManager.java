package com.example.administrator.test.data;

import android.util.Log;

import com.example.administrator.test.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qiu on 2019/12/26 18:33.
 */
public class AppDataManager {
    private static String TAG = "AppDataManager";
    private static AppDataManager manager;
    private Retrofit retrofit;
    private OkHttpClient client;
    private HttpLoggingInterceptor interceptor;

    private AppDataManager() {
        //打印请求日志
        interceptor = new HttpLoggingInterceptor(s -> Log.d(TAG, s));
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        //初始化okhttp
        client = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true).build();
        //初始化retrofit
        initRetrofit(AppConstants.domain);
    }

    /**
     * 初始化retrofit
     *
     * @param baseUrl
     */
    private void initRetrofit(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client).build();

    }

    public static AppDataManager getInstance() {
        if (manager == null) {
            manager = new AppDataManager();
        }
        return manager;
    }

    /**
     * 创建请求
     */
    public Apis create() {
        initRetrofit(AppConstants.domain);
        return retrofit.create(Apis.class);
    }

    /**
     * 创建请求，可切换地址
     */
    public Apis create(String baseUrl) {
        initRetrofit(baseUrl);
        return retrofit.create(Apis.class);
    }

}
