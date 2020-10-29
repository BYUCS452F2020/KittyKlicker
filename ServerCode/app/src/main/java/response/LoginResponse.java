package main.java.response;

/**
 * @author Jared Swensen
 */

public class LoginResponse extends Response {
    private String authToken;
    private String userID;

    /**
     * LoginResponse Constructor
     * @param authToken Non-empty auth token string
     * @param userID User name passed in with request
     */
    public LoginResponse(String authToken, String userID) {
        super();
        this.authToken = authToken;
        this.userID = userID;
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
}
