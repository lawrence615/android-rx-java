package com.mobidev.androidrx.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.mobidev.androidrx.R;
import com.mobidev.androidrx.adapter.CountiesAdapter;
import com.mobidev.androidrx.api.DummyRestClient;
import com.mobidev.androidrx.model.County;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lawrence on 9/4/16.
 */
public class ListCountiesFromServerActivity extends AppCompatActivity {

    private RecyclerView rvCountiesFromServer;
    private ProgressBar mProgressBar;
    private CountiesAdapter countiesAdapter;


    private DummyRestClient service;
    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_counties_from_server);

        service = new DummyRestClient(this);

        rvCountiesFromServer = (RecyclerView) findViewById(R.id.rvCountiesFromServer);
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
        countiesAdapter = new CountiesAdapter(this);
        rvCountiesFromServer.setHasFixedSize(true);
        rvCountiesFromServer.setLayoutManager(new LinearLayoutManager(this));
        rvCountiesFromServer.setAdapter(countiesAdapter);


        Observable<List<County>> observable = Observable.fromCallable(new Callable<List<County>>() {
            @Override
            public List<County> call() throws Exception {
                return service.fetchCounties();
            }
        });


        subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<County>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<County> counties) {
                        displayCounties(counties);
                    }
                });


    }


    private void displayCounties(List<County> counties) {
        countiesAdapter.setCounties(counties);
        mProgressBar.setVisibility(View.GONE);
        rvCountiesFromServer.setVisibility(View.VISIBLE);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
