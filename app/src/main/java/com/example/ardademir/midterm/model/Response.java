package com.example.ardademir.midterm.model;

import java.util.List;

/**
 * Created by ardademir on 27.04.2017.
 */

public class Response {

    private List<Venue> venues;
    private boolean confident;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public boolean isConfident() {
        return confident;
    }

    public void setConfident(boolean confident) {
        this.confident = confident;
    }
}
