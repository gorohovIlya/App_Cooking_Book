package com.example.my_cooking_book.feature.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.my_cooking_book.data.db_recipes.ConstantsOfDb;
import com.example.my_cooking_book.data.db_recipes.DbHelper;

public class DbManager {
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
    }

    public  void openDb(){
        db = dbHelper.getWritableDatabase();
    }

    public ContentValues insertToDb(String name, String ingred, String wayOfPrep){
        ContentValues cv = new ContentValues();
        cv.put(ConstantsOfDb.NAME_OF_RECIPE, name);
        cv.put(ConstantsOfDb._INGRED, ingred);
        cv.put(ConstantsOfDb.WAY_OF_PREP, wayOfPrep);

        return cv;
    }

    public void DeleteFromDb (String name) {
//        return "DELETE FROM " + ConstantsOfDb.TABLE_NAME_1 +
//                " WHERE " + ConstantsOfDb.NAME_OF_RECIPE + " = " + "'" + name + "'" + ";";
        db = dbHelper.getWritableDatabase();
        db.delete(ConstantsOfDb.TABLE_NAME_1, ConstantsOfDb.NAME_OF_RECIPE + "=?", new String[]{name});
        db.close();
    }
}
