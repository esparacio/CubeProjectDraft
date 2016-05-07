package edu.elon.elena.cubeuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/*
* Elena Sparacio (c) 2016
*
* CreateNewGame allows the user to choose and color for their cube
* and name it. Currently, the name and color are written to a file
* (so they are persistent). Right now, the color feature has not
* been implemented and may be removed.
*
* */

public class CreateNewGame extends AppCompatActivity {


    private final String filename = "preferences.txt";
    private EditText cubeName;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Edit text for the name of the cube
        cubeName = (EditText) findViewById(R.id.cubeName);

        //Radio buttons for colors
        RadioButton radio = (RadioButton) findViewById(R.id.radbutton);
        RadioButton radiored = (RadioButton) findViewById(R.id.radbutton2);
        RadioButton radioblue = (RadioButton) findViewById(R.id.radbutton3);

        //OnClickListeners for colors
        radio.setOnClickListener(radio_listener);
        radiored.setOnClickListener(radio_listenerred);
        radioblue.setOnClickListener(radio_listenerblue);

    }

    //The following onClick Listeners all record the color the user chose
    private View.OnClickListener radio_listener = new View.OnClickListener() {
        public void onClick(View v) {
            ImageView i = (ImageView) findViewById(R.id.character);
            i.setImageResource(R.drawable.cubecharactergreen);
            color = "Green";

        }
    };

    private View.OnClickListener radio_listenerred = new View.OnClickListener() {
        public void onClick(View v) {
            ImageView i = (ImageView) findViewById(R.id.character);
            i.setImageResource(R.drawable.cubecharacterred);
            color = "Red";

        }
    };

    private View.OnClickListener radio_listenerblue = new View.OnClickListener() {
        public void onClick(View v) {
            ImageView i = (ImageView) findViewById(R.id.character);
            i.setImageResource(R.drawable.cubecharacterblue);
            color = "Blue";

        }
    };


    //On the button press Go! this method launches. It writes the name of the
    //cube as well as the color to a file.
    public void launchNew(View view) {

        String name = cubeName.getText().toString();
        String cubeNameandColor = name + "~" + color;

        //write to a file
        Context context = getBaseContext();
        Writer writer = null;

        try {
            OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(cubeNameandColor);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //Launch new game
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }


}
