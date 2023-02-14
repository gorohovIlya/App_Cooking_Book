package com.example.my_cooking_book.db_recipes;

public class ConstantsOfDb {
    public static final String TABLE_NAME = "recipes_table";
    public static final String _ID = "_id";
    public static final String NAME_OF_RECIPE = "name_of_recipe";
    public static final String _INGRED = "_ingred";
    public static final String WAY_OF_PREP = "way_of_prep";
    public static final String DB_NAME = "db_recipes.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY," + NAME_OF_RECIPE + " TEXT," +
            _INGRED + " TEXT," + WAY_OF_PREP + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
