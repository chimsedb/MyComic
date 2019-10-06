package com.example.mycomic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycomic.ChapterActivity;
import com.example.mycomic.Common.Common;
import com.example.mycomic.Interface.IRecyclerOnClick;
import com.example.mycomic.Model.Comic;
import com.example.mycomic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.ViewHolder> {

    private List<Comic> comicList;
    private Context context;

    public MyComicAdapter(List<Comic> comicList, Context context) {
        this.comicList = comicList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(comicList.get(position).getImage()).into(holder.imageComic);
        holder.nameComic.setText(comicList.get(position).getName());

        holder.setiRecyclerOnClick(new IRecyclerOnClick() {
            @Override
            public void onClickItem(View view, int position) {
                Common.ChapterID = comicList.get(position).getID();

                Intent intent = new Intent(new Intent(context,ChapterActivity.class));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageComic;
        TextView nameComic;
        IRecyclerOnClick iRecyclerOnClick;

        public void setiRecyclerOnClick(IRecyclerOnClick iRecyclerOnClick) {
            this.iRecyclerOnClick = iRecyclerOnClick;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageComic = itemView.findViewById(R.id.imageComic);
            nameComic = itemView.findViewById(R.id.nameComic);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iRecyclerOnClick.onClickItem(view,getAdapterPosition());
        }
    }
}
