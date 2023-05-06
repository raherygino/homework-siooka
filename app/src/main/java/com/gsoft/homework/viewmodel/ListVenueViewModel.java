package com.gsoft.homework.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gsoft.homework.models.Venue;

import java.util.List;

public class ListVenueViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    private final Context context;
    private final List<Venue> venueList;
    public int position = 0;
    public ListVenueViewModel(Context context, List<Venue> venues) {
        this.context = context;
        this.venueList = venues;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void showVenue() {
        Venue venue = venueList.get(position);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(venue.name);
        alertDialog.setMessage(
                "Adresse: "+venue.location.formattedAddress+
                "\nCity: "+venue.location.locality+
                "\nCountry:"+venue.location.country);

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }
}
