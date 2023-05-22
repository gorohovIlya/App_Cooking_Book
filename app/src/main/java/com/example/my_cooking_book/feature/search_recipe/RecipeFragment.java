package com.example.my_cooking_book.feature.search_recipe;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.databinding.FragmentRecipeBinding;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeFragment extends Fragment {

    String imageUrl;
    String label;
    String calories;
    String totalWeight;
    String totalTime;
    String url;
    ArrayList<String> ingredientsLines;

    private FragmentRecipeBinding binding;

    public RecipeFragment(String imageUrl, String label, String calories, String totalWeight, String totalTime, String url, ArrayList<String> ingredientsLines) {
        this.imageUrl = imageUrl;
        this.label = label;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.totalTime = totalTime;
        this.url = url;
        this.ingredientsLines = ingredientsLines;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        binding.titleRecipe.setText(label);
        Picasso.get().load(imageUrl).into(binding.imageRecipe);
        binding.calories.setText(calories + " kcal");
        binding.time.setText(totalTime + " min");
        binding.weight.setText(totalWeight + " g");

        binding.webUrl.setText(url);
        Linkify.addLinks(binding.webUrl, Linkify.WEB_URLS);
        binding.webUrl.setMovementMethod(LinkMovementMethod.getInstance());

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < ingredientsLines.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("ingredient", ingredientsLines.get(i));
            dataList.add(map);
        }
        String [] from = {"ingredient"};
        int [] to = {R.id.text_ingredient};
        SimpleAdapter simpleAdapter = new SimpleAdapter(v.getContext(), dataList, R.layout.item_ingredient_lines, from, to);
        binding.ingredientLines.setAdapter(simpleAdapter);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
