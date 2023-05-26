package com.example.my_cooking_book.data.parse.recipes.api;

import com.example.my_cooking_book.data.parse.recipes.response.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RecipesApi {

    @GET("/search")
//    @Headers({"Accept: application/json", "Accept-Language: en"})
    Call<RecipeResponse> getRecipes(@Query("q") String ingredient,
                                    @Query("app_id") String app_id,
                                    @Query("app_key") String app_key,
                                    @Query("to") Integer to);
}
