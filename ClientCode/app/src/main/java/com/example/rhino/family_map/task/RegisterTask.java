package com.example.rhino.family_map.task;

import android.os.AsyncTask;

import com.example.rhino.family_map.proxy.Proxy;

import model.request.RegisterRequest;
import model.response.Login_RegisterResponse;

public class RegisterTask extends AsyncTask<RegisterRequest, Integer, Login_RegisterResponse> {

    public interface RegisterListener
    {
        public void onRegisterComplete(Login_RegisterResponse response);
    }

    private RegisterListener listener;

    public RegisterTask(RegisterListener listener) {
        this.listener = listener;
    }

    protected Login_RegisterResponse doInBackground(RegisterRequest... request)
    {
        return Proxy.Register(request[0]);
    }

    protected void onPostExecute(Login_RegisterResponse response)
    {
        listener.onRegisterComplete(response);
    }
}
