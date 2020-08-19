package com.example.googlemap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface API {
    @GET("maps/api/place/nearbysearch/json?location=21.027763,105.834160&radius=500&types=food&key=AIzaSyCmxFS2arHibTbROQAfTkZAJRkEpz8LErU")
    Call<RootModel> getLocation(@Query("name") String name);
}
