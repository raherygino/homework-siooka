package com.gsoft.homework.viewmodel;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;

import com.gsoft.homework.api.LocationTrack;

public class MainViewModel extends ViewModel {

    public MainViewModel(Context context) {

        LocationTrack locationTrack = new LocationTrack(context);

        if (locationTrack.canGetLocation()) {
            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            Toast.makeText(context, "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
        } else {
            locationTrack.showSettingsAlert();
        }
    }

}