package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.MainActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button createGameButton = (Button) findViewById(R.id.c_g_menu_button);
        Button playGameButton = (Button) findViewById(R.id.p_g_menu_button);

        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainMenu.this, QuestionAdder.class);
                startActivity(createIntent);
            }
        });

        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(MainMenu.this, GameActivity.class);
                startActivity(playIntent);
            }
        });
    }
}