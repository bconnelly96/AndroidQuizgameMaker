package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import edu.temple.quizgame.R;

public class QuestionAdder extends AppCompatActivity {
    EditText quizName;
    Switch questionChoice;
    RadioButton trueFalseSelector;
    TextView[] multipleChoiceQuestionFields;
    Button doneButton;
    Button nextQuestionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_adder);

    }

    ArrayList addAnswersFromUser() {

    }

    String addQuestionFromUser() {

    }

    String addQuizName() {

    }

    //Data addData() {
    //}

        
}
