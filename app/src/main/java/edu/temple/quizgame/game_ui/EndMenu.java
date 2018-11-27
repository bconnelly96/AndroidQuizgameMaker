package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import edu.temple.quizgame.R;

// Simple End Menu displays user's basic statistics for completed quiz
public class EndMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_menu);

        TextView etext;

        int qNum;
        int cNum;

        Intent recIntent = getIntent();
        qNum = recIntent.getIntExtra("num_Questions", 0);
        cNum = recIntent.getIntExtra("num_Correct_Questions", 0);
        String text = "You got %d out of %d correct.";
        text = String.format(Locale.getDefault(),text, cNum,qNum);
        etext = findViewById(R.id.textView2);
        etext.setText(text);

        Button resetButton = (Button) findViewById(R.id.button_reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(EndMenu.this, MainActivity.class);
                startActivity(createIntent);
            }
        });
    }
}