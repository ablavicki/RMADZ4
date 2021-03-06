package com.ferit.ablavicki.rmadz4;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;
    private List<News> news;
    private NewsClickCallback onNewsClickCallback;

    public NewsAdapter(Context context, NewsClickCallback onNewsClickListener) {
        this.context = context;
        this.onNewsClickCallback = onNewsClickListener;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsView = inflater.inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder (newsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        News current = this.news.get(position);
        holder.tvTitle.setText(current.getTitle());
        holder.tvDescription.setText(current.getDescription());
        holder.tvCategory.setText(current.getCategory());
        holder.tvDate.setText(current.getDate());
        Picasso.get().load(current.getPoster().getUrl()).into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        if(news != null)
            return this.news.size();
        else
            return 0;
    }

    public void setList(List<News> list) {
        this.news = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPicture) ImageView ivPicture;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvDescription) TextView tvDescription;
        @BindView(R.id.tvCategory) TextView tvCategory;
        @BindView(R.id.tvDate) TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNewsClickCallback.onClick(news.get(getAdapterPosition()));
                }
            });

        }
    }
}
