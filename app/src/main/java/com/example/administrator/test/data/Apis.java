package com.example.administrator.test.data;

import com.example.administrator.test.data.model.LoginUserBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by qiu on 2019/12/26 18:21.
 */
public interface Apis {

    @POST("/api/login")
    @FormUrlEncoded
    Call<LoginUserBean> Login(@Field("username") String username, @Field("password") String password);
}
