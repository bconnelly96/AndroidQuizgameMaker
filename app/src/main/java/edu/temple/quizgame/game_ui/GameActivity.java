package edu.temple.quizgame.game_ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.QuizSession;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ArrayList list = new ArrayList<String>();
        list.add("hello1");
        list.add("hello2");
        list.add("hello3");
        list.add("hello4");
        MultipleChoiceQuestion mcq = new MultipleChoiceQuestion("testq", "hello1", list);
        Intent i = new Intent(this, MultipleChoiceVisual.class);
        i.putExtra("question_obj", mcq);
        startActivity(i);

    }

}
