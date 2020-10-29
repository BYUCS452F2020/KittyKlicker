package main.java.model;

/**
 * User Data Structure
 * @author Jared Swensen
 */
public class User {
    private String userID;
    private String password;
    private int kittiesKlicked;
    private int kittiesPerKlick;
    private String teamName;

    public User(String userID, String password, int kittiesKlicked, int kittiesPerKlick, String teamName) {
        this.userID = userID;
        this.password = password;
        this.kittiesKlicked = kittiesKlicked;
        this.kittiesPerKlick = kittiesPerKlick;
        this.teamName = teamName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getKittiesKlicked() {
        return kittiesKlicked;
    }

    public void setKittiesKlicked(int kittiesKlicked) {
        this.kittiesKlicked = kittiesKlicked;
    }

    public int getKittiesPerKlick() {
        return kittiesPerKlick;
    }

    public void setKittiesPerKlick(int kittiesPerKlick) {
        this.kittiesPerKlick = kittiesPerKlick;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
