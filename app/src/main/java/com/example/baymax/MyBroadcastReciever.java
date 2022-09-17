package com.example.baymax;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;

import androidx.annotation.RequiresApi;

public class MyBroadcastReciever extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator=(Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);
        Notification notify = null;

        PendingIntent contentIntent = PendingIntent.getActivity(context, 24444,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            noti = new Notification.Builder(context)
                    .setContentTitle("Alarm is On")
                    .setContentText("You've set up the Alarm")
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        }*/
        notify = new Notification.Builder(context)
                .setContentTitle("Alarm is On")
                .setContentText("You've set up the Alarm")
                .setFullScreenIntent(contentIntent,true)
                .setSmallIcon(R.mipmap.ic_launcher).build();
        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notify.flags =Notification.FLAG_AUTO_CANCEL ;
        manager.notify(0,notify);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        Ringtone r=RingtoneManager.getRingtone(context,notification);
        r.play();



    }
}

