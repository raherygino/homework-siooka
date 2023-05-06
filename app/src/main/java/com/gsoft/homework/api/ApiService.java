package com.gsoft.homework.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("places/search")
    Call<ResponseBody> searchVenues(
            @Header("Authorization") String authToken,
            @Query("query") String query,
            @Query("near") String city,
            @Query("open_now") Boolean open_now,
            @Query("DISTANCE") String distance
    );

    @Headers("Content-Type: application/json")
    @GET("places/search")
    Call<ResponseBody> searchCity(
            @Header("Authorization") String authToken,
            @Query("ll") String ll
    );
}
