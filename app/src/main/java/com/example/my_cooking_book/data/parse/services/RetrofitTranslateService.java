package com.example.my_cooking_book.data.parse.services;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTranslateService {

    private static final String BASE_URL = "https://translate.api.cloud.yandex.net";
    public static String AIM_TOKEN = "t1.9euelZrMzs2KjJWLkpuSm4qQmMeKm-3rnpWanZzPmM2Pl4_Pl5LGy4qezZPl9PdEYSJc-e8PRgTl3fT3BBAgXPnvD0YE5Q.DGKUoUngs-Rr36WYVF6TPqjT5FfNUEpCDE_xh9HleuCBWgIh5WSBO9qbX17mGJxDquptEyGkZ22AX_ZtqR0rBQ";
    public static final String FOLDER_ID = "b1gv8vupjdrpuciv2va8";

    private static Retrofit retrofit;

    private static Retrofit create(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static Retrofit getInstance(){
        if(retrofit == null) retrofit = create();
        return retrofit;
    }
}
