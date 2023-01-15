package com.example.my_cooking_book;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView cookPhoto;
    Button searchBtn, myrecipesBtn, quitBtn;
    Resources resources;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cookPhoto = findViewById(R.id.cook_img);
        searchBtn = findViewById(R.id.search_btn);
        myrecipesBtn = findViewById(R.id.my_recipes_btn);
        quitBtn = findViewById(R.id.quit_btn);

        resources = getResources();
        cookPhoto.setImageResource(R.drawable.cooking_photo);
    }
}