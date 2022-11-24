package com.pm.tomasinofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);

        TextView somostxt = findViewById(R.id.txt_somos);
        TextView tomasinotxt = findViewById(R.id.txt_toma);
        ImageView imagen = findViewById(R.id.imageView2);

        somostxt.setAnimation(animacion2);
        tomasinotxt.setAnimation(animacion2);
        imagen.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent =  new Intent(splash.this, Login.class);
                startActivity(intent);
                finish();
            }

        }, 4000);
    }
}