package com.example.my_cooking_book.feature.notes;

import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db.DbHelper;

public class NoteFragment extends Fragment {

    TextView note_ingredients;
    TextView note_instruction;
    TextView note_name;
    ImageView dish_picture;
    Button deleteBtn;
    Button toPrevFragBtn;
    Button chooseImgBtn;

    String id_recipe, name, ingreds, way_of_prep;

    public NoteFragment(String id_recipe, String name, String ingreds, String way_of_prep) {
        this.id_recipe = id_recipe;
        this.name = name;
        this.ingreds = ingreds;
        this.way_of_prep = way_of_prep;
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
        chooseImgBtn = view.findViewById(R.id.choose_img);

        note_name.setText(name);
        note_ingredients.setText(ingreds);
        note_instruction.setText(way_of_prep);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        toPrevFragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesListFragment notesListFragment = new NotesListFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, notesListFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;

    }

    private void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Удаление " + name);
        builder.setMessage("Вы уверены, что хотите удалить " + name + "?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DbHelper dbHelper = new DbHelper(getContext());
                dbHelper.deleteOneRecipe(id_recipe);
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
