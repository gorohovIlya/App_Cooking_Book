package com.example.my_cooking_book.feature.home;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db.DbHelper;
import com.example.my_cooking_book.databinding.FragmentHomeBinding;
import com.example.my_cooking_book.feature.home.FavRecipesAdapter;
import com.example.my_cooking_book.feature.home.fav_recipe.FavRecipeFragment;
import com.example.my_cooking_book.feature.home.info.InfoFragment;
import com.example.my_cooking_book.feature.search_recipe.search.recycler.OnItemClickListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    FavRecipesAdapter favRecipesAdapter;

    DbHelper dbHelper;
    ArrayList<String> fav_recipe_id;
    ArrayList<String> fav_recipe_image;
    ArrayList<String> fav_recipe_name;
    ArrayList<String> fav_recipe_calories;
    ArrayList<String> fav_recipe_time;
    ArrayList<String> fav_recipe_weight;
    ArrayList<String> fav_recipe_url;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        dbHelper = new DbHelper(v.getContext());

        fav_recipe_id = new ArrayList<>();
        fav_recipe_image = new ArrayList<>();
        fav_recipe_name = new ArrayList<>();
        fav_recipe_calories = new ArrayList<>();
        fav_recipe_time = new ArrayList<>();
        fav_recipe_weight = new ArrayList<>();
        fav_recipe_url = new ArrayList<>();

        storeDataOfFavRecipesInArrays();

        favRecipesAdapter = new FavRecipesAdapter(v.getContext(), fav_recipe_id, fav_recipe_image, fav_recipe_name,
                fav_recipe_calories, fav_recipe_time, fav_recipe_weight, fav_recipe_url);
        binding.favRecipesRecyclerView.setAdapter(favRecipesAdapter);
        binding.favRecipesRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        binding.btnInfoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                InfoFragment infoFragment = new InfoFragment();
                transaction.replace(R.id.container, infoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        favRecipesAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                FavRecipeFragment favRecipeFragment = new FavRecipeFragment(
                        favRecipesAdapter.getId().get(position),
                        favRecipesAdapter.getImage().get(position),
                        favRecipesAdapter.getName().get(position),
                        favRecipesAdapter.getCalories().get(position),
                        favRecipesAdapter.getWeight().get(position),
                        favRecipesAdapter.getTime().get(position),
                        favRecipesAdapter.getUrl().get(position));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, favRecipeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }

    private void storeDataOfFavRecipesInArrays(){
        Cursor cursor = dbHelper.readAllDataOfFavRecipes();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "Нет любимых рецептов.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                fav_recipe_id.add(cursor.getString(0));
                fav_recipe_image.add(cursor.getString(1));
                fav_recipe_name.add(cursor.getString(2));
                fav_recipe_calories.add(cursor.getString(3));
                fav_recipe_time.add(cursor.getString(4));
                fav_recipe_weight.add(cursor.getString(5));
                fav_recipe_url.add(cursor.getString(6));
            }
        }
    }
}