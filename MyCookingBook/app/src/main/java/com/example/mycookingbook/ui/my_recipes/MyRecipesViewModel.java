package com.example.mycookingbook.ui.my_recipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRecipesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyRecipesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my recipes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}