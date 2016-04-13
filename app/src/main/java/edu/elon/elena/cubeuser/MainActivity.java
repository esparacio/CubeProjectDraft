package edu.elon.elena.cubeuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {


    private EditText cubeName;
    private final String filename = "preferences.txt";
    private String color;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cubeName = (EditText) findViewById(R.id.cubeName);
        RadioButton radio = (RadioButton) findViewById(R.id.radbutton);
        RadioButton radiored = (RadioButton) findViewById(R.id.radbutton2);
        RadioButton radioblue = (RadioButton) findViewById(R.id.radbutton3);
        radio.setOnClickListener(radio_listener);
        radiored.setOnClickListener(radio_listenerred);
        radioblue.setOnClickListener(radio_listenerblue);




    }

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


    public void launchNew(View view) {

        String cubie = cubeName.getText().toString();
        System.out.println("Cube Name is: " +cubie + "Cube color is " +color);
        String cubeNameandColor = cubie + "~" + color;
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

        Intent intent = new Intent(this, New.class);
        startActivity(intent);
    }


}
