package main.java.model;

/**
 * Team Data Structure
 * @author Jared Swensen
 */
public class Team {
    private String teamID;
    private String motto;
    private int totalKittiesKlicked;

    public Team(String teamID, String motto, int totalKittiesKlicked) {
        this.teamID = teamID;
        this.motto = motto;
        this.totalKittiesKlicked = totalKittiesKlicked;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public int getTotalKittiesKlicked() {
        return totalKittiesKlicked;
    }

    public void setTotalKittiesKlicked(int totalKittiesKlicked) {
        this.totalKittiesKlicked = totalKittiesKlicked;
    }
}
