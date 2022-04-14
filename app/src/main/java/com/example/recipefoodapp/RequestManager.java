package com.example.recipefoodapp;

import android.content.Context;

import com.example.recipefoodapp.Listeners.RandomRecipeResponseListeners;
import com.example.recipefoodapp.Listeners.RecipesDetailsListener;
import com.example.recipefoodapp.Listeners.SimilarRecipeListener;
import com.example.recipefoodapp.Models.RandomRecipeApiResponse;
import com.example.recipefoodapp.Models.RecipeDetailsResponse;
import com.example.recipefoodapp.Models.SimilarRecipeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
    public void getRandomRecipes(RandomRecipeResponseListeners listeners, List<String> tags){

// call all RandomRecipes
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
// create 1 Call Object
        Call<RandomRecipeApiResponse> call =callRandomRecipes
                .CallRandomRecipe(context.getString(R.string.api_key), "10", tags);
        // call queue
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call,
                                   Response<RandomRecipeApiResponse> response) {
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

    public void getRecipeDetails(RecipesDetailsListener listener, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        // retrofit is load data form api to CallRecipeDetails.class
//Call is send request Response
        Call<RecipeDetailsResponse> call =
                callRecipeDetails.callRecipeDetails(
                        id, context.getString(R.string.api_key));

        // enqueue is Returns true if this call has been either executed or enqueued.
        // It is an error to execute or enqueue a call more than once.
        call.enqueue(new Callback<RecipeDetailsResponse>() {
            // Callback called for a reponse of HTTP.
            @Override
            public void onResponse(Call<RecipeDetailsResponse> call, Response<RecipeDetailsResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());

            }

            @Override
            public void onFailure(Call<RecipeDetailsResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getSimilarRecipes(SimilarRecipeListener listener, int id){

    }

    // create the interface for random API
    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> CallRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags")List<String> tags
                );
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    private interface CallSimilarRecipes{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipeResponse>> callSimilarRecipes(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey

        );
    }
}
