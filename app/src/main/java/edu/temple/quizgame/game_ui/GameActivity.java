package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

//TODO: note code will not compile until quizSession can be loaded from memory

public class GameActivity extends AppCompatActivity {
    QuizSession quizSession;
    ListView questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questionList = findViewById(R.id.q_select_list);
        QListAdapter adapter = new QListAdapter(this, listContents());
        questionList.setAdapter(adapter);

        questionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent, recIntent;
                Bundle args;
                if (quizSession.quizQuestions.get(position) instanceof TrueFalseQuestion) {
                    intent = new Intent(GameActivity.this, TrueFalseVisual.class);
                    intent.putExtra("tf_obj", quizSession.quizQuestions.get(position));
                    startActivity(intent);

                    args = getIntent().getExtras();
                    boolean answer = args.getBoolean("tf_answer");
                } else {
                    intent = new Intent(GameActivity.this, MultipleChoiceVisual.class);
                    intent.putExtra("mc_obj", quizSession.quizQuestions.get(position));
                    startActivity(intent);

                    args = getIntent().getExtras();
                    String answer = args.getString("mc_answer");


                }
            }
        });


    }

    private String [] listContents() {
        String[] listContents = new String[quizSession.numQuestions];
        for (int i = 0; i < quizSession.numQuestions; i++) {
            listContents[i] = "Question " + Integer.toString(i);
        }
        return listContents;
    }

}
