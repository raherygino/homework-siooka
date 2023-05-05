package com.gsoft.homework.viewmodel;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gsoft.homework.BR;
import com.gsoft.homework.api.LocationTrack;
import com.gsoft.homework.api.RetrofitClient;
import com.gsoft.homework.models.Venue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends BaseObservable {

    Double latitude = 41.8781; //Default Latitude
    Double longitude = -87.6298; //Default Latitude

    private Context context;
    private final MutableLiveData<String> _data = new MutableLiveData<>();
    public LiveData<String> data = _data;
    public ObservableField<String> queryValue = new ObservableField<>();
    public ObservableList<Venue> venues = new ObservableArrayList<>();

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

    public ObservableList<Venue> getItemList() {
        return venues;
    }

    @Bindable
    public ObservableList<Venue> getVenues() {
        return venues;
    }

    public void searchVenues() {

        Call<ResponseBody> query = RetrofitClient.searchVenues(queryValue.get(),Double.toString(latitude), Double.toString(longitude));
        query.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(response.body().source().readUtf8());
                        JSONArray results = json.getJSONArray("results");
                        String value = "value: ";
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject jsonObject = results.getJSONObject(i);
                            String fsqId = jsonObject.getString("fsq_id");
                            String name = jsonObject.getString("name");
                            venues.add(new Venue(fsqId,name, null));
                        }
                        notifyPropertyChanged(BR.venues);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e) {
                        _data.setValue(e.getMessage());
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