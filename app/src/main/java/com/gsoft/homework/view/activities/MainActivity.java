package com.gsoft.homework.view.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.gsoft.homework.R;
import com.gsoft.homework.databinding.ActivityMainBinding;
import com.gsoft.homework.viewmodel.MyViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        //MyViewModel viewModel = new MyViewModel();
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}