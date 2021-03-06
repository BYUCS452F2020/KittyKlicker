package model.client;

import java.util.ArrayList;
import java.util.List;

import model.server.PowerUp;

public class KittyClient {

    private static KittyClient client;

    private String host;//todo: set these to whatever your home host/port are
    private String port;// these will NOT be set up so that the user can enter them in

    private String userID;
    private String authToken;
    private String teamName;
    private String teamMotto;
    private int kittiesKlicked;
    private int kittyPower;
    private List<PowerUp> powerups;

    private KittyClient() {
        userID = null;
        authToken = null;
        teamName = null;
        teamMotto = null;
        kittiesKlicked = 0;
        kittyPower = 1; //todo: check that this is correct for starting
        powerups = null;
        host = "192.168.1.218";
        port = "80";
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
        teamMotto = null;
        kittiesKlicked = 0;
        kittyPower = 1; //todo: check that this is correct for starting
        powerups = null;
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

    public String getTeamMotto() {
        return teamMotto;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamMotto(String teamMotto) {
        this.teamMotto = teamMotto;
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

    public List<PowerUp> getPowerups() {
        return powerups;
    }

    public void setPowerups(List<PowerUp> powerups) {
        this.powerups = powerups;
    }
}
