package com.example.my_cooking_book.feature.search_recipe.search;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.my_cooking_book.data.parse.recipes.response.RecipeResponse;
import com.example.my_cooking_book.data.parse.services.RetrofitTranslateService;
import com.example.my_cooking_book.data.parse.translate.body.TranslateBody;
import com.example.my_cooking_book.data.parse.translate.response.TranslateResponse;
import com.example.my_cooking_book.data.repository.Repository;
import com.example.my_cooking_book.databinding.FragmentSearchBinding;
import com.example.my_cooking_book.domain.model.recipe.Hits;
import com.example.my_cooking_book.feature.search_recipe.search.recycler.RecipesRecyclerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BindSearchAdapter {

    private static RecipesRecyclerAdapter adapter;

    static void checkBoxToBind(String ingreds, String time, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        if(binding.checkBox.isChecked()){
            if(binding.etFromCalories.getText().toString().equals("")
                    && binding.etToCalories.getText().toString().equals("")
                    && !binding.etTime.getText().toString().equals("")){
                BindSearchAdapter.translateIngredsByTimeToApi(ingreds, time, listRecipes, binding, view);
            }else if(!binding.etFromCalories.getText().toString().equals("")
                    && !binding.etToCalories.getText().toString().equals("")
                    && binding.etTime.getText().toString().equals("")){
                BindSearchAdapter.translateIngredsByCaloriesToApi(ingreds, listRecipes, binding, view);
            }else if(!binding.etFromCalories.getText().toString().equals("")
                    && !binding.etToCalories.getText().toString().equals("")
                    && !binding.etTime.getText().toString().equals("")){
                BindSearchAdapter.translateIngredsByCaloriesAndTimeToApi(ingreds, time, listRecipes, binding, view);
            }else{
                Toast.makeText(view.getContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
            }
        }else
            BindSearchAdapter.translateIngredsToApi(ingreds, listRecipes, binding, view);
    }
    
    static void translateIngredsByCaloriesAndTimeToApi(String ingreds, String time, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        if (!binding.addIngredients.getText().toString().equals("")) {
            binding.progressBar.setVisibility(View.VISIBLE);
            TranslateBody translateBody = createBodyForTranslateIngreds(ingreds);
            Call<TranslateResponse> translateResponseCall = Repository.getTranslate(RetrofitTranslateService.AIM_TOKEN, translateBody);
            translateResponseCall.enqueue(new Callback<TranslateResponse>() {
                @Override
                public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        TranslateResponse translateResponse = response.body();
                        bindRecipesByCaloriesAndTime(translateResponse.translations[0].text, time,
                                listRecipes, binding, view);
                    }
                }
                @Override
                public void onFailure(Call<TranslateResponse> call, Throwable t) {}
            });
        } else
            Toast.makeText(view.getContext(), "Добавьте ингредиент!", Toast.LENGTH_SHORT).show();
    }

    static void translateIngredsByCaloriesToApi(String ingreds, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        if (!binding.addIngredients.getText().toString().equals("")) {
            binding.progressBar.setVisibility(View.VISIBLE);
            TranslateBody translateBody = createBodyForTranslateIngreds(ingreds);
            Call<TranslateResponse> translateResponseCall = Repository.getTranslate(RetrofitTranslateService.AIM_TOKEN, translateBody);
            translateResponseCall.enqueue(new Callback<TranslateResponse>() {
                @Override
                public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        TranslateResponse translateResponse = response.body();
                        bindRecipesByCalories(translateResponse.translations[0].text,
                                listRecipes, binding, view);
                    }
                }
                @Override
                public void onFailure(Call<TranslateResponse> call, Throwable t) {}
            });
        } else
            Toast.makeText(view.getContext(), "Добавьте ингредиент!", Toast.LENGTH_SHORT).show();
    }

    static void translateIngredsByTimeToApi(String ingreds, String time, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        if (!binding.addIngredients.getText().toString().equals("")) {
            binding.progressBar.setVisibility(View.VISIBLE);
            TranslateBody translateBody = createBodyForTranslateIngreds(ingreds);
            Call<TranslateResponse> translateResponseCall = Repository.getTranslate(RetrofitTranslateService.AIM_TOKEN, translateBody);
            translateResponseCall.enqueue(new Callback<TranslateResponse>() {
                @Override
                public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                    if (response.isSuccessful() && response.code() == 200) {
                        TranslateResponse translateResponse = response.body();
                        bindRecipesByTime(translateResponse.translations[0].text, time,
                                listRecipes, binding, view);
                    }
                }
                @Override
                public void onFailure(Call<TranslateResponse> call, Throwable t) {}
            });
        } else
            Toast.makeText(view.getContext(), "Добавьте ингредиент!", Toast.LENGTH_SHORT).show();
    }

    static void translateIngredsToApi(String ingreds, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        if (!binding.addIngredients.getText().toString().equals("")) {
            binding.progressBar.setVisibility(View.VISIBLE);
            TranslateBody translateBody = createBodyForTranslateIngreds(ingreds);
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
                public void onFailure(Call<TranslateResponse> call, Throwable t) {}
            });
        } else
            Toast.makeText(view.getContext(), "Добавьте ингредиент!", Toast.LENGTH_SHORT).show();
    }
    
    static void bindRecipesByCaloriesAndTime(String ingreds, String time, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        Call<RecipeResponse> responseRecipeCall = Repository.getRecipesByTime(ingreds, time);
        responseRecipeCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    RecipeResponse recipeResponse = response.body();
                    listRecipes.clear();
                    sortRecipesByCalories(recipeResponse, listRecipes, binding);
                    if (listRecipes.size() == 0) {
                        binding.textNoRecipes.setVisibility(View.VISIBLE);
                        binding.recipesRecyclerView.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.GONE);
                    } else {
                        binding.textNoRecipes.setVisibility(View.GONE);
                        binding.recipesRecyclerView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.VISIBLE);
                        translateRecipes(listRecipes, binding, view);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<RecipeResponse> call, @NonNull Throwable t) {}
        });
    }

    static void bindRecipesByCalories(String ingreds, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view){
        Call<RecipeResponse> responseRecipeCall = Repository.getRecipes(ingreds);
        responseRecipeCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    RecipeResponse recipeResponse = response.body();
                    listRecipes.clear();
                    sortRecipesByCalories(recipeResponse, listRecipes, binding);
                    if (listRecipes.size() == 0) {
                        binding.textNoRecipes.setVisibility(View.VISIBLE);
                        binding.recipesRecyclerView.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.GONE);
                    } else {
                        binding.textNoRecipes.setVisibility(View.GONE);
                        binding.recipesRecyclerView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.VISIBLE);
                        translateRecipes(listRecipes, binding, view);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<RecipeResponse> call, @NonNull Throwable t) {}
        });
    }

    static void bindRecipesByTime(String ingreds, String time, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view) {
        Call<RecipeResponse> responseRecipeCall = Repository.getRecipesByTime(ingreds, time);
        responseRecipeCall.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    RecipeResponse recipeResponse = response.body();
                    listRecipes.clear();
                    listRecipes.addAll(recipeResponse.hits);
                    if (listRecipes.size() == 0) {
                        binding.textNoRecipes.setVisibility(View.VISIBLE);
                        binding.recipesRecyclerView.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.GONE);
                    } else {
                        binding.textNoRecipes.setVisibility(View.GONE);
                        binding.recipesRecyclerView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.VISIBLE);
                        translateRecipes(listRecipes, binding, view);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<RecipeResponse> call, @NonNull Throwable t) {}
        });
    }


    static void bindRecipes(String ingreds, ArrayList<Hits> listRecipes, FragmentSearchBinding binding, View view) {
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
                        binding.recipesRecyclerView.setVisibility(View.GONE);
                        binding.progressBar.setVisibility(View.GONE);
                    } else {
                        binding.textNoRecipes.setVisibility(View.GONE);
                        binding.recipesRecyclerView.setVisibility(View.VISIBLE);
                        binding.progressBar.setVisibility(View.VISIBLE);

                        translateRecipes(listRecipes, binding, view);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<RecipeResponse> call, @NonNull Throwable t) {}
        });
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

                    binding.recipesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    adapter = new RecipesRecyclerAdapter(listRecipes);
                    binding.recipesRecyclerView.setAdapter(adapter);
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

    static void sortRecipesByCalories(RecipeResponse recipeResponse, ArrayList<Hits> listRecipes, FragmentSearchBinding binding){
        for (int i = 0; i < recipeResponse.hits.size(); i++) {
            int calories = (int) Double.parseDouble(recipeResponse.hits.get(i).getRecipe().getCalories());
            if((int) Double.parseDouble(binding.etFromCalories.getText().toString()) <= calories
                    && calories <= (int) Double.parseDouble(binding.etToCalories.getText().toString())){
                listRecipes.add(recipeResponse.hits.get(i));
            }
        }
    }

    static TranslateBody createBodyForTranslateIngreds(String ingreds){
        TranslateBody translateBody = new TranslateBody();
        translateBody.texts = new String[1];
        translateBody.texts[0] = ingreds;
        translateBody.targetLanguageCode = "en";
        return translateBody;
    }
}
