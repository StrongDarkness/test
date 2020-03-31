package com.example.administrator.test.data.model;

import android.content.Intent;

/**
 * Created by qiu on 2019/12/26 18:23.
 */
public class LoginUserBean {
    private int code;
    private String result;
    private String message;
    private String userId;
    private String username;
    private String token;

    public void LoggedInUser() {
    }

    public LoginUserBean(int code, String result, String message, String userId, String username, String token) {
        this.code = code;
        this.result = result;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.token = token;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
