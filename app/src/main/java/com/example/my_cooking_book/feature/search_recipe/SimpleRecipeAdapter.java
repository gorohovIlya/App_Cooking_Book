package com.example.my_cooking_book.feature.search_recipe;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.domain.model.recipe.Hits;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleRecipeAdapter {
    public static ArrayList<HashMap<String, Object>> createDataListForAdapter(ArrayList<Hits> list) {
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("image", list.get(i).getRecipe().getImage());
            map.put("label", list.get(i).getRecipe().getLabel());
            dataList.add(map);
        }
        String[] from = {"image", "label"};
        int[] to = {R.id.image_item, R.id.label};
        return dataList;
    }

    public static String[] createFromForAdapter() {
        return new String[]{"image", "label"};
    }

    public static int[] createToForAdapter() {
        return new int[]{R.id.image_item, R.id.label};
    }
}
