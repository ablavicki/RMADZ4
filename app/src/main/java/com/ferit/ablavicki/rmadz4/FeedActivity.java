package com.ferit.ablavicki.rmadz4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedActivity extends AppCompatActivity {

    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        setupRV();
    }

    public void setupRV(){

        LinearLayoutManager linearLayout =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration divider =
                new DividerItemDecoration(this, linearLayout.getOrientation());
        NewsAdapter adapter =
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


}
