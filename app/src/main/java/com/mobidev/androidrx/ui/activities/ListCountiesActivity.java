package com.mobidev.androidrx.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobidev.androidrx.R;
import com.mobidev.androidrx.adapter.CountiesAdapter;
import com.mobidev.androidrx.model.County;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * Created by Lawrence on 9/4/16.
 */
public class ListCountiesActivity extends AppCompatActivity {

    private CountiesAdapter countiesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_counties);

        RecyclerView rvCounties = (RecyclerView) findViewById(R.id.rvCounties);
        countiesAdapter = new CountiesAdapter(this);
        rvCounties.setHasFixedSize(true);
        rvCounties.setLayoutManager(new LinearLayoutManager(this));
        rvCounties.setAdapter(countiesAdapter);

        Observable<List<County>> listObservable = Observable.just(getCountiesList());
        listObservable.subscribe(new Observer<List<County>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<County> counties) {
                countiesAdapter.setCounties(counties);
            }
        });
    }


    private static List<County> getCountiesList() {
        List<County> counties = new ArrayList<>();
        counties.add(new County(1, "Mombasa"));
        counties.add(new County(2, "Kwale"));
        counties.add(new County(3, "Kilifi"));
        counties.add(new County(4, "Tana River"));
        counties.add(new County(5, "Lamu"));
        counties.add(new County(6, "Taita-Taveta"));
        counties.add(new County(7, "Garissa"));
        counties.add(new County(8, "Wajir"));
        counties.add(new County(9, "Mandera"));
        counties.add(new County(10, "Marsabit"));
        counties.add(new County(11, "Isiolo"));
        counties.add(new County(12, "Meru"));
        counties.add(new County(13, "Tharaka-Nithi"));
        counties.add(new County(14, "Embu"));
        counties.add(new County(15, "Kitui"));
        counties.add(new County(16, "Machakos"));
        counties.add(new County(17, "Makueni"));
        counties.add(new County(18, "Nyandarua"));
        counties.add(new County(19, "Nyeri"));
        counties.add(new County(20, "Kirinyaga"));
        counties.add(new County(21, "Murang'a"));
        counties.add(new County(22, "Kiambu"));
        counties.add(new County(23, "Turkana"));
        counties.add(new County(24, "West Pokot"));
        counties.add(new County(25, "Samburu"));
        counties.add(new County(26, "Trans-Nzoia"));
        counties.add(new County(27, "Uasin Gishu"));
        counties.add(new County(28, "Elgeyo-Marakwet"));
        counties.add(new County(29, "Nandi"));
        counties.add(new County(30, "Baringo"));
        counties.add(new County(31, "Laikipia"));
        counties.add(new County(32, "Nakuru"));
        counties.add(new County(33, "Narok"));
        counties.add(new County(34, "Kajiado"));
        counties.add(new County(35, "Kericho"));
        counties.add(new County(36, "Bomet"));
        counties.add(new County(37, "Kakamega"));
        counties.add(new County(38, "Vihiga"));
        counties.add(new County(39, "Bungoma"));
        counties.add(new County(40, "Busia"));
        counties.add(new County(41, "Siaya"));
        counties.add(new County(42, "Kisumu"));
        counties.add(new County(43, "Homa Bay"));
        counties.add(new County(44, "Migori"));
        counties.add(new County(45, "Kisii"));
        counties.add(new County(46, "Nyamira"));
        counties.add(new County(47, "Nairobi"));
        return counties;
    }
}
