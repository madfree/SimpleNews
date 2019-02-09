package com.madfree.simplenews;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class ArticleDataSourceFactory extends DataSource.Factory<Integer, Article> {

    private MutableLiveData<PageKeyedDataSource<Integer, Article>> articleLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Article> create() {
        ArticleDataSource articleDataSource = new ArticleDataSource();
        articleLiveDataSource.postValue(articleDataSource);
        return articleDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Article>> getArticleLiveDataSource() {
        return articleLiveDataSource;
    }
}
