package edu.elon.elena.cubeuser;

import android.content.Context;
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

public class Gameplay extends AppCompatActivity {

    private A_L3D l3d;
    private final String filename = "preferences.txt";
    int[][][] threeDarray = new int[8][8][8];
    private Character aCharacter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        launchName(null);
        levelCreator();
        aCharacter = new Character(l3d);
        setArray();

    }

    public void setArray(){
        aCharacter.setArray(threeDarray);
        //NEW
        //setting the start point of the game for the level - this will change if there is a saved game
        aCharacter.setxValue(0);
        aCharacter.setyValue(0);
        aCharacter.setzValue(7);
    }


    //The Goal is to cut findCharacter out so that the program runs at O(1) for each movement
    public void launchUp(View view){
        aCharacter.moveUp();

    }

    public void launchDown(View view){
        aCharacter.moveDown();

    }

    public void launchRight(View view){
        aCharacter.moveRight();

    }

    public void launchLeft(View view){
        aCharacter.moveLeft();

    }

    //Scrollable layers here (do with two buttons, eventually turn into a scrollable interface
    public void launchScrollUp(View view){
        aCharacter.scrollUp();
    }


    public void launchName(View view) {
        Context context = getBaseContext();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(filename);
            Scanner scanner = new Scanner(new InputStreamReader(in));
            scanner.useDelimiter("~");
            String name = scanner.next();
            String color = scanner.next();
            EditText e = (EditText) findViewById (R.id.editedName);
            String welcome = "Welcome back " +name;
            e.setText(welcome);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void levelCreator(){

        AssetManager assetManager = getAssets();
        InputStream input = null;
        l3d = new A_L3D();
        try {
            input = assetManager.open("level1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
            Scanner in = new Scanner(new InputStreamReader(input));
            int yval = 7;
            int level = 7;

            while (in.hasNextLine()) {
                String line = in.nextLine();
                Scanner scanny = new Scanner(line);
                scanny.useDelimiter(",");
                for(int i = 0; i<8; i++){
                    String space = scanny.next();
                    if(space.equals("~")){
                        level--;
                        yval = 8;
                    }
                    if(space.equals("*")){
                        return;
                    }
                    LevelCreator levels = new LevelCreator();
                    int color = levels.colorChooser(space);

                    if(!((space.equals("~")||space.equals("0")))){
                        threeDarray[i][yval][level] = color;
                        l3d.setVoxel(i, yval, level, color);
                    }
                }
                yval--;
                l3d.update();
            }
        in.close();
        }






}
