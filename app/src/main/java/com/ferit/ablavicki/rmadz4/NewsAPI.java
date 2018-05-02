package com.ferit.ablavicki.rmadz4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsAPI {
    @GET("rss/{category}")
    Call<RSSFeed> getRSSFeed(@Path("category") String category);
}

