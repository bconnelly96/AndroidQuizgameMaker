package edu.temple.quizgame.game_logic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_ui.MainMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent menuIntent = new Intent(this, MainMenu.class);
        startActivity(menuIntent);
    }
}
