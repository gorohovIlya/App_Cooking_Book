package com.example.my_cooking_book.feature.notes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.example.my_cooking_book.feature.notes.DbManager;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db_recipes.ConstantsOfDb;
import com.example.my_cooking_book.data.db_recipes.DbHelper;

public class CreatingRecipeFragment extends Fragment {
    EditText recipe_name;
    EditText ingredients;
    EditText how_to_prepare;
    Button create_recipe;
    SQLiteDatabase sdb;
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
                sdb = dbHelper.getWritableDatabase();
                DbManager dbManager = new DbManager(getContext());
                ContentValues user_data = dbManager.insertToDb(recipe_name.getText().toString(),
                        ingredients.getText().toString(),
                        how_to_prepare.getText().toString());
                sdb.insert(ConstantsOfDb.TABLE_NAME_1, null, user_data);
                Toast.makeText(getContext(), "Информация сохранена",
                        Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }
}