package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
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
                if(numCompletedQuestions < quizSession.numQuestions){
                    if(question instanceof TrueFalseQuestion){
                        TrueFalseQuestion temp = (TrueFalseQuestion) question;
                        Intent intent = new Intent(GameActivity.this, TrueFalseVisual.class);
                        intent.putExtra("tf_obj", question);
                        startActivity(intent);
                    }
                    else{
                        MultipleChoiceQuestion temp = (MultipleChoiceQuestion) question;
                        Intent intent = new Intent(GameActivity.this, MultipleChoiceVisual.class);
                        intent.putExtra("mc_obj", question);
                        startActivity(intent);
                    }
                    Intent newintent = getIntent();
                    if(question instanceof TrueFalseQuestion){
                        boolean b = newintent.getBooleanExtra("tf_answer", false);
                        if(b == (boolean) quizSession.quizQuestions.get(position).getCorrectAnswer()){
                            numCorrectQuestions++;
                        }
                    }
                    else{
                        String s = newintent.getStringExtra("mc_answer");
                        if(s.equals(quizSession.quizQuestions.get(position).getCorrectAnswer())){
                            numCorrectQuestions++;
                        }
                    }
                    numCompletedQuestions++;
                }
                else{
                    Intent intent3 = new Intent(GameActivity.this, EndMenu.class);
                    intent3.putExtra("num_Correct_Questions", numCorrectQuestions);
                    intent3.putExtra("num_Questions", quizSession.numQuestions);
                    startActivity(intent3);
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