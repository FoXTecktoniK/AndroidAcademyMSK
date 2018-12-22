package com.example.oleg.androidacademymsk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {

    private Context mContext;
    private int size;

    public ExampleAdapter(Context context, int size) {
        mContext = context;
        this.size = size;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView indexTextView;
        ConstraintLayout constraintLayout;
        static int[] colors = {0xFF00FF00, 0xFF4682b4, 0xFF0095b6, 0xFF158078, 0xFF015d52, 0xFF00541f, 0xFF507d2a, 0xFFcc7722, 0xFFc35831, 0xFFcc0605, 0xFFff8c00, 0xFFff2400, 0xFFf8173e};

        public ViewHolder(View itemView) {
            super(itemView);

            constraintLayout = itemView.findViewById(R.id.constraint_layout);
            indexTextView = itemView.findViewById(R.id.index_text_view);
        }

        public static ViewHolder create(ViewGroup parent) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.example_layout, parent, false));
        }

        public void bind(int i) {
            indexTextView.setText(Integer.toString(i));
            constraintLayout.setBackgroundColor(getColor(i));
        }

        private static int getColor(int i) {
            return colors[i % colors.length];
        }

    }
}
