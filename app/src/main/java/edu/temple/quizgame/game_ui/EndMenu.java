package edu.temple.quizgame.game_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.temple.quizgame.R;

// Simple End Menu displays user's basic statistics for completed quiz
public class EndMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_menu);

        TextView etext;
        Button button;

        int qNum;
        int cNum;

        Intent recIntent = getIntent();
        qNum = recIntent.getIntExtra("num_Questions", 0);
        cNum = (int) recIntent.getIntExtra("num_Correct_Questions", 0);
        etext = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        etext.setText("You got "+ cNum + "out of "+ qNum+ "correct.");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}