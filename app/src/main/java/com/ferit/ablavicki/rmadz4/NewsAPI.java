package com.ferit.ablavicki.rmadz4;

import retrofit2.http.GET;

public interface NewsAPI {
    @GET("rss")
    Call<RSS> getRSS();
}
