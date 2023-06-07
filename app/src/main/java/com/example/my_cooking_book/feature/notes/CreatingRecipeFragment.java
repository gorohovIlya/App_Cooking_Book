package com.example.my_cooking_book.feature.notes;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db.DbHelper;

public class CreatingRecipeFragment extends Fragment {
    EditText recipe_name;
    EditText ingredients;
    EditText how_to_prepare;
    Button create_recipe;

    DbHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creating_recipe, container, false);
        recipe_name = view.findViewById(R.id.recipe_name);
        ingredients = view.findViewById(R.id.ingreds);
        how_to_prepare = view.findViewById(R.id.how_to_prepare);
        create_recipe = (Button) view.findViewById(R.id.create_recipe);
        dbHelper = new DbHelper(view.getContext());

        create_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(view.getContext());
                dbHelper.addRecipe(recipe_name.getText().toString().trim(),
                        ingredients.getText().toString().trim(),
                        how_to_prepare.getText().toString().trim());

                NotesListFragment notesListFragment = new NotesListFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, notesListFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}