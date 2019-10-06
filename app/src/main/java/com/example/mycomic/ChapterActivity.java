package com.example.mycomic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.mycomic.Adapter.MyChapterAdapter;
import com.example.mycomic.Adapter.MySliderAdapter;
import com.example.mycomic.Common.Common;
import com.example.mycomic.Model.Banner;
import com.example.mycomic.Model.Chapter;
import com.example.mycomic.Retrofit.IComicAPI;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChapterActivity extends AppCompatActivity {

    RecyclerView rcChapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IComicAPI comicAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        comicAPI = Common.getApi();
        rcChapter = findViewById(R.id.rcChapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcChapter.setLayoutManager(layoutManager);
        rcChapter.hasFixedSize();
        fetchChapter();
    }

    private void fetchChapter() {

        compositeDisposable.add(comicAPI.getChapter(Common.ChapterID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Chapter>>() {
                    @Override
                    public void accept(List<Chapter> chapters) throws Exception {
                        rcChapter.setAdapter(new MyChapterAdapter(chapters));
                        Log.d("12312377",chapters.size()+"");
                    }
                }));
    }
}
