package com.example.rhino.family_map.proxy;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;

import model.LoginRequest;
import model.Login_RegisterResponse;
import model.RegisterRequest;
import model.client.Client;
import model.server.EventsResponse;
import model.server.PersonsResponse;
import model.server.Response;

public class Proxy {

    private static Gson gson = new Gson();

    public static Login_RegisterResponse Login(LoginRequest request)
    {
        try
        {
            URL server = new URL("http://" + Client.getClient().getHost() + ":" + Client.getClient().getPort() + "/user/login");
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
            URL server = new URL("http://" + Client.getClient().getHost() + ":" + Client.getClient().getPort() + "/user/register");
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

    public static PersonsResponse GetPerson(String auth)
    {
        try
        {
            URL server = new URL("http://" + Client.getClient().getHost() + ":" + Client.getClient().getPort() + "/person");
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", auth);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStreamReader responseBody = new InputStreamReader(connection.getInputStream());

                // Read response body
                PersonsResponse response = gson.fromJson(responseBody, PersonsResponse.class);
                return response;
            }
        }
        catch (Exception e)
        {
            Log.e("GetPerson", e.getMessage(), e);
        }
        return null;
    }

    public static EventsResponse GetEvents(String auth)
    {
        try
        {
            URL server = new URL("http://" + Client.getClient().getHost() + ":" + Client.getClient().getPort() + "/event");
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", auth);
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                InputStreamReader responseBody = new InputStreamReader(connection.getInputStream());

                // Read response body
                EventsResponse response = gson.fromJson(responseBody, EventsResponse.class);
                return response;
            }
        }
        catch (Exception e)
        {
            Log.e("GetEvents", e.getMessage(), e);
        }
        return null;
    }
}
