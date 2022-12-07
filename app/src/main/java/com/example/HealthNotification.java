package com.example;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.anganwadi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class HealthNotification extends AppCompatActivity {
    Button submit;
    EditText hsentby,htitle,hdate,htime,hvenue;
    AlertDialog.Builder builder;

    DatabaseReference databaseReference;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_notification);
        submit=findViewById(R.id.submitbutton);
        hsentby=findViewById(R.id.sentbY);
        htitle=findViewById(R.id.title);
        hdate=findViewById(R.id.datE);
        htime=findViewById(R.id.time);
        hvenue=findViewById(R.id.venue);


        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://anganwadiautomation-default-rtdb.firebaseio.com/");
        builder = new AlertDialog.Builder(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hsentbyStr = hsentby.getText().toString();
                String htitleStr = htitle.getText().toString();
                String hdateStr = hdate.getText().toString();
                String htimeStr = htime.getText().toString();
                String hvenueStr = hvenue.getText().toString();


                if(hsentbyStr.isEmpty()||htitleStr.isEmpty()||hdateStr.isEmpty()||htimeStr.isEmpty()||hvenueStr.isEmpty()){

                }else{
                    databaseReference.child("PANCHAYATH NOTIFICATION").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            databaseReference.child("PANCHAYATH NOTIFICATION").child(htitleStr).child("Title").setValue(htitleStr);
                            databaseReference.child("PANCHAYATH NOTIFICATION").child(htitleStr).child("Sentby").setValue(hsentbyStr);
                            databaseReference.child("PANCHAYATH NOTIFICATION").child(htitleStr).child("Date").setValue(hdateStr);
                            databaseReference.child("PANCHAYATH NOTIFICATION").child(htitleStr).child("Time").setValue(htimeStr);
                            databaseReference.child("PANCHAYATH NOTIFICATION").child(htitleStr).child("Venue").setValue(hvenueStr);

                            builder.setMessage("Notification Sended to the  Teachers,Users,Panchayath,Cdpo,supervisors")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent i = new Intent(com.example.HealthNotification.this,healthinspectorhomepagefrag.class);
                                            startActivity(i);
                                        }
                                    });

                            //Creating dialog box
                            AlertDialog alert = builder.create();
                            //Setting the title manually
                            alert.setTitle("Notification");
                            alert.show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                        }




                        });
                    }
                initChannels(getApplicationContext());
            }
            private void initChannels(Context context) {
                int notificationId= new Random().nextInt(100);
                String channelId="notification_channel_1";


                NotificationManager notificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Intent notificationIntent = new Intent(getApplicationContext(), Healthnotifyxmljava.class);
                notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                notificationIntent.putExtra("message", "This is a notification message");
                PendingIntent pendingIntent= PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                NotificationCompat.Builder builder=new NotificationCompat.Builder(
                        getApplicationContext(),channelId
                );
                builder.setSmallIcon(R.drawable.ic_baseline_keyboard_arrow_left_24);
                builder.setDefaults(NotificationCompat.DEFAULT_ALL);
                builder.setContentTitle("Notification?");
                builder.setContentText("Message");
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                builder.setPriority(NotificationCompat.PRIORITY_MAX);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    if (notificationManager!=null && notificationManager.getNotificationChannel(channelId)==null){
                        NotificationChannel notificationChannel=new NotificationChannel(channelId,"Notification channel 1"
                                ,NotificationManager.IMPORTANCE_HIGH);
                        notificationChannel.setDescription("This notification channel is used to notify user.");
                        notificationChannel.enableVibration(true);
                        notificationChannel.enableLights(true);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                }
                Notification notification=builder.build();
                if (notificationManager != null){
                    notificationManager.notify(notificationId,notification);
                }



            }

        });
    }
}
