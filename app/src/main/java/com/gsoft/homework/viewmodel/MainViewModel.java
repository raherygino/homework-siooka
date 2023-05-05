package com.gsoft.homework.viewmodel;

import android.content.Context;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.gsoft.homework.api.LocationTrack;
import com.gsoft.homework.api.RetrofitClient;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    Double latitude = 41.8781; //Default Latitude
    Double longitude = -87.6298; //Default Latitude

    private Context context;
    private final MutableLiveData<String> _data = new MutableLiveData<>();
    public LiveData<String> data = _data;
    public ObservableField<String> queryValue = new ObservableField<>();

    public MainViewModel(Context mContext) {
        context = mContext;
        LocationTrack locationTrack = new LocationTrack(context);

        if (locationTrack.canGetLocation()) {
            longitude = locationTrack.getLongitude();
            latitude = locationTrack.getLatitude();
        } else {
            locationTrack.showSettingsAlert();
        }
    }

    public void searchVenues() {
        Call<ResponseBody> query = RetrofitClient.searchVenues(queryValue.get(),Double.toString(latitude), Double.toString(longitude));
        query.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        _data.setValue(response.body().source().readUtf8());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    _data.setValue(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                _data.setValue(t.getMessage());
            }
        });
    }
}