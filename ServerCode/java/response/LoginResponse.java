package response;

/**
 * Created by jswense2 on 10/8/18.
 */

public class LoginResponse extends Response {
    private String authToken;
    private String userName;
    private String personID;

    /**
     * LoginResponse Constructor
     * @param authToken Non-empty auth token string
     * @param userName User name passed in with request
     * @param personID Non-empty string containing the Person ID of the user’s generated Person object
     */
    public LoginResponse(String authToken, String userName, String personID) {
        super();
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
