package com.madfree.simplenews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("top-headlines?apiKey={api_key}")
    Call<NewsApiResponse> getNews (
            @Query("country") String country,
            @Query("apiKey") String api_key
    );
}