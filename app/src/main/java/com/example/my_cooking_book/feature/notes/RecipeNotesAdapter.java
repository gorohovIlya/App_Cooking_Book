package com.example.my_cooking_book.feature.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.domain.model.recipe.RecipeNote;

import java.util.ArrayList;
import java.util.LinkedList;

public class RecipeNotesAdapter extends RecyclerView.Adapter<RecipeNotesAdapter.NoteViewHolder> {

    Context context;
    LinkedList<RecipeNote> note_names;
    FragmentManager fragmentManager;

    RecipeNotesAdapter(Context context,
                       LinkedList<RecipeNote> note_names, FragmentManager fragmentManager) {
        this.context = context;
        this.note_names = note_names;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.user_recipe_item, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.note_name_txt.setText(note_names.get(position).getRecipeNote_name());
//        holder.actions_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//             NoteFragment noteFragment = new NoteFragment(note_names.get(position));
//             FragmentTransaction transaction = fragmentManager.beginTransaction();
//             transaction.replace(R.id.container, noteFragment);
//             transaction.addToBackStack(null);
//             transaction.commit();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return note_names.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView note_name_txt;
        ImageView note_image;
        Button view_note_btn;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            note_name_txt = itemView.findViewById(R.id.item_name);
            note_image = itemView.findViewById(R.id.note_img);
            view_note_btn = itemView.findViewById(R.id.view_note_btn);
        }
    }
}
