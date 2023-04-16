package com.example.camaudioapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    private static final int VideoRecordPermissionCode = 30;
    ActivityResultLauncher<Intent> activitytLauncher;
    VideoView viewVid,videoView;
    Button buttonGrabarVideo, buttonCompartirVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.VideoViewr);
        buttonGrabarVideo = findViewById(R.id.btnVideoRecord);
        buttonCompartirVideo = findViewById(R.id.btnShareVid);
        if(isCameraPresent()){
            Log.i("Camera phone", "Camara detectada");
            cameraPermissions();
        }else{
            Log.i("Camera phone", "Camara no detectada");
        }

        buttonGrabarVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!= null){
                    activitytLauncher.launch(intent);

                }else{
                    Toast.makeText(VideoActivity.this, "Accion no soportada", Toast.LENGTH_SHORT).show();
                }

            }
        });



        VideoView videoView = findViewById(R.id.VideoViewr);
       String videoPath = "android.resource://"+ getPackageName() + "/" + R.raw.jerusalema;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    private void cameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    VideoRecordPermissionCode);
        }
    }

    private boolean isCameraPresent() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    };
}