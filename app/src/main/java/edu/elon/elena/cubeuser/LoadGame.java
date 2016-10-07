package edu.elon.elena.cubeuser;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
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
import java.util.Scanner;

import static android.os.SystemClock.sleep;

/*
* Elena Sparacio (c) 2016
*
* LoadGame loads a previous file and is where all of the gameplay
* action begins. Players can move around the cube by button press.
*
* */

public class LoadGame extends AppCompatActivity {

    private A_L3D l3d;
    private final String filename = "preferences.txt";
    private int level;
    int[][][] threeDarray = new int[8][8][8];
    private Character aCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        //get the player preferences
        PlayerPrefs prefs = new PlayerPrefs();
        Context context = getBaseContext();
        prefs.readFile(context);
        level = prefs.getLevel();

        //welcome the player back
        EditText welcomeUser = (EditText) findViewById(R.id.editedName);
        welcomeUser.setText("Welcome back " + prefs.getUserName());

        //reads a file and creates the appropriate background for the level
        LevelCreator creator = new LevelCreator();
        l3d = new A_L3D();

        //create the level
        AssetManager assetManager = getAssets();
        threeDarray = creator.loadLevel(l3d, assetManager,level);

        //creates a new cube character for movement
        aCharacter = new Character(l3d,context);
        setArray();

    }

    public void startOver(){
        Intent intent = new Intent(this, YouDied.class);
        startActivity(intent);
    }

    //Sets an array in the character class once the level is read
    public void setArray(){
        aCharacter.setArray(threeDarray);
        //setting the start point of the game for the level - this will change if there is a saved game
        aCharacter.setxValue(0);
        aCharacter.setyValue(0);
        aCharacter.setzValue(7);
    }

    //On button press, this moves the cube character up
    public void launchUp(View view){
        aCharacter.moveUp();

    }

    //On button press, this moves the cube character down
    public void launchDown(View view){
        aCharacter.moveDown();

    }

    //On button press, this moves the cube character right
    public void launchRight(View view){
        aCharacter.moveRight();

    }

    //On button press, this moves the cube character left
    public void launchLeft(View view){
        aCharacter.moveLeft();

    }

    //Allows the player to scroll forward through the layers
    public void launchScrollUp(View view){
        aCharacter.scrollUp();

    }


}
