package com.pseudolab.coursera_daily_selfie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

/**
 * Created by Geronimo on 5/6/2015.
 */
public class NotificationReceiver extends BroadcastReceiver {
    private Intent selfieIntent;
    private PendingIntent pendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        selfieIntent = new Intent(context,MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context,0,selfieIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentText("Time for another selfie")
                .setContentTitle("Daily Selfie")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1,notificationBuilder.build());
    }
}
