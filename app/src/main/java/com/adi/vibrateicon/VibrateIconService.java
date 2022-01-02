package com.adi.vibrateicon;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class VibrateIconService extends Service {

    public final static String CHANNEL_ID = "serviceChannel";
    private final static int NOTIFICATION_ID = 1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.alpha_n_box)
                .setContentTitle(getString(R.string.persistent_notification_title))
                .setContentText(getString(R.string.persistent_notification_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true);

        startForeground(NOTIFICATION_ID, builder.build());
        registerBroadCastReceiver();
    }

    private void registerBroadCastReceiver() {
        BroadcastReceiver br = new RingerModeBroadcastReceiver();
        IntentFilter filter = new IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION);
        this.registerReceiver(br, filter);
    }


    private void createNotificationChannels() {
        createNotificationChannel(
                getString(R.string.persistent_channel_name),
                getString(R.string.persistent_channel_description),
                VibrateIconService.CHANNEL_ID);
        createNotificationChannel(
                getString(R.string.vibrate_channel_name),
                getString(R.string.vibrate_channel_description),
                RingerModeBroadcastReceiver.CHANNEL_ID);
    }

    private void createNotificationChannel(String channelName, String channelDescription, String id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(id, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
