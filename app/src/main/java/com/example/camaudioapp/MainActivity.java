    package com.example.camaudioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

        private Button btnFoto,btnVideo,btnAudio;
        private ImageView logo;
        TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            textView = findViewById(R.id.txtMultiMedia);
            logo = findViewById(R.id.imageView);
            btnFoto = findViewById(R.id.btnFotografias);
            btnVideo = findViewById(R.id.btnGrabarVideo);
            btnAudio = findViewById(R.id.GrabarAudio);

            btnFoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent photo = new Intent(MainActivity.this,PhotoActivity.class );
                    startActivity(photo);
                }
            });
            btnVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent video = new Intent(MainActivity.this, VideoActivity.class);
                    startActivity(video);
                }
            });
            btnAudio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent audio = new Intent(MainActivity.this, AudioActivity.class);
                    startActivity(audio);
                }
            });

        }
    }