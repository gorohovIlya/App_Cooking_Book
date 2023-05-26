package com.example.my_cooking_book.feature.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.databinding.FragmentHomeBinding;
import com.example.my_cooking_book.feature.notes.CreatingRecipeFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        binding.btnInfoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                InfoFragment infoFragment = new InfoFragment();
                transaction.replace(R.id.container, infoFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }
}