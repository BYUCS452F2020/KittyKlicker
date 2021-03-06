package model.response;

/**
 * @author Jared Swensen
 */

public class Response {
    private String message;

    /**
     * Response Constructor for non errors
     */
    public Response() {
    }

    /**
     * Response Constructor for errors
     * @param error error message
     */
    public Response(String error) {
        this.message = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
