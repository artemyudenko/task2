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

import static com.artemyudenko.task2.Constants.LOCATION;
import static com.artemyudenko.task2.Constants.NAME;

public class AddIntentReciever extends BroadcastReceiver {

    public static final String CHANNEL_ID = "myChannelId";
    public static final String CHANNEL_NAME = "myChannelName";

    @Override
    public void onReceive(Context context, Intent intent) {
         createNotificationChannel(context);
        showNotification(context, intent);
    }

    private void showNotification(Context context, Intent intent) {
        String itemName = intent.getStringExtra(NAME.getKey());
        final Resources res = context.getResources();
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("New item is added!")
                .setContentText("Item " + itemName + " is added")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(
                        R.drawable.ic_action_stat_share,
                        res.getString(R.string.action_list),
                        PendingIntent.getActivity(context,0, constructIntent(context,2), 0))
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(context,2, constructIntent(context,1), 0));

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, mBuilder.build());
    }

    private Intent constructIntent(Context context, int location) {
        Intent i = new Intent(context, MainActivity.class);
        i.putExtra(LOCATION.getKey(), location);
        return i;
    }

    private void createNotificationChannel(Context context) {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
        channel.setDescription("despacito");
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

}
