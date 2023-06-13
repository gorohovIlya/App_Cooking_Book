package com.example.my_cooking_book.feature.search_recipe.search.recycler;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.domain.model.recipe.Hits;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipesRecyclerAdapter extends RecyclerView.Adapter<RecipesRecyclerAdapter.ViewHolder>{

    private ArrayList<Hits> data;
    private static OnItemClickListener mListener;

    public RecipesRecyclerAdapter(ArrayList<Hits> data) {
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageRecipe;
        public TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageRecipe = itemView.findViewById(R.id.image_item);
            tvTitle = itemView.findViewById(R.id.label);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recipes, parent, false);
        RecipesRecyclerAdapter.ViewHolder viewHolder = new RecipesRecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String imageUrl = data.get(position).getRecipe().getImage();
        Picasso.get().load(imageUrl).into(holder.imageRecipe);

        String label = data.get(position).getRecipe().getLabel();
        holder.tvTitle.setText(label);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
