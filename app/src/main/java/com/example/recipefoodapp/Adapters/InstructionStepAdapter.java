package com.example.recipefoodapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipefoodapp.Models.Step;
import com.example.recipefoodapp.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder>{

    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.list_instruction_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {
        holder.txtV_instruction_step_number.setText(String.valueOf(list.get(position).number));
        holder.txtV_instruction_title.setText(list.get(position).step);

        holder.recyvler_instruction_ingredients.setHasFixedSize(true);
        holder.recyvler_instruction_ingredients
                .setLayoutManager(new LinearLayoutManager(
                        context, LinearLayoutManager.HORIZONTAL, false
                ));
        InstructionIngredientsAdapter instructionIngredientsAdapter
                = new InstructionIngredientsAdapter(context, list.get(position).ingredients);
        holder.recyvler_instruction_ingredients.setAdapter(instructionIngredientsAdapter);

        holder.recyvler_instruction_equipments.setHasFixedSize(true);
        holder.recyvler_instruction_equipments
                .setLayoutManager(new LinearLayoutManager(
                        context, LinearLayoutManager.HORIZONTAL, false
                ));
        InstructionEquipmentsAdapter instructionEquipmentsAdapter
                = new InstructionEquipmentsAdapter(context, list.get(position).equipment);
        holder.recyvler_instruction_equipments.setAdapter(instructionEquipmentsAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionStepViewHolder extends RecyclerView.ViewHolder {
    TextView txtV_instruction_step_number, txtV_instruction_title;
    RecyclerView recyvler_instruction_equipments, recyvler_instruction_ingredients;
    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        txtV_instruction_step_number = itemView.findViewById(R.id.txtV_instruction_step_number);
        txtV_instruction_title = itemView.findViewById(R.id.txtV_instruction_title);
        recyvler_instruction_equipments = itemView.findViewById(R.id.recyvler_instruction_equipments);
        recyvler_instruction_ingredients = itemView.findViewById(R.id.recyvler_instruction_ingredients);
    }
}

