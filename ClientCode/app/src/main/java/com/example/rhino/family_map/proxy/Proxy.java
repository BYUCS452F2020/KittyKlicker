package com.example.rhino.family_map.proxy;

import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import model.client.KittyClient;
import model.request.LoginRequest;
import model.request.RegisterRequest;
import model.response.Login_RegisterResponse;

public class Proxy {

    private static Gson gson = new Gson();

    public static Login_RegisterResponse Login(LoginRequest request)
    {
        try
        {
            URL server = new URL("http://" + KittyClient.getClient().getHost() + ":" + KittyClient.getClient().getPort() + "/user/login");
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStreamWriter ow = new OutputStreamWriter(connection.getOutputStream());
            gson.toJson(request, ow);
            ow.flush();
            ow.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                // Get response body
                InputStreamReader responseBody = new InputStreamReader(connection.getInputStream());

                // Read response body
                Login_RegisterResponse response = gson.fromJson(responseBody, Login_RegisterResponse.class);
                return response;
            }
        }
        catch (Exception e)
        {
            Log.e("Login", e.getMessage(), e);
            return new Login_RegisterResponse(e.getMessage());
        }

        return null;
    }

    public static Login_RegisterResponse Register(RegisterRequest request)
    {
        try
        {
            URL server = new URL("http://" + KittyClient.getClient().getHost() + ":" + KittyClient.getClient().getPort() + "/user/register");
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStreamWriter ow = new OutputStreamWriter(connection.getOutputStream());
            gson.toJson(request, ow);
            ow.flush();
            ow.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                // Get response body
                InputStreamReader responseBody = new InputStreamReader(connection.getInputStream());

                // Read response body
                Login_RegisterResponse response = gson.fromJson(responseBody, Login_RegisterResponse.class);
                return response;
            }
        }
        catch (Exception e)
        {
            Log.e("Register", e.getMessage(), e);
            return new Login_RegisterResponse(e.getMessage());
        }

        return null;
    }
}
