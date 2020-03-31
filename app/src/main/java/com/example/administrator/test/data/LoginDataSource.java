package com.example.administrator.test.data;

import com.example.administrator.test.AppConstants;
import com.example.administrator.test.data.model.LoggedInUser;
import com.example.administrator.test.data.model.LoginUserBean;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

                return new Result.Success<>(new LoggedInUser("","",""));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
