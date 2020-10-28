package model;

/**
 * Team Data Structure
 * @author Jared Swensen
 */
public class Team {
    private String teamName;
    private int totalKittiesKlicked;

    public Team(String teamName, int totalKittiesKlicked) {
        this.teamName = teamName;
        this.totalKittiesKlicked = totalKittiesKlicked;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTotalKittiesKlicked() {
        return totalKittiesKlicked;
    }

    public void setTotalKittiesKlicked(int totalKittiesKlicked) {
        this.totalKittiesKlicked = totalKittiesKlicked;
    }
}
