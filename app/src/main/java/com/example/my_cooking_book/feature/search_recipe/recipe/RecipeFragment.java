package com.example.my_cooking_book.feature.search_recipe.recipe;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.my_cooking_book.data.db.DbHelper;
import com.example.my_cooking_book.data.parse.translate.body.TranslateBody;
import com.example.my_cooking_book.data.parse.translate.response.TranslateResponse;
import com.example.my_cooking_book.data.parse.services.RetrofitTranslateService;
import com.example.my_cooking_book.data.repository.Repository;
import com.example.my_cooking_book.databinding.FragmentRecipeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeFragment extends Fragment {

    String imageUrl;
    String label;
    int calories;
    int totalWeight;
    int totalTime;
    String url;
    ArrayList<String> ingredientsLines;

    private IngredientRecyclerAdapter adapter;
    private FragmentRecipeBinding binding;
    private DbHelper dbHelper;

    public RecipeFragment(String imageUrl, String label, String calories, String totalWeight, String totalTime, String url, ArrayList<String> ingredientsLines) {
        this.imageUrl = imageUrl;
        this.label = label;
        this.calories = (int) Double.parseDouble(calories);
        this.totalWeight = (int) Double.parseDouble(totalWeight);
        this.totalTime = (int) Double.parseDouble(totalTime);
        this.url = url;
        this.ingredientsLines = ingredientsLines;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        TranslateBody translateBody = new TranslateBody();
        translateBody.texts = new String[ingredientsLines.size()];
        for (int i = 0; i < ingredientsLines.size(); i++) {
            translateBody.texts[i] = ingredientsLines.get(i);
        }
        translateBody.targetLanguageCode = "ru";

        Call<TranslateResponse> responseTranslateCall = Repository.getTranslate(RetrofitTranslateService.AIM_TOKEN, translateBody);
        responseTranslateCall.enqueue(new Callback<TranslateResponse>() {
            @Override
            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                if(response.isSuccessful() && response.code() == 200) {
                    TranslateResponse translateResponse = response.body();
                    ingredientsLines.clear();

                    for (int i = 0; i < translateResponse.translations.length; i++) {
                        ingredientsLines.add(translateResponse.translations[i].text);
                    }

                    binding.titleRecipe.setText(label);
                    Picasso.get().load(imageUrl).into(binding.imageRecipe);
                    binding.calories.setText(calories + " ккал");
                    binding.time.setText(totalTime + " мин");
                    binding.weight.setText(totalWeight + " г");

                    binding.webUrl.setText(url);
                    Linkify.addLinks(binding.webUrl, Linkify.WEB_URLS);
                    binding.webUrl.setMovementMethod(LinkMovementMethod.getInstance());

                    binding.ingredientRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    adapter = new IngredientRecyclerAdapter(ingredientsLines);
                    binding.ingredientRecyclerView.setAdapter(adapter);
                }else
                    Toast.makeText(view.getContext(),
                            "Токен не получен " + response.code(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<TranslateResponse> call, Throwable t) {}
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        binding.addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DbHelper(view.getContext());
                dbHelper.addFavRecipe(imageUrl, label, String.valueOf(calories), String.valueOf(totalTime),
                        String.valueOf(totalWeight), url);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
