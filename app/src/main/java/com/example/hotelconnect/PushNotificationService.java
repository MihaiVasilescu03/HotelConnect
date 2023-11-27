package com.example.hotelconnect;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {

    private static final String TAG = "PushNotif";
    private static final String CHANNEL_ID = "Notificare-Camere";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        // Handle token refresh if needed
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String title, body;

        // Check if the message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            title = remoteMessage.getData().get("title");
            body = remoteMessage.getData().get("body");

            // Handle the data payload as needed. For example, update UI or perform some action.
        } else {
            // Check if the message contains a notification payload.
            if (remoteMessage.getNotification() != null) {
                title = remoteMessage.getNotification().getTitle();
                body = remoteMessage.getNotification().getBody();
            } else {
                // Neither data payload nor notification payload is present.
                return;
            }
        }

        // Display a notification.
        showNotification(title, body);
    }

    private void showNotification(String title, String body) {
        NotificationManager notificationManager = getSystemService(NotificationManager.class);

        // Create a notification channel for Android Oreo and higher.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notificari Camere",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Build the notification.
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.hclogo)
                .setAutoCancel(true);

        // Show the notification.
        notificationManager.notify(/* Notification ID */ 0, notificationBuilder.build());
    }
}
