package com.example.my_cooking_book.db_recipes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }
    public void openDb(){
        db = dbHelper.getWritableDatabase();
    }
    public void insertToDb(String name, String ingred, String wayOfPrep){
        ContentValues cv = new ContentValues();
        cv.put(ConstantsOfDb.NAME_OF_RECIPE, name);
        cv.put(ConstantsOfDb._INGRED, ingred);
        cv.put(ConstantsOfDb.WAY_OF_PREP, wayOfPrep);
    }
}
