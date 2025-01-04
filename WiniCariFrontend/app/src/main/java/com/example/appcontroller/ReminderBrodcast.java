package com.example.appcontroller;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBrodcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "Voilà votre tache";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyLemubit");
        builder.setSmallIcon(R.drawable.buscontroleur);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("Alerte !!");
        builder.setContentText(message);
        Intent notifyIntent = new Intent(context, navigationbar.class);
        notifyIntent.setAction("TAKE_THIS_NOTIFICATION_RIGHT_NOW");
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        );
        notifyIntent.addFlags(Intent.FLAG_FROM_BACKGROUND);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(notifyPendingIntent);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());


    }

}
