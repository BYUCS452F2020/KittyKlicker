package model;

/**
 * Created by jswense2 on 10/8/18.
 * Errors: Request property missing or has invalid value, Username already taken by another user, Internal server error
 */

public class RegisterRequest {
    private String userName;
    private String password;
    private String team;

    /**
     * RegisterRequest Constructor
     * @param userName Non-empty string
     * @param password Non-empty string
     * @param team Non-empty string
     */
    public RegisterRequest(String userName, String password, String team) {
        this.userName = userName;
        this.password = password;
        this.team = team;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getTeam() {
        return team;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}

