package request;

/**
 * @author Jared Swensen
 */

public class RegisterRequest {
    private String userName;
    private String password;

    /**
     * RegisterRequest Constructor
     * @param userName Non-empty string
     * @param password Non-empty string
     */
    public RegisterRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
