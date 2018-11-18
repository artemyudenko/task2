package com.artemyudenko.task2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class AddIntentReciever extends BroadcastReceiver {

    public static final String CHANNEL_ID = "myChannelId";
    public static final String CHANNEL_NAME = "myChannelName";

    @Override
    public void onReceive(Context context, Intent intent) {
        String itemName = intent.getStringExtra("NAME");
        createNotificationChannel(context);
        showNotification(context, itemName);
    }

    private void showNotification(Context context, String itemName) {
        final Resources res = context.getResources();

        Intent mainMenuIntent = new Intent(context, MainActivity.class);
        mainMenuIntent.putExtra("LOCATION", 1);

        Intent edit = new Intent(context, MainActivity.class);
        edit.putExtra("LOCATION", 2);

        Intent list = new Intent(context, MainActivity.class);
        list.putExtra("LOCATION", 3);

        PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, mainMenuIntent, 0);
        PendingIntent pendingIntentEdit = PendingIntent.getActivity(context, 0, edit, 0);
        PendingIntent pendingIntentList = PendingIntent.getActivity(context, 0, list, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("New item is added!")
                .setContentText("Item " + itemName + " is added")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(
                        R.drawable.ic_action_stat_share,
                        res.getString(R.string.action_list),
                        pendingIntentList)
                .addAction(
                        R.drawable.ic_action_stat_reply,
                        res.getString(R.string.action_edit),
                        pendingIntentEdit)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent1);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, mBuilder.build());
    }

    private void createNotificationChannel(Context context) {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
        channel.setDescription("despacito");
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
