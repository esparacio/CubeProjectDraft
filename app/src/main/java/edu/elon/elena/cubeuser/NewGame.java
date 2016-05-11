package edu.elon.elena.cubeuser;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static android.os.SystemClock.sleep;


/*
* Elena Sparacio (c) 2016
*
* NewGame is the beginning of a new game. Currently, it starts an animation.
* After the animation, a fresh Gameplay screen will start with level one loaded.
* This has yet to be implemented.
* */

public class NewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        //plays the video
        final VideoView videoView = (VideoView) findViewById(R.id.clip1);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.cubetest);
         videoView.setVideoURI(video);
         videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
             public void onPrepared(MediaPlayer mp) {
                 videoView.requestFocus();
                 videoView.start();
             }
         });


    }

    public void launchLoad(View view) {
        Intent intent = new Intent(this, LoadGame.class);
        startActivity(intent);
    }
}