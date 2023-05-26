package com.example.my_cooking_book.feature.notes;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db_recipes.ConstantsOfDb;
import com.example.my_cooking_book.data.db_recipes.DbHelper;

public class NoteFragment extends Fragment {



    TextView note_ingredients;
    TextView note_instruction;
    TextView note_name;
    ImageView dish_picture;
    SQLiteDatabase notes_sdb;
    Cursor cursor;
    RecipeNote recipeNote;
    Button deleteBtn;
    Button toPrevFragBtn;

    public NoteFragment(RecipeNote recipeNote) {
        this.recipeNote = recipeNote;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_fragment, container, false);
        note_ingredients = view.findViewById(R.id.note_ingreds);
        note_instruction = view.findViewById(R.id.note_instruction);
        dish_picture = view.findViewById(R.id.dish_picture);
        note_name = view.findViewById(R.id.note_name);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        toPrevFragBtn = view.findViewById(R.id.toPrevFragBtn);
        note_name.setText(recipeNote.getRecipeNote_name());
        note_ingredients.setText(recipeNote.getRecipeNote_ingreds());
        note_instruction.setText(recipeNote.getRecipeNote_instruction());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbManager dbManager = new DbManager(view.getContext());
                DbHelper dbHelper = new DbHelper(view.getContext());
                dbManager.DeleteFromDb(note_name.getText().toString());
                Toast.makeText(view.getContext(), "DELETED", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        cursor.close();
//        notes_sdb.close();
//        Log.i("aboba", "note_fragment_on_stop");
//    }
}