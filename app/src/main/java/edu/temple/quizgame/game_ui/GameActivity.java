package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

public class GameActivity extends AppCompatActivity {
    QuizSession quizSession;

    ListView questionList;

    int numCompletedQuestions = 0;
    int numCorrectQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Bring quizSession object from QuizPicker activity into memory
        Intent qsIntent = getIntent();
        final QuizSession quizSession = (QuizSession) qsIntent.getSerializableExtra("selected_quiz");
        if(quizSession == null){
            System.out.println("ERROR");
        }
        String [] lContents = new String[quizSession.numQuestions];
        for (int i = 0; i < quizSession.numQuestions; i++) {
            lContents[i] = quizSession.quizQuestions.get(i).getQuestion();
        }

        questionList = findViewById(R.id.q_select_list);
        QListAdapter adapter = new QListAdapter(this, lContents);
        questionList.setAdapter(adapter);

        questionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*quizQuestions indices align with position values in questionList,
                *i.e. questionList[0] references the question String found in quizQuestions[0]*/
                Question question = quizSession.quizQuestions.get(position);
                // if there are still open questions
                if (numCompletedQuestions < quizSession.numQuestions) {
                    Intent intent;
                    // if the selected question is T/F
                    if (question instanceof TrueFalseQuestion) {
                        TrueFalseQuestion tQuestion = (TrueFalseQuestion) question;
                        intent = new Intent(GameActivity.this, TrueFalseVisual.class);
                        intent.putExtra("tf_obj", tQuestion);
                        startActivity(intent);
                        // if the selected question is MC
                    } else {
                        MultipleChoiceQuestion mQuestion = (MultipleChoiceQuestion) question;
                        intent = new Intent(GameActivity.this, MultipleChoiceVisual.class);
                        intent.putExtra("mc_obj", mQuestion);
                        startActivity(intent);
                    }
                    /*receive the user's selected value via extra data;
                     *adjust number of correct answers and completed questions accordingly*/
                    Intent recIntent = getIntent();
                    if (question instanceof TrueFalseQuestion) {
                        boolean tCorrect = recIntent.getBooleanExtra("tf_answer", false);
                        if (tCorrect == (boolean) quizSession.quizQuestions.get(position).getCorrectAnswer()) {
                            numCorrectQuestions++;
                        } else {
                            String mCorrect = recIntent.getStringExtra("mc_answer");
                            if (mCorrect != null) {
                                if (mCorrect.equals(quizSession.quizQuestions.get(position).getCorrectAnswer())) {
                                    numCorrectQuestions++;
                                }
                            }
                        }
                    }
                    numCompletedQuestions++;
                // when all questions have been answered, display an end menu
                } else {
                    Intent doneIntent = new Intent(GameActivity.this, EndMenu.class);
                    doneIntent.putExtra("num_Correct_Questions", numCorrectQuestions);
                    doneIntent.putExtra("num_Questions", quizSession.numQuestions);
                    startActivity(doneIntent);
                }
            }

        });
    }
    /*Returns a String array, where each index contains
    *the questions from the Question objects found in the ArrayList
    *quizQuestions in GameActivity's quizSession object*/
  /*  private String[] listContents() {
        String [] lContents = new String[quizSession.numQuestions];
        for (int i = 0; i < quizSession.numQuestions; i++) {
            lContents[i] = quizSession.quizQuestions.get(i).getQuestion();
        }
        return lContents;
    }*/
}