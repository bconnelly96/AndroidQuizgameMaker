package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.temple.quizgame.R;

/*
* Launcher activity for the application.
* Contains two buttons that launch either a game creation activity
* or a game play activity
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createGameButton = (Button) findViewById(R.id.c_g_menu_button);
        Button playGameButton = (Button) findViewById(R.id.p_g_menu_button);

        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainActivity.this, QuestionAdder.class);
                startActivity(createIntent);
            }
        });

        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playIntent = new Intent(MainActivity.this, QuizPicker.class);
                startActivity(playIntent);
            }
        });
    }
}
