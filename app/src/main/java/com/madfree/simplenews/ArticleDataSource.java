package com.madfree.simplenews;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDataSource extends PageKeyedDataSource<Integer, Article> {

    public static final int PAGE_SIZE = 20;
    public static final int FIRST_PAGE = 1;
    public static final String COUNTRY = "de";
    public static final String API_KEY = BuildConfig.API_KEY;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull final LoadInitialCallback<Integer, Article> callback) {

        RetrofitClient.getsInstance()
                .getApi()
                .getNews(COUNTRY, API_KEY, FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<NewsApiResponse>() {
                    @Override
                    public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().articles, null, FIRST_PAGE +1);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer,
            Article> callback) {

        RetrofitClient.getsInstance()
                .getApi()
                .getNews(COUNTRY, API_KEY, params.key, PAGE_SIZE)
                .enqueue(new Callback<NewsApiResponse>() {
                    @Override
                    public void onResponse(Call<NewsApiResponse> call,
                                           Response<NewsApiResponse> response) {

                        Integer key = (params.key > 1) ? params.key-1 : null;

                        if (response.body() != null) {
                            callback.onResult(response.body().articles, key);
                        }

                    }

                    @Override
                    public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Article> callback) {

        RetrofitClient.getsInstance()
                .getApi()
                .getNews(COUNTRY, API_KEY, params.key, PAGE_SIZE)
                .enqueue(new Callback<NewsApiResponse>() {
                    @Override
                    public void onResponse(Call<NewsApiResponse> call,
                                           Response<NewsApiResponse> response) {
                        Integer key = (params.key == response.body().totalResults) ? params.key+1 : null;

                        if (response.body() != null) {
                            callback.onResult(response.body().articles, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsApiResponse> call, Throwable t) {

                    }
                });

    }
}
