package response;

/**
 * @author Jared Swensen
 */

public class KlickResponse extends Response {
    private int kittiesKlicked;

    /**
     * response that sends data to update after a klick
     *
     * We can expand this to include updates about powerups earned... etc.
     *
     * @param kittiesKlicked the new kittiesKlicked total
     */
    public KlickResponse(int kittiesKlicked) {
        this.kittiesKlicked = kittiesKlicked;
    }

    public int getKittiesKlicked() {
        return kittiesKlicked;
    }

    public void setKittiesKlicked(int kittiesKlicked) {
        this.kittiesKlicked = kittiesKlicked;
    }
}
