package com.gsoft.homework.view.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.gsoft.homework.databinding.ActivityMainBinding;
import com.gsoft.homework.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MainViewModel viewModel = new MainViewModel();
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}