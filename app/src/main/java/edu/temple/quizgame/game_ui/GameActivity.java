package edu.temple.quizgame.game_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.MultipleChoiceQuestion;
import edu.temple.quizgame.game_logic.Question;
import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

public class GameActivity extends AppCompatActivity {
    ListView questionList;

    int numQuestions;
    int numCompletedQuestions = 0;
    int numCorrectQuestions = 0;
    int REQUST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Bring quizSession object from QuizPicker activity into memory
        final Intent qsIntent = getIntent();
        final QuizSession quizSession = (QuizSession) qsIntent.getSerializableExtra("selected_quiz");
        if(quizSession != null){
            numQuestions = quizSession.numQuestions;
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
                if (numCompletedQuestions < numQuestions) {
                    /*quizQuestions indices align with position values in questionList,
                     *i.e. questionList[0] references the question String found in quizQuestions[0]*/
                    Question question = quizSession.quizQuestions.get(position);
                    Intent intent;
                    // if the selected question is T/F
                    if (question instanceof TrueFalseQuestion) {
                        TrueFalseQuestion tQuestion = (TrueFalseQuestion) question;
                        intent = new Intent(GameActivity.this, TrueFalseVisual.class);
                        intent.putExtra("tf_obj", tQuestion);
                        startActivityForResult(intent, REQUST_CODE);
                        // if the selected question is MC
                    } else {
                        MultipleChoiceQuestion mQuestion = (MultipleChoiceQuestion) question;
                        intent = new Intent(GameActivity.this, MultipleChoiceVisual.class);
                        intent.putExtra("mc_obj", mQuestion);
                        startActivityForResult(intent, REQUST_CODE);
                    }
                } else {
                    Intent endIntent = new Intent(GameActivity.this, EndMenu.class);
                    endIntent.putExtra("num_Questions", numCompletedQuestions);
                    endIntent.putExtra("num_Correct_Questions", numCorrectQuestions);
                    startActivity(endIntent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent recIntent) {
        if (requestCode == REQUST_CODE) {
            // Check answer
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Your Answer Was Correct", Toast.LENGTH_SHORT).show();
                numCorrectQuestions++;
            } else {

                Toast.makeText(this, "Your Answer Was Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
        numCompletedQuestions++;
    }
}