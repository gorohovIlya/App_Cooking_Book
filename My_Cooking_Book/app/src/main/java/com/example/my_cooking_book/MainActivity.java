package com.example.my_cooking_book;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.my_cooking_book.fragments.HomeFragment;
import com.example.my_cooking_book.fragments.NotesFragment;
import com.example.my_cooking_book.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView  bottomNavigationView;

    SearchFragment searchFragment = new SearchFragment();
    HomeFragment homeFragment = new HomeFragment();
    NotesFragment notesFragment = new NotesFragment();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
                        return true;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.notes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notesFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}