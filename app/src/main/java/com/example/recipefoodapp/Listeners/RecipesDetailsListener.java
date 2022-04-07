package com.example.recipefoodapp.Listeners;

import com.example.recipefoodapp.Models.RecipeDetailsResponse;

public interface RecipesDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);
}
