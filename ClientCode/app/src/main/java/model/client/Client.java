package model.client;

import java.net.MalformedURLException;
import java.net.URL;

import model.LoginRequest;
import model.RegisterRequest;

/**
 * Created by jswense2 on 11/17/18.
 */

public class Client {

    private static Client client;

    // authorization
    private String auth;
    private String personID;
    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;

    private String host = "";
    private String port = "";
    private URL server;

    public static Client getClient() {
        if (client == null)
            client = new Client();
        return client;
    }

    private Client() {
        try {
            server = new URL("");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }

    public RegisterRequest getRegisterRequest() {
        return registerRequest;
    }

    public void setRegisterRequest(RegisterRequest registerRequest) {
        this.registerRequest = registerRequest;
    }
}
