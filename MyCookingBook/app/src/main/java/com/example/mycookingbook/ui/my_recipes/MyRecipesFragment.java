package com.example.mycookingbook.ui.my_recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mycookingbook.databinding.FragmentMyRecipesBinding;

public class MyRecipesFragment extends Fragment {

    private FragmentMyRecipesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyRecipesViewModel myRecipesViewModel =
                new ViewModelProvider(this).get(MyRecipesViewModel.class);

        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyRecipes;
        myRecipesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}