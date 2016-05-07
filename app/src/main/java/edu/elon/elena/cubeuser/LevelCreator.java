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

    public void createLevel(){

    }

    public int colorChooser(String aLetter){
        int color =0x000000;
        if(aLetter == null){
            return color;
        }
        switch(aLetter){
            case "B": //this is blank space. You can't jump here. If you try, you'll die
                color = 0x0000FF;
                break;
            case "P": //this is a platform! You can jump and stand on these
                color = 0xFFFF00;
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
                color = 0xFF00FF;
                break;
            case "G": //this is the end spot, reaching here means you've finished the level!
                color = 0xFF00FF;
                break;
            default:
                color = 0x000000;
                break;
        }

        return color;
    }

}


