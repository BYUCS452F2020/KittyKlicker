package model.client;

import java.util.ArrayList;
import java.util.List;

import model.server.AuthToken;


public class KittyClient {

    private static KittyClient client;

    private String host;//todo: set these to whatever your home host/port are
    private String port;// these will NOT be set up so that the user can enter them in

    private String userID;
    private String authToken;
    private String teamName;
    private int kittiesKlicked;
    private int kittyPower;
    private List<String> powerups;

    private KittyClient() {
        userID = null;
        authToken = null;
        teamName = null;
        kittiesKlicked = 0;
        kittyPower = 0;//todo: check that this is correct for starting
        powerups = new ArrayList<String>();
    }

    public static KittyClient getClient() {
        if (client == null)
            client = new KittyClient();
        return client;
    }

    public void clear() {
        userID = null;
        authToken = null;
        teamName = null;
        kittiesKlicked = 0;
        kittyPower = 0;//todo: check that this is correct for starting
        powerups = new ArrayList<String>();
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
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

    public List<String> getPowerups() {
        return powerups;
    }

    public void setPowerups(List<String> powerups) {
        this.powerups = powerups;
    }
}
