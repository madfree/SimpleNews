package com.madfree.simplenews;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class ArticleViewModel extends ViewModel {

    LiveData<PagedList<Article>> articlePagedList;
    LiveData<PageKeyedDataSource<Integer, Article>> liveDataSource;

    public ArticleViewModel () {

        ArticleDataSourceFactory articleDataSourceFactory = new ArticleDataSourceFactory();
        liveDataSource = articleDataSourceFactory.getArticleLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ArticleDataSource.PAGE_SIZE)
                        .build();

        articlePagedList = (new LivePagedListBuilder(articleDataSourceFactory, config)).build();

    }
}
