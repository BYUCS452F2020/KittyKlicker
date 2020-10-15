package com.example.rhino.family_map.task;

import android.os.AsyncTask;

import com.example.rhino.family_map.proxy.Proxy;

import model.LoginRequest;
import model.Login_RegisterResponse;

/**
 * Created by jswense2 on 11/17/18.
 */

public class LoginTask extends AsyncTask<LoginRequest, Integer, Login_RegisterResponse> {

    public interface LoginListener
    {
        public void onLoginComplete(Login_RegisterResponse response);
    }

    private LoginListener listener;

    public LoginTask(LoginListener listener) {
        this.listener = listener;
    }

    protected Login_RegisterResponse doInBackground(LoginRequest... request)
    {
        return Proxy.Login(request[0]);
    }

    protected void onPostExecute(Login_RegisterResponse response)
    {
        listener.onLoginComplete(response);
    }
}
