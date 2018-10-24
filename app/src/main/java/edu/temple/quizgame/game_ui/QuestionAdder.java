package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import edu.temple.quizgame.game_logic.QuizSession;
import edu.temple.quizgame.R;

public class QuestionAdder extends AppCompatActivity {
    /*UI Elements for user interaction*/
    EditText quizName;
    EditText questionField;
    Switch questionChoice;
    RadioButton trueFalseSelector;
    EditText[] multipleChoiceAnswerFields;
    TextView tf, mc;
    Button doneButton;
    Button nextQuestionButton;
    /*game logic variables; bridge between UI and logic components of application*/
    QuizSession quizSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_adder);

    }

    /*retrieves and returns user's question from questionField EditText*/
    String getUserQuestion() {

    }

    /*retrieves and returns user's answers from multipleChoiceAnswerFields EditText[] for M-C question*/
    ArrayList<String> getMCAnswerFields() {

    }

    /*retrieves and returns user's T/F selection from trueFalseSelector RadioButton*/
    boolean getTFSelector() {

    }

    /*retrieves and returns user's Quiz Name selection from quizName EditText*/
    String getQuizName() {

    }

    /*determines and returns whether the current question is T/F or M-C based on user selection on questionChoice Switch*/
    boolean isTrueFalse() {

    }
}
