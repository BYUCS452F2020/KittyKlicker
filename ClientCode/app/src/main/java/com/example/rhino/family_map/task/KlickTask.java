package com.example.rhino.family_map.task;

import android.os.AsyncTask;

import com.example.rhino.family_map.proxy.Proxy;

import model.request.KlickRequest;
import model.request.LoginRequest;
import model.response.KlickResponse;
import model.response.Login_RegisterResponse;

/**
 * Created by jswense2 on 11/17/18.
 */

public class KlickTask extends AsyncTask<KlickRequest, Integer, KlickResponse> {

    public interface KlickListener
    {
        public void onKlickComplete(KlickResponse response);
    }

    private KlickListener listener;

    public KlickTask(KlickListener listener)
    {
        this.listener = listener;
    }

    protected KlickResponse doInBackground(KlickRequest... request)
    {
//        return Proxy.Login(request[0]);
//        System.out.println("klick task");
        return Proxy.Klick(request[0]);
    }

    protected void onPostExecute(KlickResponse response)
    {
//        System.out.println("post execute");
        listener.onKlickComplete(response);
    }
}
