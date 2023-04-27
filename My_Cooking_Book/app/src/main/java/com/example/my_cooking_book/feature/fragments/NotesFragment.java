package com.example.my_cooking_book.feature.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.my_cooking_book.R;

public class NotesFragment extends Fragment{
    ListView user_recipes;
    Button recipe_add;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, null);
        user_recipes = view.findViewById(R.id.recipe_notes);
        recipe_add = (Button) view.findViewById(R.id.add_recipe);
//        return inflater.inflate(R.layout.fragment_notes, container, false);
        recipe_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                CreatingRecipeFragment creatingRecipeFragment = new CreatingRecipeFragment();
                transaction.replace(R.id.container, creatingRecipeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }}
