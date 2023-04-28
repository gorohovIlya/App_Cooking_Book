package com.example.my_cooking_book.data.parse;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://api.edamam.com";
    public static final String APP_ID = "b0f2d8f2";
    public static final String APP_KEY = "04e3a4c65b1c48a7846779e2851726cf";

    private static Retrofit retrofit;

    private static Retrofit create(){
        Log.i("MyLog", "create в RetrofitService");
        return new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static Retrofit getInstance(){
        Log.i("MyLog", "getInstance в RetrofitService");
        if(retrofit == null) retrofit = create();
        return retrofit;
    }
}
