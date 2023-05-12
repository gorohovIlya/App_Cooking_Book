package com.example.my_cooking_book.data.parse.recipes.api;

import android.util.Log;

import com.example.my_cooking_book.data.parse.service.RetrofitService;

public class RecipesApiService {

    private static RecipesApi recipesApi;

    private static RecipesApi create() {
        Log.i("MyLog", "create в RecipesApiService");
        return RetrofitService.getInstance().create(RecipesApi.class);
    }

    public static RecipesApi getInstance() {
        Log.i("MyLog", "getInstance в RecipesApiService");
        if (recipesApi == null) recipesApi = create();
        return recipesApi;
    }
}
