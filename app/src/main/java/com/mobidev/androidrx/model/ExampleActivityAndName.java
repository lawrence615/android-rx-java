package com.mobidev.androidrx.model;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lawrence on 9/4/16.
 */
public class ExampleActivityAndName {

    public final Class<? extends AppCompatActivity> mExampleActivityClass;
    public final String mExampleName;

    public ExampleActivityAndName(Class<? extends AppCompatActivity> mExampleActivityClass, String mExampleName) {
        this.mExampleActivityClass = mExampleActivityClass;
        this.mExampleName = mExampleName;
    }
}
