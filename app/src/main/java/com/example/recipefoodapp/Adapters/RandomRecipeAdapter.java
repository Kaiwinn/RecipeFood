package com.example.recipefoodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefoodapp.Models.Recipe;
import com.example.recipefoodapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{

    Context context;
    List<Recipe> list;

    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.txv_title.setText(list.get(position).title);
        holder.txv_title.setSelected(true);
        holder.txtV_likes.setText(list.get(position).aggregateLikes+ " Likes");
        holder.txtV_servings.setText(list.get(position).servings+ " Servings");
        holder.txtV_times.setText(list.get(position).readyInMinutes+ " Minutes");

        Picasso.get().load(list.get(position).image).into(holder.img_food);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView txv_title, txtV_servings, txtV_likes, txtV_times;
    ImageView img_food;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        txv_title = itemView.findViewById(R.id.txv_title);
        txtV_servings = itemView.findViewById(R.id.txtV_servings);
        txtV_likes = itemView.findViewById(R.id.txtV_likes);
        txtV_times = itemView.findViewById(R.id.txtV_times);
        img_food = itemView.findViewById(R.id.img_food);
    }
}
