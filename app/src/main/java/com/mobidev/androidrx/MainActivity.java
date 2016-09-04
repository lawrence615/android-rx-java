package com.mobidev.androidrx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobidev.androidrx.adapter.ExampleAdapter;
import com.mobidev.androidrx.model.ExampleActivityAndName;
import com.mobidev.androidrx.ui.activities.ListColoursActivity;
import com.mobidev.androidrx.ui.activities.ListCountiesActivity;
import com.mobidev.androidrx.ui.activities.ListCountiesFromServerActivity;
import com.mobidev.androidrx.ui.activities.ListCountiesFromServerUsingSinglesActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ExampleAdapter(this, getExamples()));
    }


    private static List<ExampleActivityAndName> getExamples() {
        List<ExampleActivityAndName> examplesList = new ArrayList<>();
        examplesList.add(new ExampleActivityAndName(ListColoursActivity.class, "List Colours"));
        examplesList.add(new ExampleActivityAndName(ListCountiesActivity.class, "List Counties"));
        examplesList.add(new ExampleActivityAndName(ListCountiesFromServerActivity.class, "List Counties From Server"));
        examplesList.add(new ExampleActivityAndName(ListCountiesFromServerUsingSinglesActivity.class, "List Counties From Server (Singles)"));
        return examplesList;
    }
}
