package com.gsoft.homework.viewmodel;

import static com.gsoft.homework.constants.data.KeysModelConstants.COUNTRY;
import static com.gsoft.homework.constants.data.KeysModelConstants.FORMATTED_ADDRESS;
import static com.gsoft.homework.constants.data.KeysModelConstants.FSQ_ID;
import static com.gsoft.homework.constants.data.KeysModelConstants.LOCALITY;
import static com.gsoft.homework.constants.data.KeysModelConstants.LOCATION;
import static com.gsoft.homework.constants.data.KeysModelConstants.NAME;
import static com.gsoft.homework.constants.data.KeysModelConstants.RESULTS;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gsoft.homework.BR;
import com.gsoft.homework.api.LocationTrack;
import com.gsoft.homework.api.RetrofitClient;
import com.gsoft.homework.models.Location;
import com.gsoft.homework.models.Venue;
import com.gsoft.homework.utils.VenueParser;

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
    Double longitude = -87.6298; //Default Longitude

    private Context context;
    private MutableLiveData<String> _snackbarMessage = new MutableLiveData<>();
    public LiveData<String> snackbarMessage = _snackbarMessage;

    private MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

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

    @Bindable
    public ObservableList<Venue> getVenues() {
        return venues;
    }

    public void searchVenues() {
        _isLoading.setValue(true);
        venues.clear();
        notifyPropertyChanged(BR.venues);

        Call<ResponseBody> query = RetrofitClient.searchVenues(queryValue.get(),Double.toString(latitude), Double.toString(longitude));
        query.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(response.body().source().readUtf8());
                        JSONArray results = json.getJSONArray(RESULTS);

                        if (results.length() == 0) {
                            _snackbarMessage.setValue("No result for \""+queryValue.get()+"\"");
                        }

                        for (int i = 0; i < results.length(); i++) {
                            JSONObject venueObject = results.getJSONObject(i);
                            venues.add(new VenueParser().Parse(venueObject));
                        }
                        notifyPropertyChanged(BR.venues);

                    } catch (IOException | JSONException e) {
                        _snackbarMessage.setValue(e.getMessage());
                    }
                } else {
                    _snackbarMessage.setValue(response.toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                _snackbarMessage.setValue(t.getMessage());
                _isLoading.setValue(false);
            }
        });
    }
}