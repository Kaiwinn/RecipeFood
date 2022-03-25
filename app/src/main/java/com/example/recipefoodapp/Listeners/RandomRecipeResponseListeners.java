package com.example.recipefoodapp.Listeners;

import com.example.recipefoodapp.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListeners {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
