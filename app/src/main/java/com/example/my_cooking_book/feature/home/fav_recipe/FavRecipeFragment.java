package com.example.my_cooking_book.feature.home.fav_recipe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.my_cooking_book.data.db.DbHelper;
import com.example.my_cooking_book.databinding.FragmentFavRecipeBinding;
import com.squareup.picasso.Picasso;

public class FavRecipeFragment extends Fragment {

    String id;
    String imageUrl;
    String label;
    String calories;
    String totalWeight;
    String totalTime;
    String url;

    private FragmentFavRecipeBinding binding;
    private DbHelper dbHelper;

    public FavRecipeFragment(String id, String imageUrl, String label, String calories, String totalWeight, String totalTime, String url){
        this.id = id;
        this.imageUrl = imageUrl;
        this.label = label;
        this.calories = calories;
        this.totalWeight = totalWeight;
        this.totalTime = totalTime;
        this.url = url;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavRecipeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        Picasso.get().load(imageUrl).into(binding.imageRecipeFav);
        binding.titleRecipeFav.setText(label);
        binding.caloriesFav.setText(calories + " ккал");
        binding.weightFav.setText(totalWeight + " г");
        binding.timeFav.setText(totalTime + " мин");
        binding.webUrlFav.setText(url);
        Linkify.addLinks(binding.webUrlFav, Linkify.WEB_URLS);
        binding.webUrlFav.setMovementMethod(LinkMovementMethod.getInstance());

        binding.backButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        binding.deleteFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        return view;
    }

    private void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Удаление " + label);
        builder.setMessage("Вы уверены, что хотите удалить " + label + "?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper = new DbHelper(getContext());
                dbHelper.deleteOneFavRecipe(id);
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        builder.create().show();
    }
}
