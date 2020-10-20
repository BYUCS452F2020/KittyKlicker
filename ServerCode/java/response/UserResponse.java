package response;

import java.util.List;

/**
 * @author Jared Swensen
 */

public class UserResponse extends Response {
    private String userID;
    private int kittiesKlicked;
    private String teamName;

    /**
     * response that sends data needed to display user info
     * @param events the list of event objects
     */
    public UserResponse(String userID, int kittiesKlicked, String teamName) {
        this.userID = userID;
        this.kittiesKlicked = kittiesKlicked;
        this.teamName = teamName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getKittiesKlicked() {
        return kittiesKlicked;
    }

    public void setKittiesKlicked(int kittiesKlicked) {
        this.kittiesKlicked = kittiesKlicked;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
