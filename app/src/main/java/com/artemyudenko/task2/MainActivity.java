package com.artemyudenko.task2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

    @Override
    protected void onStart(){
        super.onStart();

        Intent previouse = getIntent();
        int location = previouse.getIntExtra("LOCATION", 0);

        if (location != 0) {
            Intent i = new Intent();
            i.putExtra("LOCATION", location);
            i.setAction(S_INTENT_FILTER + 2);
            i.addCategory(CATEGORY + 2);
            sendBroadcast(i,"com.permissions.NOTI_CLICK");
        }
    }
}
