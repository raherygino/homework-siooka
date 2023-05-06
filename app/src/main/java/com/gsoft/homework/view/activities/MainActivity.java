package com.gsoft.homework.view.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.gsoft.homework.databinding.ActivityMainBinding;
import com.gsoft.homework.view.adapters.VenueAdapter;
import com.gsoft.homework.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new MainViewModel(this);
        binding.setViewModel(viewModel);
        ListView listView = binding.listView;
        listView.setAdapter(new VenueAdapter(this, viewModel.getVenues()));
        binding.setLifecycleOwner(this);
        viewModel.snackbarMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Snackbar.make( binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.getMyLocation();
            } else {
                Toast.makeText(this, "Permission is denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getMyLocation();
    }
}