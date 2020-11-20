package com.example.rhino.family_map.proxy;

import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import model.client.KittyClient;
import model.request.KlickRequest;
import model.request.LoginRequest;
import model.request.RegisterRequest;
import model.response.KlickResponse;
import model.response.Login_RegisterResponse;

public class Proxy {

    private static Gson gson = new Gson();

//    public static Login_RegisterResponse Login(LoginRequest request) {
//        Login_RegisterResponse response = new Login_RegisterResponse("AUTH", request.getUserName(), "TEAM", 0, 1, new ArrayList<String>());
//        return response;
//    }
//
//    public static Login_RegisterResponse Register(RegisterRequest request) {
//        Login_RegisterResponse response = new Login_RegisterResponse("AUTH", request.getUserName(), request.getTeamName(), 0, 1, new ArrayList<String>());
//        return response;
//    }

//    public static KlickResponse Klick(KlickRequest request) {
//        KlickResponse response = new KlickResponse(1);
//        return response;
//    }

    public static Login_RegisterResponse Login(LoginRequest request) {
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
            Log.e("Klick", e.getMessage(), e);
            return new Login_RegisterResponse(e.getMessage());
        }

        return null;
    }

    public static Login_RegisterResponse Register(RegisterRequest request) {
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
            Log.e("Klick", e.getMessage(), e);
            return new Login_RegisterResponse(e.getMessage());
        }

        return null;
    }

    public static KlickResponse Klick(KlickRequest request)
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
                KlickResponse response = gson.fromJson(responseBody, KlickResponse.class);
                return response;
            }
        }
        catch (Exception e)
        {
            Log.e("Klick", e.getMessage(), e);
            return new KlickResponse(0);
        }

        return null;
    }

//    private String authToken;
//    private String userID;
//    private String teamName;
//    private int kittiesKlicked;
//    private int kittyPower;
//    private List<String> powerups;

//    public static Login_RegisterResponse Login(LoginRequest request)
//    {
//        try
//        {
//            URL server = new URL("http://" + KittyClient.getClient().getHost() + ":" + KittyClient.getClient().getPort() + "/user/login");
//            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//
//            OutputStreamWriter ow = new OutputStreamWriter(connection.getOutputStream());
//            gson.toJson(request, ow);
//            ow.flush();
//            ow.close();
//
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
//            {
//                // Get response body
//                InputStreamReader responseBody = new InputStreamReader(connection.getInputStream());
//
//                // Read response body
//                Login_RegisterResponse response = gson.fromJson(responseBody, Login_RegisterResponse.class);
//                return response;
//            }
//        }
//        catch (Exception e)
//        {
//            Log.e("Login", e.getMessage(), e);
//            return new Login_RegisterResponse(e.getMessage());
//        }
//
//        return null;
//    }
//
//    public static Login_RegisterResponse Register(RegisterRequest request)
//    {
//        try
//        {
//            URL server = new URL("http://" + KittyClient.getClient().getHost() + ":" + KittyClient.getClient().getPort() + "/user/register");
//            HttpURLConnection connection = (HttpURLConnection) server.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoOutput(true);
//
//            OutputStreamWriter ow = new OutputStreamWriter(connection.getOutputStream());
//            gson.toJson(request, ow);
//            ow.flush();
//            ow.close();
//
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
//            {
//                // Get response body
//                InputStreamReader responseBody = new InputStreamReader(connection.getInputStream());
//
//                // Read response body
//                Login_RegisterResponse response = gson.fromJson(responseBody, Login_RegisterResponse.class);
//                return response;
//            }
//        }
//        catch (Exception e)
//        {
//            Log.e("Register", e.getMessage(), e);
//            return new Login_RegisterResponse(e.getMessage());
//        }
//
//        return null;
//    }
}
