package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    public static final String CHANNEL_ID ="project" ;
    Button sign;
    EditText vl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name=getString(R.string.NAME);
            String description=getString(R.string.channel_Description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,name,importance);
                channel.setDescription(description);

                NotificationManager notificationManager=getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);

                sign=findViewById(R.id.button);
                sign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vl=(EditText) findViewById(R.id.editText2);
                        String name=vl.getText().toString();

                        Intent intent=new Intent(MainActivity.this, displayNotifiacation.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);

                        NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("My notification")
                                .setContentText("Hello "+name)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);

                        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(MainActivity.this);
                        notificationManager.notify(0,builder.build());


                    }
                });
        }
    }



}

