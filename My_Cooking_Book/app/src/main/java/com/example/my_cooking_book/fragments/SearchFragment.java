package com.example.my_cooking_book.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.parse.ParsingManager;

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        ParsingManager.requestRecipeData("cucumber tomato", getActivity());
        return v;
    }
}