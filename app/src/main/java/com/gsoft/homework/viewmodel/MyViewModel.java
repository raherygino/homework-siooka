package com.gsoft.homework.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<String> _value = new MutableLiveData<>();
    public LiveData<String> dataValue = _value;

    public MyViewModel() {
        _value.setValue("hello world");
    }

}
