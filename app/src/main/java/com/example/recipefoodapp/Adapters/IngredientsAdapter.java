package com.example.recipefoodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefoodapp.Models.ExtendedIngredient;
import com.example.recipefoodapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context, List<ExtendedIngredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_meal_ingredients, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.txtV_ingredients_name.setText(list.get(position).name);
        holder.txtV_ingredients_name.setSelected(true);
        holder.txtV_ingredients_quantity.setSelected(true);
        holder.txtV_ingredients_quantity.setText(list.get(position).original);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+
                list.get(position).image).into(holder.img_ingredients);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class IngredientsViewHolder extends RecyclerView.ViewHolder {

    TextView txtV_ingredients_quantity, txtV_ingredients_name;
    ImageView img_ingredients;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        txtV_ingredients_name = itemView.findViewById(R.id.txtV_ingredients_name);
        txtV_ingredients_quantity = itemView.findViewById(R.id.txtV_ingredients_quantity);
        img_ingredients = itemView.findViewById(R.id.img_ingredients);
    }
}
