package com.example.my_cooking_book.feature.search_recipe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.parse.recipes.response.RecipeResponse;
import com.example.my_cooking_book.data.repository.RecipesRepository;
import com.example.my_cooking_book.databinding.FragmentSearchBinding;
import com.example.my_cooking_book.domain.model.recipe.Hits;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    ArrayList<Hits> listRecipes;

    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                Call<RecipeResponse> responseCall = RecipesRepository.getRecipes(binding.etSearch.getText().toString());
                responseCall.enqueue(new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                        if(response.isSuccessful() && response.code() == 200){
                            RecipeResponse recipeResponse = response.body();
                            listRecipes = recipeResponse.hits;

                            ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
                            for (int i = 0; i < listRecipes.size(); i++) {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("image", listRecipes.get(i).getRecipe().getImage());
                                map.put("label", listRecipes.get(i).getRecipe().getLabel());
                                dataList.add(map);
                            }
                            String [] from = {"image", "label"};
                            int [] to = {R.id.image_item, R.id.label};

                            SimpleAdapter adapter = new SimpleAdapter(v.getContext(), dataList, R.layout.item_list_recipes, from, to);
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

                            Log.i("MyLog", listRecipes.get(0).getRecipe().getUrl() + " !");
                        } else{
                            Log.i("MyLog", "Ответ не пришёл");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<RecipeResponse> call, @NonNull Throwable t) {
                        Log.i("MyLog", "Ошибка");
                    }
                });
            }
        });

        binding.listRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecipeFragment recipeFragment = new RecipeFragment(
                        listRecipes.get(position).getRecipe().getImage(),
                        listRecipes.get(position).getRecipe().getLabel(),
                        listRecipes.get(position).getRecipe().getCalories(),
                        listRecipes.get(position).getRecipe().getTotalWeight(),
                        listRecipes.get(position).getRecipe().getTotalTime(),
                        listRecipes.get(position).getRecipe().getUrl(),
                        listRecipes.get(position).getRecipe().getIngredientLines());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, recipeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }
}