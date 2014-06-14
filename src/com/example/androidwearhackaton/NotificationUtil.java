package com.example.androidwearhackaton;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.preview.support.v4.app.NotificationManagerCompat;
import android.preview.support.wearable.notifications.WearableNotifications;
import android.support.v4.app.NotificationCompat;

public class NotificationUtil {
    
    public static void createNotification(Activity a, String title, String text){
        int notificationId = 001;
     // Build intent for notification content
     Intent viewIntent = new Intent(a, WearActivity.class);
     PendingIntent viewPendingIntent =
             PendingIntent.getActivity(a, 0, viewIntent, 0);

     NotificationCompat.Builder notificationBuilder =
             new NotificationCompat.Builder(a)
             .setSmallIcon(R.drawable.ic_launcher)
             .setContentTitle(title)
             .setContentText(text)
             .setContentIntent(viewPendingIntent);

     // Get an instance of the NotificationManager service
     NotificationManagerCompat notificationManager =
             NotificationManagerCompat.from(a);

     // Build the notification and issues it with notification manager.
     notificationManager.notify(notificationId, notificationBuilder.build());
    }
    
    public static void createWearableNotification(NotificationCompat.Builder nb){
        WearableNotifications.Builder wnb = new WearableNotifications.Builder(nb);
    }

}
