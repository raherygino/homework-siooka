package com.gsoft.homework.api;

import static com.gsoft.homework.constants.network.NetworkConstants.AUTH_TOKEN;
import static com.gsoft.homework.constants.network.NetworkConstants.BASE_URL;
import static com.gsoft.homework.constants.network.NetworkConstants.DISTANCE;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

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

