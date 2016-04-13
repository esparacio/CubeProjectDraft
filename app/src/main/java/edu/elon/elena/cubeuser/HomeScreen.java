package edu.elon.elena.cubeuser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

    }

    public void launchPlay(View view) {
        Intent intent = new Intent(this, Gameplay.class);
        startActivity(intent);
    }

    public void launchNew(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
