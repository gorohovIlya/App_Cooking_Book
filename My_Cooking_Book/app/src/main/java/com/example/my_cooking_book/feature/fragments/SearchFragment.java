package com.example.my_cooking_book.feature.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.parse.recipes.RecipeResponse;
import com.example.my_cooking_book.data.repository.RecipesRepository;
import com.example.my_cooking_book.domain.model.recipe.Hits;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    EditText etSearch;
    Button btnSearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        etSearch = v.findViewById(R.id.et_search);
        btnSearch = v.findViewById(R.id.btn_search);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RecipeResponse> responseCall = RecipesRepository.getRecipes(etSearch.getText().toString());
                responseCall.enqueue(new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                        if(response.isSuccessful() && response.code() == 200){
                            Log.e("MyLog", response.toString());
                        } else{
                            Log.e("MyLog", "Ответ не пришёл");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RecipeResponse> call, @NonNull Throwable t) {
                        Log.e("MyLog", "Ошибка");
                    }
                });
            }
        });
        return v;
    }
}