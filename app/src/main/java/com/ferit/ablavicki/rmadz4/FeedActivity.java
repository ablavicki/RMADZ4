package com.ferit.ablavicki.rmadz4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class FeedActivity extends AppCompatActivity implements Callback<RSSFeed>{

    private static final String URL_API = "http://www.bug.hr/";
    NewsAdapter adapter;
    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        setupRV();

        getRSSFeed();
    }

    public void setupRV(){
        LinearLayoutManager linearLayout =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration divider =
                new DividerItemDecoration(this, linearLayout.getOrientation());
        adapter =
                new NewsAdapter(this, mOnNewsClickListener);

        rvFeed.setLayoutManager(linearLayout);
        rvFeed.addItemDecoration(divider);
        rvFeed.setAdapter(adapter);
    }

    private NewsClickCallback mOnNewsClickListener = new NewsClickCallback(){
        @Override
        public void onClick(News news) {
            String message = news.getTitle();
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    };

    private void getRSSFeed() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_API)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        NewsAPI api = retrofit.create(NewsAPI.class);
        Call<RSSFeed> call = api.getRSSFeed("mobiteli");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
        List<News> list = response.body().getChannel().getNewsList();
        adapter.setList(list);
    }

    @Override
    public void onFailure(Call<RSSFeed> call, Throwable t) {
        Log.i("Fail", t.getMessage());
    }

}
