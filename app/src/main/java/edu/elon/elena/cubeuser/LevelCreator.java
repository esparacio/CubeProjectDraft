package edu.elon.elena.cubeuser;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by ElenaSparacio on 4/1/16.
 */
public class LevelCreator {

    public int[][][] loadLevel(A_L3D l3d, AssetManager assetManager, int level){

        int[][][] threeDarray = new int[8][8][8];

        //opens the file
        InputStream input = null;

        try {
            String levelFile = "level" + level + ".txt";
            input = assetManager.open(levelFile);

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
                    return threeDarray;
                }

                //assign each letter with a color to put in the array
                int color = colorChooser(letter);

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

        return threeDarray;
    }


    public int colorChooser(String aLetter){
        int color =0x000000;
        if(aLetter == null){
            return color;
        }
        switch(aLetter){
            case "B": //this is blank space. You can't jump here. If you try, you'll die
                color = 0x5A5A5A;
                break;
            case "P": //this is a platform! You can jump and stand on these
               // color = 0x062989;
                color = 0x0a1552;
                break;
            case "A": //Teleporter start 1
                color = 0x56CC27;
                break;
            case "D": //Teleporter end 1
                color = 0x56CC26;
                break;
            case "F": //Teleporter start 2
                color = 0x56CC24;
                break;
            case "E": //Teleporter end 2
                color = 0x56CC25;
                break;
            case "S": //this is the starting point of the game
                color = 0xFF0000;
                break;
            case "C": //this is a challenge. Landing on this spot should send you to a challenge on mobile
                color = 0x342d2d;
                break;
            case "G": //this is the end spot, reaching here means you've finished the level!
                color = 0x4f1212;
                break;
            default:
                color = 0x000000;
                break;
        }

        return color;
    }

}


