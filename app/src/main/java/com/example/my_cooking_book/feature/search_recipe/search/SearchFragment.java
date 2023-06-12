package com.example.my_cooking_book.feature.search_recipe.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.databinding.FragmentSearchBinding;
import com.example.my_cooking_book.domain.model.recipe.Hits;
import com.example.my_cooking_book.feature.search_recipe.recipe.RecipeFragment;
import com.example.my_cooking_book.feature.search_recipe.search.recycler.RecipesRecyclerAdapter;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    ArrayList<Hits> listRecipes = new ArrayList<>();
    private String addIngredientText = "";

    private RecipesRecyclerAdapter adapter;
    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        IAMWorker.schedulePeriodicWork();

        if (!addIngredientText.equals("")) {
            binding.addIngredients.setText(addIngredientText);
            binding.btnDeleteIcon.setVisibility(View.VISIBLE);
        }

        if (listRecipes != null) {
            binding.recipesRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            adapter = new RecipesRecyclerAdapter(listRecipes);
            binding.recipesRecyclerView.setAdapter(adapter);
        }
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindSearchAdapter.checkBoxToBind(binding.addIngredients.getText().toString(),
                        binding.etTime.getText().toString(),
                        listRecipes, binding, view);
            }
        });

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
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

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.etSearch.getText().toString().equals("")) {
                    binding.addIngredients.append(binding.etSearch.getText().toString() + " ");
                    addIngredientText = binding.addIngredients.getText().toString();
                    binding.etSearch.setText("");
                }
                if (!binding.addIngredients.getText().toString().equals(""))
                    binding.btnDeleteIcon.setVisibility(View.VISIBLE);
            }
        });

        binding.btnDeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.addIngredients.setText("");
                binding.btnDeleteIcon.setVisibility(View.GONE);
                addIngredientText = "";
            }
        });

        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    binding.tvCalories.setVisibility(View.VISIBLE);
                    binding.tvTime.setVisibility(View.VISIBLE);
                    binding.linearCalories.setVisibility(View.VISIBLE);
                    binding.linearTime.setVisibility(View.VISIBLE);
                }else{
                    binding.tvCalories.setVisibility(View.GONE);
                    binding.tvTime.setVisibility(View.GONE);
                    binding.linearCalories.setVisibility(View.GONE);
                    binding.linearTime.setVisibility(View.GONE);
                }
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