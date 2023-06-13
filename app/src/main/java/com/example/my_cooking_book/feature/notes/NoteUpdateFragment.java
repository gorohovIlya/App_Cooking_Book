package com.example.my_cooking_book.feature.notes;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db.DbHelper;

public class NoteUpdateFragment extends Fragment {

    EditText updateRecipeName;
    EditText updateIngredients;
    EditText updateWayOfPrep;
    Button updateRecipe;
    Button toNotesFragBtn;
    Button chooseImgBtn;
    ImageView updateRecipeImg;

    String nameUpdate;
    String ingredsUpdate;
    String wayOfPrepUpdate;
    String recipe_id;
    String image_uri;

    public NoteUpdateFragment(String nameUpdate, String ingredsUpdate, String wayOfPrepUpdate, String recipe_id, String image_uri) {
        this.nameUpdate = nameUpdate;
        this.ingredsUpdate = ingredsUpdate;
        this.wayOfPrepUpdate = wayOfPrepUpdate;
        this.recipe_id =  recipe_id;
        this.image_uri = image_uri;
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_update, container, false);
        updateIngredients = view.findViewById(R.id.update_ingreds);
        updateRecipeName = view.findViewById(R.id.update_recipe_name);
        updateWayOfPrep = view.findViewById(R.id.update_how_to_prepare);
        updateRecipe = view.findViewById(R.id.update_recipe);
        toNotesFragBtn = view.findViewById(R.id.toNoteFragBtn);
        updateRecipeImg = view.findViewById(R.id.updateRecipeImg);

        updateRecipeName.setText(nameUpdate);
        updateIngredients.setText(ingredsUpdate);
        updateWayOfPrep.setText(wayOfPrepUpdate);


        updateRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbhelper = new DbHelper(getContext());
                dbhelper.updateRecipe(recipe_id,
                        updateRecipeName.getText().toString(),
                        updateIngredients.getText().toString(),
                        updateWayOfPrep.getText().toString());

            }
        });




        toNotesFragBtn.setOnClickListener(new View.OnClickListener() {
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


}
