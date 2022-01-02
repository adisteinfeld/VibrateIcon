package com.adi.vibrateicon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Intent.ACTION_LOCKED_BOOT_COMPLETED.equals(intent.getAction()) &&
            !Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            return;
        }

        System.out.println("ADIDBG: Locked boot");
        Intent serviceIntent = new Intent(context, VibrateIconService.class);
        context.startForegroundService(serviceIntent);
    }
}
