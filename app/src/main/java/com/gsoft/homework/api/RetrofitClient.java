package com.gsoft.homework.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.foursquare.com/v3/";
    private static final String AUTH_TOKEN = "fsq3Zqz/AgPNrDCzy68/ZmgWqYKPUz2kmyhiQLkWR01STrA=";
    private static final String DISTANCE = "DISTANCE";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }

    public static Call<ResponseBody> searchVenues(String query, String latitude, String longitude) {
        return  getApiService().searchVenues(AUTH_TOKEN, query, latitude+","+longitude, true, DISTANCE);
    }
}

