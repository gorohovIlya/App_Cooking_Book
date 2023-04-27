package com.example.my_cooking_book.data.repository;

import com.example.my_cooking_book.data.parse.RetrofitService;
import com.example.my_cooking_book.data.parse.recipes.RecipeResponse;
import com.example.my_cooking_book.data.parse.recipes.RecipesApiService;
import com.example.my_cooking_book.domain.model.recipe.Hits;

import retrofit2.Call;

public class RecipesRepository {

    public static Call<RecipeResponse> getRecipes(String ingredient) {
        return RecipesApiService.getInstance().getRecipes("public", ingredient, RetrofitService.APP_ID, RetrofitService.APP_KEY);
    }
}
