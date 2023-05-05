package com.gsoft.homework.view.activities;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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
    }
}