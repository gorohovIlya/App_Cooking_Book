package com.example.my_cooking_book.data.repository;

import android.util.Log;

import com.example.my_cooking_book.data.parse.RetrofitService;
import com.example.my_cooking_book.data.parse.recipes.RecipeResponse;
import com.example.my_cooking_book.data.parse.recipes.RecipesApiService;
import com.example.my_cooking_book.domain.model.recipe.Hits;

import retrofit2.Call;

public class RecipesRepository {

    public static Call<RecipeResponse> getRecipes(String ingredient) {
        Log.i("MyLog", "getRecipes в RecipesRepository");
        return RecipesApiService.getInstance().getRecipes("public", ingredient, RetrofitService.APP_ID, RetrofitService.APP_KEY);
    }
}
