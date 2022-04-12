package com.example.recipefoodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recipefoodapp.Adapters.IngredientsAdapter;
import com.example.recipefoodapp.Listeners.RecipesDetailsListener;
import com.example.recipefoodapp.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {

    TextView txtV_meal_name, txtV_meal_source, txtV_meal_sumary;
    ImageView imgV_meal_image;
    RecyclerView recycler_meal_ingredients;
    IngredientsAdapter ingredientsAdapter;
    int id;
    LinearLayout loading;

    RequestManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        findView();

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        manager = new RequestManager(this);
        manager.getRecipeDetails(RecipeDetailsListener, id);
        loading.setVisibility(View.VISIBLE);
    }

    private void findView() {
        loading = findViewById(R.id.loading);
        txtV_meal_name = findViewById(R.id.txtV_meal_name);
        txtV_meal_source = findViewById(R.id.txtV_meal_source);
        txtV_meal_sumary = findViewById(R.id.txtV_meal_sumary);
        imgV_meal_image = findViewById(R.id.imgV_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);

    }

    private final RecipesDetailsListener RecipeDetailsListener
            = new RecipesDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            loading.setVisibility(View.INVISIBLE);
            txtV_meal_name.setText(response.title);
            txtV_meal_source.setText(response.sourceName);
            txtV_meal_sumary.setText(response.summary);
            Picasso.get().load(response.image).into(imgV_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new
                    LinearLayoutManager(RecipeDetailActivity.this,
                    LinearLayoutManager.HORIZONTAL, false));
            ingredientsAdapter = new IngredientsAdapter(
                    RecipeDetailActivity.this, response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingredientsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this,
                    message, Toast.LENGTH_SHORT).show();
        }
    };
}