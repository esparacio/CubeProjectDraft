package edu.elon.elena.cubeuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class YouWin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_win);

    }

    public void launchLoad(View view) {

        //get the current level and level up appropriately
        Context context = getBaseContext();
        PlayerPrefs prefs = new PlayerPrefs();
        prefs.readFile(context);
        int newLevel = prefs.getLevel() + 1;
        if(newLevel>3){
            System.out.println("You won the game!!! Automatic restart. For now.");
            prefs.setLevel(1,context);
        } else {
            prefs.setLevel(newLevel, context);
        }

        Intent intent = new Intent(this, LoadGame.class);
        startActivity(intent);
    }


}
