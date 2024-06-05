package com.rarmash.prac11;

import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.app.NotificationChannel;
import android.widget.Button;
import android.os.Build;
import android.app.PendingIntent;
import android.app.AlarmManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "delayed_channel";
    private MediaPlayer mediaPlayer;
    Button webViewButton;
    Button musicButton;
    Button uiButon;
    Button rNotificationsButton;
    Button dNotificationsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webViewButton = findViewById(R.id.webviewBTn);
        musicButton = findViewById(R.id.musicBtn);
        uiButon = findViewById(R.id.uiBtn);
        rNotificationsButton = findViewById(R.id.rNotificationsBtn);
        dNotificationsButton = findViewById(R.id.dNotificationsBtn);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://rus.hitmotop.com/get/music/20181205/Fever_Ray_-_If_I_Had_A_Heart_60748384.mp3");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


        webViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                } else {
                    mediaPlayer.pause();
                }
            }
        });

        uiButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });


        createNotificationChannel();
        rNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.baseline_circle_notifications_24)
                        .setContentTitle("Example Notification").setContentText("This is a test notification")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                NotificationManager notificationManager = getSystemService(NotificationManager.class);
                notificationManager.notify(1, builder.build());
            }
        });
        dNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleNotification(10000); // 10 секунд
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Delayed Notifications";
            String description = "Channel for delayed example notifications";
            int importance =
                    NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            notificationManager.createNotificationChannel(channel);
        }
    }

    private void scheduleNotification(long delay) {
        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager)
                getSystemService(ALARM_SERVICE);
        long futureInMillis = System.currentTimeMillis() + delay;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent);
    }
}