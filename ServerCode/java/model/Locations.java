package model;

import java.util.HashSet;

/**
 * Created by jswense2 on 10/27/18.
 */

public class Locations {
    private LocationData[] data;

    public Locations(LocationData[] data) {
        this.data = data;
    }

    public LocationData[] getData() {
        return data;
    }

    public void setData(LocationData[] data) {
        this.data = data;
    }
}
