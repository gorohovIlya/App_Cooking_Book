package com.example.my_cooking_book.feature.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_cooking_book.R;
import com.example.my_cooking_book.feature.search_recipe.search.recycler.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavRecipesAdapter extends RecyclerView.Adapter<FavRecipesAdapter.ViewHolder>{

    private Context context;
    private ArrayList<String> id;
    private ArrayList<String> image;
    private ArrayList<String> name;
    private ArrayList<String> calories;
    private ArrayList<String> time;
    private ArrayList<String> weight;
    private ArrayList<String> url;
    private static OnItemClickListener mListener;

    public FavRecipesAdapter(Context context, ArrayList<String> fav_recipe_id, ArrayList<String> image, ArrayList<String> name, ArrayList<String> calories,
                             ArrayList<String> time, ArrayList<String> weight, ArrayList<String> url) {
        this.context = context;
        id = fav_recipe_id;
        this.image = image;
        this.name = name;
        this.calories = calories;
        this.time = time;
        this.weight = weight;
        this.url = url;
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
    public FavRecipesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_recipes, parent, false);
        FavRecipesAdapter.ViewHolder viewHolder = new FavRecipesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavRecipesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String imageUrl = image.get(position);
        Picasso.get().load(imageUrl).into(holder.imageRecipe);

        String label = name.get(position);
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
        return id.size();
    }

    public ArrayList<String> getId() {
        return id;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<String> getCalories() {
        return calories;
    }

    public ArrayList<String> getTime() {
        return time;
    }

    public ArrayList<String> getWeight() {
        return weight;
    }

    public ArrayList<String> getUrl() {
        return url;
    }
}
