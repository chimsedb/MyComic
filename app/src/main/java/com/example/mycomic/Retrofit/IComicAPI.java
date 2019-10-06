package com.example.mycomic.Retrofit;

import com.example.mycomic.Model.Banner;
import com.example.mycomic.Model.Chapter;
import com.example.mycomic.Model.Comic;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IComicAPI {
    @GET("banner")
    Observable<List<Banner>> getBanerList();

    @GET("comic")
    Observable<List<Comic>> getComicList();

    @GET("chapter/{id}")
    Observable<List<Chapter>> getChapter(@Path("id") int id);
}
