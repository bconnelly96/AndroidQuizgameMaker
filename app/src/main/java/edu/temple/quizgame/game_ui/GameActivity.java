package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

//TODO: note code will not compile until quizSession can be loaded from memory

public class GameActivity extends AppCompatActivity {
    QuizSession quizSession;

    ListView questionList;

    int numCompletedQuestions = 0;
    int numCorrectQuestions = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //TODO: load quizSession here, before setting adapter

        questionList = findViewById(R.id.q_select_list);
        QListAdapter adapter = new QListAdapter(this, listContents());
        questionList.setAdapter(adapter);

        questionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question question = quizSession.quizQuestions.get(position);
                if (question instanceof TrueFalseQuestion) {
                    TrueFalseQuestion temp = (TrueFalseQuestion) question;
                    //start TrueFalseVisual activity
                } else {

                }

            }
        });
    }

    /*Returns a String array, where each index contains
    *the questions from the Question objects found in the ArrayList
    *quizQuestions in GameActivity's quizSession object*/
    private String[] listContents() {
        String [] lContents = new String[quizSession.numQuestions];
        for (int i = 0; i < quizSession.numQuestions; i++) {
            lContents[i] = quizSession.quizQuestions.get(i).getQuestion();
        }
        return lContents;
    }
}