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
    int[][][] threeDarray = new int[8][8][8];
    private Character aCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        launchName(null);

        //reads a file and creates the appropriate background for the level
        levelCreator();

        //creates a new cube character for movement
        aCharacter = new Character(l3d);
        setArray();

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


    //Reads the file and welcomes the user back by name - eventually this method will
    //update the level and where the user saved
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

    //this method creates a level based upon file input
    public void levelCreator(){

        //creates a new instance of an L3D cube
        l3d = new A_L3D();

        //opens the file
        AssetManager assetManager = getAssets();
        InputStream input = null;

        try {
            input = assetManager.open("level1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

            //reads the input file
            Scanner in = new Scanner(new InputStreamReader(input));
        
            int yValue = 7;
            int zValue = 7;

            //for each line in the file
            while (in.hasNextLine()) {

                //creates a scanner for the line
                String line = in.nextLine();
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");

                //for each letter in the line 
                for(int i = 0; i<8; i++){
                    
                    String letter = lineScanner.next();
                    
                    //the "~" character is written in the file to indicate a new layer 
                    if(letter.equals("~")){
                        zValue--;
                        yValue = 8;
                    }
                    //the "*" character is written in the file to indicate the file has completed
                    if(letter.equals("*")){
                        return;
                    }

                    //assign each letter with a color to put in the array
                    LevelCreator levels = new LevelCreator();
                    int color = levels.colorChooser(letter);

                    //fill the array (i = xValue)
                    if(!((letter.equals("~")||letter.equals("0")))){
                        threeDarray[i][yValue][zValue] = color;
                        l3d.setVoxel(i, yValue, zValue, color);
                    }
                }
                //decrement the y Value
                yValue--;
                //update the cube
                l3d.update();
            }
        in.close();
        }






}
