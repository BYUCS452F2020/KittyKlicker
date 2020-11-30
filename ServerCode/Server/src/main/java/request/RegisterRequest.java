package request;

/**
 * @author Jared Swensen
 */

public class RegisterRequest {
    private String userName;
    private String password;
    private String teamName;

    /**
     * RegisterRequest Constructor
     * @param userName Non-empty string
     * @param password Non-empty string
     * @param teamName Non-empty string
     */
    public RegisterRequest(String userName, String password, String teamName) {
        this.userName = userName;
        this.password = password;
        this.teamName = teamName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
