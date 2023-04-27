package com.example.my_cooking_book.data.parse.recipes;

import com.example.my_cooking_book.data.parse.RetrofitService;

public class RecipesApiService {

    private static RecipesApi recipesApi;

    private static RecipesApi create() {
        return RetrofitService.getInstance().create(RecipesApi.class);
    }

    public static RecipesApi getInstance() {
        if (recipesApi == null) recipesApi = create();
        return recipesApi;
    }
}
