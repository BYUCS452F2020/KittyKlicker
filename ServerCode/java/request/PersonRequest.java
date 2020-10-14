package request;

/**
 * Created by jswense2 on 10/10/18.
 */

public class PersonRequest {
    private String auth;
    private String id;

    /**
     * PersonRequest Constructor requesting all family members of the current user
     * @param auth auth token of the current user
     */
    public PersonRequest(String auth) {
        this.auth = auth;
    }

    /**
     * PersonRequest Constructor containing a person id
     * @param auth auth token of the current user
     * @param id person id to fetch
     */
    public PersonRequest(String auth, String id) {
        this.auth = auth;
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
