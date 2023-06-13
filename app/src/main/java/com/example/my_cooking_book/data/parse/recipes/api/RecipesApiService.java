package com.example.my_cooking_book.data.parse.recipes.api;

import com.example.my_cooking_book.data.parse.services.RetrofitRecipeService;

public class RecipesApiService {

    private static RecipesApi recipesApi;

    private static RecipesApi create() {
        return RetrofitRecipeService.getInstance().create(RecipesApi.class);
    }

    public static RecipesApi getInstance() {
        if (recipesApi == null) recipesApi = create();
        return recipesApi;
    }
}
