package com.adi.vibrateicon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class RingerModeBroadcastReceiver extends BroadcastReceiver {
    public final static String CHANNEL_ID = "vibrateChannel";
    private final static int NOTIFICATION_ID = 2;

    public boolean isOnVibrate(Context context) {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audio.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE;
    }

    private void showNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_vibration_48px)
                .setContentTitle(context.getString(R.string.vibrate_notification_title))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    private void cancelNotification(Context context) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(NOTIFICATION_ID);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("ADIDBG: OnReceive");
        if (!intent.getAction().equals(AudioManager.RINGER_MODE_CHANGED_ACTION)) {
            return;
        }
        if (isOnVibrate(context)) {
            System.out.println("ADIDBG: OnReceive is on silent");
            showNotification(context);
        } else {
            System.out.println("ADIDBG: OnReceive is not on silent");
            cancelNotification(context);
        }
    }
}
