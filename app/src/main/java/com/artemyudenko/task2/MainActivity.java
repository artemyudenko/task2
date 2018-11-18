package com.artemyudenko.task2;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static com.artemyudenko.task2.Constants.LOCATION;

public class MainActivity extends AppCompatActivity {

    private AddIntentReciever addIntentReciever = new AddIntentReciever();
    private static final String S_INTENT_FILTER = "sharedIntent";
    private static final String CATEGORY = "sharedCat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter(S_INTENT_FILTER);
        intentFilter.addCategory(CATEGORY);
        registerReceiver(addIntentReciever, intentFilter);
    }

    @Override
    protected void onStart(){
        super.onStart();

        Intent previouse = getIntent();
        int location = previouse.getIntExtra(LOCATION.getKey(), 0);
        if (location != 0) {
            Intent i = new Intent();
            i.putExtra(LOCATION.getKey(), location);
            i.setAction(S_INTENT_FILTER + 2);
            i.addCategory(CATEGORY + 2);
            sendBroadcast(i,"com.permissions.NOTI_CLICK");
        }
    }
}
