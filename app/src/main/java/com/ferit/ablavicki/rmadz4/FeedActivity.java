package com.ferit.ablavicki.rmadz4;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.btnRefresh)
    Button btnRefresh;
    @BindView(R.id.spinner)
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        setupRV();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

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
        public static final String VIEW_NEWS_FAIL = "View in browser fail";

        @Override
        public void onClick(News news) {
            Intent implicitIntent = new Intent();
            Uri uri = Uri.parse(news.getLink());
            implicitIntent.setData(uri);
            implicitIntent.setAction(Intent.ACTION_VIEW);
            if(canBeCalled(implicitIntent)){
                startActivity(implicitIntent);
            }
            else{
                Log.i(VIEW_NEWS_FAIL, "No browser");
            }

        }
    };

    private boolean canBeCalled(Intent implicitIntent) {
        PackageManager packageManager = this.getPackageManager();
        if(implicitIntent.resolveActivity(packageManager) != null) return true;
        else return false;
    }

    private void getRSSFeed() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_API)
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        NewsAPI api = retrofit.create(NewsAPI.class);
        Call<RSSFeed> call = api.getRSSFeed(spinner.getSelectedItem().toString());
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

    @OnClick(R.id.btnRefresh)
    public void refreshFeed(){
        getRSSFeed();
    }

}
