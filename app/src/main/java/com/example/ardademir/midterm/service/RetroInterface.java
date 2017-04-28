package com.example.ardademir.midterm.service;

import com.example.ardademir.midterm.model.FoursquareResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ardademir on 27.04.2017.
 */


public interface RetroInterface {

    @GET("venues/search")
    Call<FoursquareResponse> getVenueJson(
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("v") String version,
            @Query("ll") String ll);
}
