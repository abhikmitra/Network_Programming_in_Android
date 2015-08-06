package com.microsoft.retrofitdemo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by abmitra on 8/5/2015.
 */
public interface ApiService {
    @GET("/abhikmitra/Network_Programming_in_Android/master/cuisine.json")
    public void getCuisines(Callback<List<Cuisine>> callback);
}
