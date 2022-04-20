package com.example.recipefoodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefoodapp.Models.Ingredient;
import com.example.recipefoodapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientsViewHolder>{

    Context context;
    List<Ingredient> list;

    public InstructionIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.list_instruction_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsViewHolder holder, int position) {
        holder.txtV_instruction_items.setText(list.get(position).name);
        holder.txtV_instruction_items.setSelected(true);
        holder.txtV_instruction_items.setHorizontallyScrolling(true);
        Picasso.get().load(
                "https://spoonacular.com/cdn/ingredients_100x100/"
                        + list.get(position).image)
                .into(holder.img_instructions_items);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionIngredientsViewHolder extends RecyclerView.ViewHolder {

    ImageView img_instructions_items;
    TextView txtV_instruction_items;
    public InstructionIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        img_instructions_items = itemView.findViewById(R.id.img_instructions_items);
        txtV_instruction_items = itemView.findViewById(R.id.txtV_instruction_items);
    }
}
