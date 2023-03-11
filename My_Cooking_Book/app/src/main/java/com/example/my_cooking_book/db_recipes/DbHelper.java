package com.example.my_cooking_book.db_recipes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, ConstantsOfDb.DB_NAME, null, ConstantsOfDb.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantsOfDb.TABLE_1_STRUCTURE);
        db.execSQL(ConstantsOfDb.TABLE_2_STRUCTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ConstantsOfDb.DROP_TABLE);
        onCreate(db);
    }
}
