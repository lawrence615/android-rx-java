package com.mobidev.androidrx.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobidev.androidrx.R;
import com.mobidev.androidrx.adapter.SimpleStringAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * Created by Lawrence on 9/4/16.
 */
public class ListColoursActivity extends AppCompatActivity {

    private SimpleStringAdapter stringAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_colours);


        RecyclerView rvColours = (RecyclerView) findViewById(R.id.rvColours);
        stringAdapter = new SimpleStringAdapter(this);
        rvColours.setHasFixedSize(true);
        rvColours.setLayoutManager(new LinearLayoutManager(this));
        rvColours.setAdapter(stringAdapter);


        Observable<List<String>> listObservable = Observable.just(getColourList());
        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                System.err.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> strings) {
                System.err.println("onNext");
                stringAdapter.setStrings(strings);
            }
        });
    }


    private static List<String> getColourList() {
        ArrayList<String> colours = new ArrayList<>();
        colours.add("blue");
        colours.add("green");
        colours.add("red");
        colours.add("chartreuse");
        colours.add("Van Dyke Brown");
        return colours;
    }
}
