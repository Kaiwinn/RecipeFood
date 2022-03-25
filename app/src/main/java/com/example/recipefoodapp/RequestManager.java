package com.example.recipefoodapp;

import android.content.Context;

import com.example.recipefoodapp.Listeners.RandomRecipeResponseListeners;
import com.example.recipefoodapp.Models.RandomRecipeApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    // in this method,
    // we'll pass an object of this RandomRecipeResponseListeners.
//we cn call this "getRandomRecipes" in MainActivity to get all data.
    public void getRandomRecipes(RandomRecipeResponseListeners listeners){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call =callRandomRecipes.CallRandomRecipe(context.getString(R.string.api_key), "10");
        // call queue
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                //i will check for
                // response is successful or not
                // if NOT
                if(!response.isSuccessful()){
                    listeners.didError(response.message());
                }
                // response is Successful
                listeners.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                // if Response is Failure
                //
                listeners.didError(t.getMessage());

            }
        });
    }

    // create the interface for random API
    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> CallRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number
        );
    }
}
