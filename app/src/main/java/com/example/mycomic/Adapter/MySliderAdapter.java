package com.example.mycomic.Adapter;

import android.util.Log;

import com.example.mycomic.Model.Banner;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MySliderAdapter extends SliderAdapter {

    private List<Banner> bannerList;

    public MySliderAdapter(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {
        imageSlideViewHolder.bindImageSlide(bannerList.get(position).getLink());
        for(int i=0;i<position;i++){
            Log.d("123123",bannerList.get(i).getLink()+"");
        }
    }
}
