package com.example.my_cooking_book.data.parse.recipes;

public class RecipeResponse {
    public int count;
    public class Hits {

        public class Recipe {
            public String label;
//            String image;
//            String calories;
//            String totalWeight;
//            String totalTime;

//            class Ingredients {
//
//                String text;
//                String weight;
//            }
//
//            Ingredients ingredients[];
        }

        public Recipe recipe;
    }

    public Hits hits[];
}
