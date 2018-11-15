package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.temple.quizgame.R;

public class EndMenu extends AppCompatActivity {

    @Override
    TextView etext;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_menu);

        int qnum;
        int cnum;

        Intent intent3 = getIntent();

        qnum = (int) intent3.getIntExtra("num_Questions");

        cnum = (int) intent3.getIntExtra("num_Correct_Questions");

        etext = findViewById(R.id.textView2);

        etext.setText("You got "+ cnum + "out of "+ qnum+ "correct.");


    }
}
