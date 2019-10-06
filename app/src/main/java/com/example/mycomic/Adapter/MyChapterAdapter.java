package com.example.mycomic.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycomic.Model.Chapter;
import com.example.mycomic.R;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.ViewHolder> {

    List<Chapter> chapterArrayList;

    public MyChapterAdapter(List<Chapter> chapterArrayList) {
        this.chapterArrayList = chapterArrayList;
    }

    @NonNull
    @Override
    public MyChapterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyChapterAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(chapterArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return chapterArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title);
        }
    }
}
