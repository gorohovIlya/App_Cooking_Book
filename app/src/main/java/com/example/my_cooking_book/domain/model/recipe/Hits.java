package com.example.my_cooking_book.domain.model.recipe;

import java.util.ArrayList;

public class Hits {

    public class Recipe {
        public String label;
        public String image;
        public String calories;
        public String totalWeight;
        public String totalTime;
        public ArrayList<String> ingredientLines;
    }

    public Recipe recipe;
}
