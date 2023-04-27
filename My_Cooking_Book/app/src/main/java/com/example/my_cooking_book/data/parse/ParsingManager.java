package com.example.my_cooking_book.data.parse;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ParsingManager {

    static final String API_KEY = "04e3a4c65b1c48a7846779e2851726cf";
    static final String API_ID = "b0f2d8f2";

    public static void requestRecipeData(String ingred, Context context){
        String url = "https://api.edamam.com/api/recipes/v2?type=public" +
                "&q=" +
                ingred +
                "&app_id=" +
                API_ID +
                "&app_key=" +
                API_KEY;
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                result -> Log.d("MyLog", "Result: " + result),
                error -> Log.d("MyLog", "Error: " + error)
        );
        queue.add(request);
    }
}
