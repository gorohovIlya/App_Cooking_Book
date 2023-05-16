package com.example.my_cooking_book.domain.model.recipe;

import java.util.ArrayList;

public class Hits {

    public class Recipe {
        private String label;
        private String image;
        private String calories;
        private String totalWeight;
        private String totalTime;
        private ArrayList<String> ingredientLines;
        private String url;

        public String getLabel() {
            return label;
        }

        public String getImage() {
            return image;
        }

        public String getCalories() {
            return calories;
        }

        public String getTotalWeight() {
            return totalWeight;
        }

        public String getTotalTime() {
            return totalTime;
        }

        public ArrayList<String> getIngredientLines() {
            return ingredientLines;
        }

        public String getUrl() {
            return url;
        }
    }

    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

}
