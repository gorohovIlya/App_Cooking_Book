package com.example.my_cooking_book.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    // Таблица с рецептами
    public static final String TABLE_NAME_1 = "recipes_table";
    public static final String _ID_1 = "_id_1";
    public static final String NAME_OF_RECIPE = "name_of_recipe";
    public static final String _INGRED = "_ingred";
    public static final String WAY_OF_PREP = "way_of_prep";

    // Таблица с любимыми рецептами
    public static final String TABLE_NAME_2 = "fav_recipes_table";
    public static final String _ID_2 = "_id_2";
    public static final String IMAGE_OF_FAV_RECIPE = "image_of_fav_recipe";
    public static final String NAME_OF_FAV_RECIPE = "name_of_fav_recipe";
    public static final String CALORIES = "_calories";
    public static final String TIME = "_time";
    public static final String WEIGHT = "_weight";
    public static final String URL = "_url";

    // База данных
    public static final String DB_NAME = "db_recipes.db";
    public static final int DB_VERSION = 1;

    SQLiteDatabase db;
    Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " +
                TABLE_NAME_1 + " (" + _ID_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_OF_RECIPE + " TEXT," +
                _INGRED + " TEXT," + WAY_OF_PREP + " TEXT)";
        String query2 = "CREATE TABLE " +
                TABLE_NAME_2 + " (" + _ID_2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + IMAGE_OF_FAV_RECIPE + " TEXT," +
                NAME_OF_FAV_RECIPE + " TEXT," + CALORIES + " TEXT," + TIME + " TEXT," + WEIGHT + " TEXT," + URL + " TEXT)";
        db.execSQL(query1);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }

    public void addRecipe(String name, String ingred, String wayOfPrep){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME_OF_RECIPE, name);
        cv.put(_INGRED, ingred);
        cv.put(WAY_OF_PREP, wayOfPrep);
        long result = db.insert(TABLE_NAME_1, null, cv);
        if(result == -1){
            Toast.makeText(context, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Добавлено успешно!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public Cursor readAllDataOfRecipes(){
        String query = "SELECT * FROM " + TABLE_NAME_1;
        db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteOneRecipe(String id_recipe){
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_1, _ID_1 + "=?", new String[]{id_recipe});
        if(result == -1){
            Toast.makeText(context, "Ошибка при удалении", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Удалено успешно.", Toast.LENGTH_SHORT).show();
        }
    }

    public void addFavRecipe(String image, String name, String calories, String time, String weight, String url){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(IMAGE_OF_FAV_RECIPE, image);
        cv.put(NAME_OF_FAV_RECIPE, name);
        cv.put(CALORIES, calories);
        cv.put(TIME, time);
        cv.put(WEIGHT, weight);
        cv.put(URL, url);
        long result = db.insert(TABLE_NAME_2, null, cv);
        if(result == -1){
            Toast.makeText(context, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Добавлено успешно!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public Cursor readAllDataOfFavRecipes(){
        String query = "SELECT * FROM " + TABLE_NAME_2;
        db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteOneFavRecipe(String id_recipe){
        db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME_2, _ID_2 + "=?", new String[]{id_recipe});
        if(result == -1){
            Toast.makeText(context, "Ошибка при удалении", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Удалено успешно.", Toast.LENGTH_SHORT).show();
        }
    }
}
