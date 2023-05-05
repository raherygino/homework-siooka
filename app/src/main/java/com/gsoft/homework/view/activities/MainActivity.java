package com.gsoft.homework.view.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.google.android.material.snackbar.Snackbar;
import com.gsoft.homework.databinding.ActivityMainBinding;
import com.gsoft.homework.view.adapters.VenueAdapter;
import com.gsoft.homework.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainViewModel viewModel = new MainViewModel(this);
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
}