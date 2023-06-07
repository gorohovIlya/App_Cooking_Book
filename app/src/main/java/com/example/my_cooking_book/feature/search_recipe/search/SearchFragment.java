package com.example.my_cooking_book.feature.search_recipe.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.parse.translate.body.TranslateBody;
import com.example.my_cooking_book.databinding.FragmentSearchBinding;
import com.example.my_cooking_book.domain.model.recipe.Hits;
import com.example.my_cooking_book.feature.search_recipe.recipe.RecipeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    ArrayList<Hits> listRecipes = new ArrayList<>();
    private String addIngredientText = "";

    TranslateBody translateBody = new TranslateBody();

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
        }
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindSearchAdapter.translateIngredsToApi(binding.addIngredients.getText().toString(),
                        listRecipes, binding, view);
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

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}