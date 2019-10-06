package com.example.mycomic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mycomic.Adapter.MyComicAdapter;
import com.example.mycomic.Adapter.MySliderAdapter;
import com.example.mycomic.Common.Common;
import com.example.mycomic.Model.Banner;
import com.example.mycomic.Model.Comic;
import com.example.mycomic.Retrofit.IComicAPI;
import com.example.mycomic.Service.PicasoImageLoadingService;

import java.util.List;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity {

    private Slider slider;
    private IComicAPI comicAPI;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RecyclerView rcComic;
    private TextView tvTitle;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        comicAPI = Common.getApi();

        slider = findViewById(R.id.slider);
        Slider.init(new PicasoImageLoadingService());

        rcComic = findViewById(R.id.rcComic);
        tvTitle = findViewById(R.id.tv_title);
        rcComic.hasFixedSize();
        rcComic.setLayoutManager(new GridLayoutManager(this, 2));

        fetchBanner();
        fetchComic();
    }

    private void fetchComic() {
        final AlertDialog dialog = new SpotsDialog.Builder().setContext(this).setMessage("Loading...").build();
        dialog.show();
        compositeDisposable.add(comicAPI.getComicList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Comic>>() {
                    @Override
                    public void accept(List<Comic> comics) throws Exception {
                        rcComic.setAdapter(new MyComicAdapter(comics,getApplicationContext()));
                        tvTitle.setText(new StringBuilder("NEWCOMIC (")
                                .append(comics.size())
                                .append(")"));
                        dialog.dismiss();
                    }
                }));
    }

    private void fetchBanner() {

        compositeDisposable.add(comicAPI.getBanerList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Banner>>() {
                    @Override
                    public void accept(List<Banner> banners) throws Exception {
                        slider.setAdapter(new MySliderAdapter(banners));
                        Log.d("123123",banners.size()+"");
                    }
                }));
    }

}
