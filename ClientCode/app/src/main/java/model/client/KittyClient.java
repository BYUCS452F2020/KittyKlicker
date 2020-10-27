package model.client;

import android.util.Pair;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polyline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

import model.LoginRequest;
import model.RegisterRequest;
import model.server.EventResponse;
import model.server.PersonResponse;

public class KittyClient {

    private static KittyClient client;

    private String host;
    private String port;

    private String userID;
    private String teamName;
    private int kittiesKlicked;
    private int kittyPower;
    private ArrayList<String> powerups;

    public static KittyClient getClient() {
        if (client == null)
            client = new KittyClient();
        return client;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getKittiesKlicked() {
        return kittiesKlicked;
    }

    public void setKittiesKlicked(int kittiesKlicked) {
        this.kittiesKlicked = kittiesKlicked;
    }

    public int getKittyPower() {
        return kittyPower;
    }

    public void setKittyPower(int kittyPower) {
        this.kittyPower = kittyPower;
    }

    public ArrayList<String> getPowerups() {
        return powerups;
    }

    public void setPowerups(ArrayList<String> powerups) {
        this.powerups = powerups;
    }
}
