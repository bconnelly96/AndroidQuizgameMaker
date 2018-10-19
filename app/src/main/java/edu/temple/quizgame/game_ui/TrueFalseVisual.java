package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import edu.temple.quizgame.R;

public class TrueFalseVisual extends AppCompatActivity {

    /*For display of a question*/
    TextView questionTextView;
    /*radio button for user's response to a T/F question*/
    RadioButton trueFalseRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);
    }

    /*sets the question UI element*/
    public void setQuestionVisual(String stringToDisplay) {

    }

    /*sets the multiple choice button UI elements*/
    public void setRadioButtonVisual() {

    }
}
