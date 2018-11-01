package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.temple.quizgame.game_logic.*;

import java.util.ArrayList;

import edu.temple.quizgame.R;

public class MultipleChoiceVisual extends AppCompatActivity {
    final int NUM_BUTTONS = 4;
    /*Each Multiple choice question has four possible answers and thus four possible answerButtons*/
    Button[] answerButtons = new Button[4];
    /*For display of a question*/
    TextView questionTextView;
    /*Reference to the question's logical attributes*/
    MultipleChoiceQuestion mCQuestionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
    }

    /*public constructor initializes UI element objects and the M-C Question reference*/
    public MultipleChoiceVisual(MultipleChoiceQuestion mcQuestionRef) {
        questionTextView = findViewById(R.id.mc_textView);
        answerButtons[0] = findViewById(R.id.mc_button1);
        answerButtons[1] = findViewById(R.id.mc_button2);
        answerButtons[2] = findViewById(R.id.mc_button3);
        answerButtons[3] = findViewById(R.id.mc_button4);
        this.mCQuestionRef = mcQuestionRef;
    }

    /*sets the question UI element*/
    public void setQuestionText(String stringToDisplay) {
        questionTextView.setText(stringToDisplay);
    }

    /*sets the multiple choice button UI elements*/
    public void setButtonText(String ... stringsToDisplay) {
        for(int i = 0; i < NUM_BUTTONS; i++) {
            answerButtons[i].setText(stringsToDisplay[i]);
        }
    }

    //TODO: finish this method
    /*retrieves & returns the user's selection from the answer buttons*/
    public String getUserChoice() {
        String userChoice = "";

        for (int i = 0; i < NUM_BUTTONS; i++) {
            final int j = i;
            answerButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userChoice = answerButtons[i].getText();
                }
            });
        }
        return userChoice;
    }

}
