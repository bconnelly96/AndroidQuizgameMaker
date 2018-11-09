package edu.temple.quizgame.game_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import edu.temple.quizgame.R;

//TODO init UI elems
public class MCAdderFragment extends Fragment {

    private final int NUM_BUTTONS = 4;

    EditText[] answers;
    RadioGroup correct;
    EditText qText;

    MCInterface MCListener;

    public MCAdderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MCListener = (MCInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.mc_adder_fragment_layout, container, false);
        return v;
    }

    public interface MCInterface {
        void sendQ(String question);
        void sendAnswers(String[] answers);
        void sendCorrect(String correctAnswer);
    }

    /*Sends question data to the parent if its next button has been clicked
    * returns true if user has entered valid input for each field*/
    public boolean nextClicked() {
        if (allFieldsValid()) {
            MCListener.sendAnswers(getAnswers());
            MCListener.sendQ(getQuestion());
            MCListener.sendCorrect(getCorrectAnswer());
            clearFields();
            return true;
        }
        return false;
    }

    public boolean doneClicked() {

    }

    private boolean allFieldsValid() {

    }

    private void clearFields() {

    }

    private String getQuestion() {
        return qText.getText().toString();
    }

    private String[] getAnswers() {
        String [] answerStrings = new String[4];
        for (int i = 0; i < NUM_BUTTONS; i++) {
            answerStrings[i] = answers[i].getText().toString();
        }
        return answerStrings;
    }

    private String getCorrectAnswer() {

    }


}
