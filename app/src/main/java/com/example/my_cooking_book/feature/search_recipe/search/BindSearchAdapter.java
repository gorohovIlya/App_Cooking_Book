package com.example.my_cooking_book.feature.search_recipe.search;

import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.parse.recipes.response.RecipeResponse;
import com.example.my_cooking_book.data.parse.services.RetrofitTranslateService;
import com.example.my_cooking_book.data.parse.translate.body.TranslateBody;
import com.example.my_cooking_book.data.parse.translate.response.TranslateResponse;
import com.example.my_cooking_book.data.repository.Repository;
import com.example.my_cooking_book.databinding.FragmentSearchBinding;
import com.example.my_cooking_book.domain.model.recipe.Hits;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BindSearchAdapter {

    static void translateIngredsToApi(String ingreds, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        binding.progressBar.setVisibility(View.VISIBLE);

        TranslateBody translateBody = new TranslateBody();
        translateBody.texts = new String[1];
        translateBody.texts[0] = ingreds;
        translateBody.targetLanguageCode = "en";

        Call<TranslateResponse> translateResponseCall = Repository.getTranslate(RetrofitTranslateService.AIM_TOKEN, translateBody);
        translateResponseCall.enqueue(new Callback<TranslateResponse>() {
            @Override
            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    TranslateResponse translateResponse = response.body();

                    bindRecipes(translateResponse.translations[0].text,
                            listRecipes, binding, view);
                }
            }

            @Override
            public void onFailure(Call<TranslateResponse> call, Throwable t) {

            }
        });
    }
    static void bindRecipes(String ingreds, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view) {
        if (!binding.addIngredients.getText().toString().equals("")) {
            Call<RecipeResponse> responseRecipeCall = Repository.getRecipes(ingreds);
            responseRecipeCall.enqueue(new Callback<RecipeResponse>() {
                @Override
                public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        RecipeResponse recipeResponse = response.body();
                        listRecipes.clear();

                        listRecipes.addAll(recipeResponse.hits);

                        if (listRecipes.size() == 0) {
                            binding.textNoRecipes.setVisibility(View.VISIBLE);
                            binding.listRecipes.setVisibility(View.GONE);
                            binding.progressBar.setVisibility(View.GONE);
                        } else {
                            binding.textNoRecipes.setVisibility(View.GONE);
                            binding.listRecipes.setVisibility(View.VISIBLE);
                            binding.progressBar.setVisibility(View.VISIBLE);

                            translateRecipes(listRecipes, binding, view);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<RecipeResponse> call, @NonNull Throwable t) {}
            });
        } else
            Toast.makeText(view.getContext(), "Добавьте ингредиент!", Toast.LENGTH_SHORT).show();
    }

    static void translateRecipes(ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        TranslateBody translateBody = new TranslateBody();
        translateBody.texts = new String[listRecipes.size()];
        for (int i = 0; i < listRecipes.size(); i++) {
            translateBody.texts[i] = listRecipes.get(i).getRecipe().getLabel();
        }
        translateBody.targetLanguageCode = "ru";

        Call<TranslateResponse> responseTranslateCall = Repository.getTranslate(RetrofitTranslateService.AIM_TOKEN, translateBody);
        responseTranslateCall.enqueue(new Callback<TranslateResponse>() {
            @Override
            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                if(response.isSuccessful() && response.code() == 200){
                    TranslateResponse translateResponse = response.body();

                    for (int i = 0; i < translateResponse.translations.length; i++) {
                        listRecipes.get(i).getRecipe().setLabel(translateResponse.translations[i].text);
                    }
                    SimpleAdapter adapter = new SimpleAdapter(view.getContext(),
                            SimpleRecipeAdapter.createDataListForAdapter(listRecipes),
                            R.layout.item_list_recipes,
                            SimpleRecipeAdapter.createFromForAdapter(),
                            SimpleRecipeAdapter.createToForAdapter());
                    adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {
                            if (view.getId() == R.id.image_item) {
                                Picasso.get().load(data.toString()).into((ImageView) view);
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
                    binding.listRecipes.setAdapter(adapter);
                    binding.progressBar.setVisibility(View.GONE);
                }else
                    Toast.makeText(view.getContext(),
                            "Токен не получен " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TranslateResponse> call, Throwable t) {
                Toast.makeText(view.getContext(),
                        "Токен не получен " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    static void bindOneRecipe(){

    }
}
