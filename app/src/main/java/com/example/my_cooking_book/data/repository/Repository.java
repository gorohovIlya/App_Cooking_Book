package com.example.my_cooking_book.data.repository;

import com.example.my_cooking_book.data.parse.translate.IAMBody;
import com.example.my_cooking_book.data.parse.translate.IAMResponse;
import com.example.my_cooking_book.data.parse.translate.IAMTokenApi;
import com.example.my_cooking_book.data.parse.translate.IAMTokenApiService;
import com.example.my_cooking_book.data.parse.translate.api.TranslateApiService;
import com.example.my_cooking_book.data.parse.translate.body.TranslateBody;
import com.example.my_cooking_book.data.parse.translate.response.TranslateResponse;
import com.example.my_cooking_book.data.parse.services.RetrofitRecipeService;
import com.example.my_cooking_book.data.parse.recipes.response.RecipeResponse;
import com.example.my_cooking_book.data.parse.recipes.api.RecipesApiService;

import retrofit2.Call;

public class Repository {

    public static Call<RecipeResponse> getRecipes(String ingredient) {
        return RecipesApiService.getInstance().getRecipes(ingredient, RetrofitRecipeService.APP_ID, RetrofitRecipeService.APP_KEY, 100);
    }

    public static Call<TranslateResponse> getTranslate(String aimToken, TranslateBody translateBody){
        return TranslateApiService.getInstance().getTranslate("Bearer " + aimToken, translateBody);
    }

    public static Call<IAMResponse> getIAM(IAMBody iamBody){
        return IAMTokenApiService.getInstance().getIAM(iamBody);
    }
}
