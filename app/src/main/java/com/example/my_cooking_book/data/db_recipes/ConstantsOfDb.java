package com.example.my_cooking_book.data.db_recipes;

public class ConstantsOfDb {
// Таблица с рецептами
    public static final String TABLE_NAME_1 = "recipes_table";
    public static final String _ID_1 = "_id";
    public static final String NAME_OF_RECIPE = "name_of_recipe";
    public static final String _INGRED = "_ingred";
    public static final String WAY_OF_PREP = "way_of_prep";
// База данных
    public static final String DB_NAME = "db_recipes.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_1_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME_1 + " (" + _ID_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_OF_RECIPE + " TEXT," +
            _INGRED + " TEXT," + WAY_OF_PREP + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_1;

}
