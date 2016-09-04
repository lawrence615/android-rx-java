package com.mobidev.androidrx.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobidev.androidrx.R;
import com.mobidev.androidrx.adapter.CountiesAdapter;
import com.mobidev.androidrx.api.DummyRestClient;
import com.mobidev.androidrx.model.County;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lawrence on 9/4/16.
 */
public class ListCountiesFromServerUsingSinglesActivity extends AppCompatActivity {

    private RecyclerView rvCountiesFromServerUsingSingles;
    private ProgressBar mProgressBar;
    private TextView mErrorMessage;
    private CountiesAdapter countiesAdapter;


    private DummyRestClient service;
    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_counties_from_server_using_singles);

        service = new DummyRestClient(this);

        rvCountiesFromServerUsingSingles = (RecyclerView) findViewById(R.id.rvCountiesFromServerUsingSingles);
        mProgressBar = (ProgressBar) findViewById(R.id.loader);
        mErrorMessage = (TextView) findViewById(R.id.error_message);
        countiesAdapter = new CountiesAdapter(this);
        rvCountiesFromServerUsingSingles.setHasFixedSize(true);
        rvCountiesFromServerUsingSingles.setLayoutManager(new LinearLayoutManager(this));
        rvCountiesFromServerUsingSingles.setAdapter(countiesAdapter);


        Single<List<County>> observable = Single.fromCallable(new Callable<List<County>>() {
            @Override
            public List<County> call() throws Exception {
                return service.fetchCountiesWithException();
            }
        });


        subscription = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<County>>() {

                    @Override
                    public void onSuccess(List<County> value) {
                        displayCounties(value);
                    }

                    @Override
                    public void onError(Throwable error) {
                        displayErrorMessage(error.getMessage());
                    }
                });
    }


    private void displayCounties(List<County> counties) {
        countiesAdapter.setCounties(counties);
        mProgressBar.setVisibility(View.GONE);
        rvCountiesFromServerUsingSingles.setVisibility(View.VISIBLE);

    }


    private void displayErrorMessage(String error) {
        mProgressBar.setVisibility(View.GONE);
        mErrorMessage.setVisibility(View.VISIBLE);
        mErrorMessage.setText(error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
