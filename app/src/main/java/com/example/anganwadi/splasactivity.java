package com.example.anganwadi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class splasactivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splasactivity);
        handler=new Handler();
      handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(splasactivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }
    public void zoom(View view){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.zoom);
        image.startAnimation(animation1);
    }

}
