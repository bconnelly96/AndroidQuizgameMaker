package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Button;


import edu.temple.quizgame.R;

public class MainMenu extends AppCompatActivity {
    /*buttons to play or create a game*/
    private Button createGameButton, playGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        createGameButton = (Button) findViewById(R.id.c_g_menu_button);
        playGameButton = (Button) findViewById(R.id.p_g_menu_button);
    }
}