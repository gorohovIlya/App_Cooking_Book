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
import com.example.my_cooking_book.domain.model.recipe.Hits;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    EditText etSearch;
    Button btnSearch;
    ListView listView;
    ArrayList<Hits> listRecipes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        etSearch = view.findViewById(R.id.et_search);
        btnSearch = view.findViewById(R.id.btn_search);
        listView = (ListView) view.findViewById(R.id.list_recipes);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RecipeResponse> responseCall = RecipesRepository.getRecipes(etSearch.getText().toString());
                responseCall.enqueue(new Callback<RecipeResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<RecipeResponse> call, @NonNull Response<RecipeResponse> response) {
                        if(response.isSuccessful() && response.code() == 200){
                            RecipeResponse recipeResponse = response.body();
                            listRecipes = recipeResponse.hits;

                            ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
                            for (int i = 0; i < listRecipes.size(); i++) {
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("image", listRecipes.get(i).recipe.image);
                                map.put("label", listRecipes.get(i).recipe.label);
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
                            listView.setAdapter(adapter);

                            Log.i("MyLog", listRecipes.get(0).recipe.ingredientLines.get(0) + " !");
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecipeFragment recipeFragment = new RecipeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, recipeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}