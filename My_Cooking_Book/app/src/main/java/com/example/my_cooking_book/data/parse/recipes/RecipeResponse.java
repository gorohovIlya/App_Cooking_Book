package com.example.my_cooking_book.data.parse.recipes;

public class RecipeResponse {

    public class Hits {

        class Recipes {
            String label;
            String image;
            String calories;
            String totalWeight;
            String totalTime;

            class Ingredients {

                String text;
                String weight;
            }

            Ingredients ingredients[];
        }

        Recipes recipes[];
    }

    Hits hits[];
}
