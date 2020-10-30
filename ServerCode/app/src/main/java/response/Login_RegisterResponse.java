package model.response;

import java.util.List;

/**
 * @author Jared Swensen
 */

public class Login_RegisterResponse extends Response {
    private String authToken;
    private String userID;
    private String teamName;
    private int kittiesKlicked;
    private int kittyPower;
    private List<String> powerups;

    public Login_RegisterResponse(String message) {
        super(message);
    }
    public Login_RegisterResponse(String authToken, String userID, String teamName, int kittiesKlicked, int kittyPower, List<String> powerups) {
        super();
        this.authToken = authToken;
        this.userID = userID;
        this.teamName = teamName;
        this.kittiesKlicked = kittiesKlicked;
        this.kittyPower = kittyPower;
        this.powerups = powerups;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserName() {
        return userID;
    }

    public void setUserName(String userID) {
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

    public List<String> getPowerups() {
        return powerups;
    }

    public void setPowerups(List<String> powerups) {
        this.powerups = powerups;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
