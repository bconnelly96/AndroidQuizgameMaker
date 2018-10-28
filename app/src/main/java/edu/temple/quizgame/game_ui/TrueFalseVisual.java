package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.temple.quizgame.R;
import edu.temple.quizgame.game_logic.TrueFalseQuestion;

public class TrueFalseVisual extends AppCompatActivity {

    /*For display of a question*/
    TextView qTextView;
    /*radio button for user's response to a T/F question*/
    RadioGroup tfRadioGroup;
    /*Reference to the question's logical attributes*/
    TrueFalseQuestion tFQuestionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);

        //TODO implement parcleable class to pass TrueFalseQuestion object to activity;
        //TODO receive TrueFalseQuestion object from Intent extra

        qTextView = findViewById(R.id.tf_textview);
        tfRadioGroup = findViewById(R.id.true_false_rg)
    }

    /*sets the question UI element*/
    public void setQuestionVisual(String stringToDisplay) {
        qTextView.setText(stringToDisplay);
    }

    /*retrieves the user's T/F selection from the class' radio button*/
    public boolean getUserChoice() {
        return true;
    }
}
