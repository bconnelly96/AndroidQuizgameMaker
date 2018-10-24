package edu.temple.quizgame.game_ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
import edu.temple.quizgame.R;

=======
import android.widget.Button;
import android.widget.TextView;
import edu.temple.quizgame.game_logic.*;

import java.util.ArrayList;

import edu.temple.quizgame.R;
>>>>>>> 7590683b9f5623b91996f3319573b8ece8966e14

public class MultipleChoiceVisual extends AppCompatActivity {

    /*Each Multiple choice question has an ArrayList of buttons to support an invariable number of answers*/
    ArrayList<Button> answerButtons;
    /*For display of a question*/
    TextView questionTextView;
    /*Reference to the question's logical attributes*/
    MultipleChoiceQuestion mCQuestionRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
    }

    /*sets the question UI element*/
    public void setQuestionVisual(String stringToDisplay) {

    }

    /*sets the multiple choice button UI elements*/
    public void setButtonVisual(String ... stringsToDisplay) {

    }

    /*retrieves & returns the user's T/F selection from the class' radio button*/
    public String getUserChoice() {

    }

}
