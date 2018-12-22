package com.example.oleg.androidacademymsk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.oleg.androidacademymsk.data.DataUtils;

public class NewsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        RecyclerView recyclerView = findViewById(R.id.newsRecycler);
        recyclerView.setBackgroundColor(0xFF_FF_FF_FF);
        recyclerView.setAdapter(new ExampleAdapter(this, 100));
        recyclerView.setLayoutManager(new CheckmateLayoutManager(this));
    }
}
