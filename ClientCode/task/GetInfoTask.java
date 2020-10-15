package com.example.rhino.family_map.task;

import android.os.AsyncTask;
import android.util.Pair;

import com.example.rhino.family_map.proxy.Proxy;

import model.server.EventsResponse;
import model.server.PersonsResponse;

/**
 * Created by jswense2 on 11/17/18.
 */

public class GetInfoTask extends AsyncTask<String, Integer, Pair<PersonsResponse, EventsResponse>> {
    public interface GetInfoListener
    {
        public void onGetInfoComplete(Pair<PersonsResponse, EventsResponse> response);
    }

    private GetInfoListener listener;

    public GetInfoTask(GetInfoListener listener) {
        this.listener = listener;
    }

    protected Pair<PersonsResponse, EventsResponse> doInBackground(String... auth)
    {
        return new Pair<>(Proxy.GetPerson(auth[0]), Proxy.GetEvents(auth[0]));
    }

    protected void onPostExecute(Pair<PersonsResponse, EventsResponse> response)
    {
        listener.onGetInfoComplete(response);
    }
}
