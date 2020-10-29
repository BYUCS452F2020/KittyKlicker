package main.java.response;

/**
 * @author Jared Swensen
 */

public class RegisterResponse extends Response {
    private String authToken;
    private String userID;

    /**
     * RegisterResponse Constructor
     * @param authToken Non-empty auth token string
     * @param userID User name passed in with request
     */
    public RegisterResponse(String authToken, String userID) {
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

    public String getuserID() {
        return userID;
    }

    public void setuserID(String userID) {
        this.userID = userID;
    }
}
