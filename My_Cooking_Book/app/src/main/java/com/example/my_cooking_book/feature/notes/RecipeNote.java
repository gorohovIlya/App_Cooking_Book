package com.example.my_cooking_book.feature.notes;


public class RecipeNote {
    String recipeNote_name;
    String recipeNote_ingreds;
    String recipeNote_instruction;

    public RecipeNote(String recipeNote_name, String recipeNote_ingreds, String recipeNote_instruction) {
        this.recipeNote_name = recipeNote_name;
        this.recipeNote_ingreds = recipeNote_ingreds;
        this.recipeNote_instruction = recipeNote_instruction;
    }

    public String getRecipeNote_name() {
        return recipeNote_name;
    }

    public String getRecipeNote_ingreds() {
        return recipeNote_ingreds;
    }

    public String getRecipeNote_instruction() {
        return recipeNote_instruction;
    }
}
