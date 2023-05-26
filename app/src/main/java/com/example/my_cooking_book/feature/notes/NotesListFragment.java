package com.example.my_cooking_book.feature.notes;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db_recipes.ConstantsOfDb;
import com.example.my_cooking_book.data.db_recipes.DbHelper;
import com.example.my_cooking_book.domain.model.recipe.RecipeNote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class NotesListFragment extends Fragment{

    ListView user_recipes;
    Button recipe_add;
    SQLiteDatabase sdb;
    Cursor cursor;
    LinkedList<RecipeNote> listNotes;

    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        listNotes = new LinkedList<>();
        user_recipes = view.findViewById(R.id.recipe_notes);
        recipe_add = (Button) view.findViewById(R.id.add_recipe);

        DbHelper dbHelper = new DbHelper(view.getContext());
        sdb = dbHelper.getWritableDatabase();
        cursor = sdb.query(ConstantsOfDb.TABLE_NAME_1,
                new String[]{ConstantsOfDb.NAME_OF_RECIPE,
                        ConstantsOfDb._INGRED, ConstantsOfDb.WAY_OF_PREP},
                null, null, null, null, null);
        String strQuery = "SELECT " + ConstantsOfDb.NAME_OF_RECIPE + " FROM " + ConstantsOfDb.TABLE_NAME_1 + ";";
        Cursor resp = sdb.rawQuery(strQuery, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String nameStr = "", ingredStr = "", wayOfPrepStr = "";
            nameStr = cursor.getString(cursor.getColumnIndex(ConstantsOfDb.NAME_OF_RECIPE));
            ingredStr = cursor.getString(cursor.getColumnIndex(ConstantsOfDb._INGRED));
            wayOfPrepStr = cursor.getString(cursor.getColumnIndex(ConstantsOfDb.WAY_OF_PREP));
            RecipeNote recipeNote = new RecipeNote(nameStr, ingredStr, wayOfPrepStr);
            listNotes.add(recipeNote);

        }
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < listNotes.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", listNotes.get(i).getRecipeNote_name());
            map.put("ingreds", listNotes.get(i).getRecipeNote_ingreds());
            map.put("way_of_prep", listNotes.get(i).getRecipeNote_instruction());
            dataList.add(map);
        }
        String[] from = {"name"};
        int[] to = {R.id.item_name};
        SimpleAdapter adapter = new SimpleAdapter(view.getContext(), dataList,
                R.layout.user_recipe_item, from, to);
        user_recipes.setAdapter(adapter);

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
        user_recipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoteFragment noteFragment = new NoteFragment(listNotes.get(position));

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, noteFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        cursor.close();
        sdb.close();
    }
}
