package com.example.retrofit_dagger_java;

import com.example.retrofit_dagger_java.dependency.NetworkModule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface MovieOperation {

    @GET("discover/movie?")
    Call<Movie> fetchMovieList(@Query("api_key") String key,
                       @Query("sort_by") String sortBy);

    @GET("movie/{movie_id}?")
    Call<Movie> fetchMovieDetails(@Path("movie_id") long movieId,
                          @Query("api_key") String key);
}


public class MovieDataSource {

    private MovieOperation operation;

    MovieDataSource() {
        operation = NetworkModule.createService(MovieOperation.class);
        operation.fetchMovieList("", "asc");

        // Created @ first time Re-build project; without any error
        DaggerNetworkComponent.builder().build();
    }
}
