package com.example.my_cooking_book.domain.model.recipe;

public class Hits {

    public class Recipes {
        String label;
        String image;
        String calories;
        String totalWeight;
        String totalTime;

        public class Ingredients {

            String text;
            String weight;
        }

        Ingredients ingredients[];
    }

    Recipes recipes[];
}
