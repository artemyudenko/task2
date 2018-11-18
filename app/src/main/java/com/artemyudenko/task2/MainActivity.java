package com.artemyudenko.task2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String S_INTENT_FILTER = "sharedIntent";
    public static final String CATEGORY = "sharedCat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter(S_INTENT_FILTER);
        intentFilter.addCategory(CATEGORY);
        registerReceiver(new AddIntentReciever(), intentFilter);
    }
}
