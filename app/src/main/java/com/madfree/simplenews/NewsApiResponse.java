package com.madfree.simplenews;

import java.util.List;

public class NewsApiResponse {
    String status;
    int totalResults;
    List<Article> articles;
}

class Source {
    public String id;
    public String name;

}

class Article {
    public Source source;
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String pusblishedAt;
    public String content;
}
