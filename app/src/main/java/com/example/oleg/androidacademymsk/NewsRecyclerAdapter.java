package com.example.oleg.androidacademymsk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.example.oleg.androidacademymsk.data.NewsItem;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<NewsItem> newsItemList;
    private final Context context;

    public NewsRecyclerAdapter(Context context, List<NewsItem> newsItemList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.newsItemList = newsItemList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bind(newsItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView categoryTV;
        private final TextView headerTV;
        private final TextView contentTV;
        private final TextView dateTV;
        private NewsItem newsItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //TODO: BLED
            categoryTV =  itemView.findViewById(R.id.categoryTV);
            headerTV = itemView.findViewById(R.id.titleTV);
            contentTV = itemView.findViewById(R.id.contentTV);
            dateTV = itemView.findViewById(R.id.dateTimeTV);
        }

        public static ViewHolder create(ViewGroup parent) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_item, parent, false));

        }

        public void bind(NewsItem newsItem) {
            this.newsItem = newsItem;
            categoryTV.setText(newsItem.getCategory().getName());
            headerTV.setText(newsItem.getTitle());
            contentTV.setText(newsItem.getPreviewText());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy, EEE", Locale.getDefault());
            dateTV.setText(simpleDateFormat.format(newsItem.getPublishDate().getTime()));
        }
    }
}
