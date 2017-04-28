package com.example.ardademir.midterm.model;

/**
 * Created by ardademir on 27.04.2017.
 */

public class FoursquareResponse {

    private Meta meta;
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
