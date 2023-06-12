package com.example.my_cooking_book.feature.notes;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.data.db.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class NotesListFragment extends Fragment{

    RecyclerView user_recipes;
    Button recipe_add;

    DbHelper dbHelper;
    ArrayList<String> recipe_id;
    ArrayList<String> recipe_name;
    ArrayList<String> recipe_ingreds;
    ArrayList<String> recipe_way_of_prep;
  
    SQLiteDatabase sdb;
    Cursor cursor;
    LinkedList<RecipeNote> listNotes;
    RecipeNotesAdapter recipeNotesAdapter;

    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        user_recipes = view.findViewById(R.id.recipe_notes);
        recipe_add = (Button) view.findViewById(R.id.add_recipe);

        dbHelper = new DbHelper(view.getContext());
        recipe_id = new ArrayList<>();
        recipe_name = new ArrayList<>();
        recipe_ingreds = new ArrayList<>();
        recipe_way_of_prep = new ArrayList<>();

        storeDataOfRecipesInArrays();

        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < recipe_id.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name", recipe_name.get(i));
            map.put("ingreds", recipe_ingreds.get(i));
            map.put("way_of_prep", recipe_way_of_prep.get(i));
            dataList.add(map);
        }
        String[] from = {"name"};
        int[] to = {R.id.item_name};
        SimpleAdapter adapter = new SimpleAdapter(view.getContext(), dataList,
                R.layout.user_recipe_item, from, to);
        user_recipes.setAdapter(adapter);
        }
//        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
//        for (int i = 0; i < listNotes.size(); i++) {
//            HashMap<String, Object> map = new HashMap<>();
//            map.put("name", listNotes.get(i).getRecipeNote_name());
//            map.put("ingreds", listNotes.get(i).getRecipeNote_ingreds());
//            map.put("way_of_prep", listNotes.get(i).getRecipeNote_instruction());
//            dataList.add(map);
//        }
//        String[] from = {"name"};
//        int[] to = {R.id.item_name};
//        SimpleAdapter adapter = new SimpleAdapter(view.getContext(), dataList,
//                R.layout.user_recipe_item, from, to);
//        user_recipes.setAdapter(adapter);

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
                NoteFragment noteFragment = new NoteFragment(recipe_id.get(position),
                        recipe_name.get(position),
                        recipe_ingreds.get(position),
                        recipe_way_of_prep.get(position));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, noteFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        recipeNotesAdapter = new RecipeNotesAdapter(view.getContext(), listNotes, getFragmentManager());
        user_recipes.setAdapter(recipeNotesAdapter);
        user_recipes.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    private void storeDataOfRecipesInArrays(){
        Cursor cursor = dbHelper.readAllDataOfRecipes();
        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "Нет заметок.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                recipe_id.add(cursor.getString(0));
                recipe_name.add(cursor.getString(1));
                recipe_ingreds.add(cursor.getString(2));
                recipe_way_of_prep.add(cursor.getString(3));
            }
        }
    }
}
