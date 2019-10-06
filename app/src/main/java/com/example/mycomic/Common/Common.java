package com.example.mycomic.Common;

import com.example.mycomic.Retrofit.IComicAPI;
import com.example.mycomic.Retrofit.RetrofitClient;

public class Common {
    public static int ChapterID ;
    public static IComicAPI getApi(){
        return RetrofitClient.getInstance().create(IComicAPI.class);
    }
}
