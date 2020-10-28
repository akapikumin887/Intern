package com.codedynamix.pottyari.System;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.codedynamix.pottyari.Activity.GameActivity;
import com.codedynamix.pottyari.R;

public class StService extends Service
{
    @Override
    public void onCreate()
    {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        int requestCode = intent.getIntExtra("REQUEST_CODE", 0);

        String channelId = "default";
        String title = GameActivity.getCntxt().getString(R.string.app_name);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(GameActivity.getCntxt(), requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager =
                (NotificationManager) GameActivity.getCntxt().getSystemService(Context.NOTIFICATION_SERVICE);

        // Notification　Channel 設定
        NotificationChannel channel = new NotificationChannel(
                channelId, title, NotificationManager.IMPORTANCE_DEFAULT);

        if (notificationManager != null)
        {
            notificationManager.createNotificationChannel(channel);
            Notification notification = new Notification.Builder(GameActivity.getCntxt(), channelId)
                    .setContentTitle(title)
                    // android標準アイコンから
                    .setSmallIcon(android.R.drawable.ic_media_play)
                    .setContentText("歩数計測中...")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .build();

            //サービス開始
            startForeground(1, notification);
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        //service終了
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
