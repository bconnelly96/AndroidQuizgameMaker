package edu.temple.quizgame.game_ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import edu.temple.quizgame.R;

/*
*A Fragment representing a True False question for
*the QuestionAdder parent.
*Contains public methods so parent can notify Fragment when
*certain Button clicks occur.
*Contains an interface so Fragment can send parent user input
*for a given question to add to the quiz
*/
public class TFAdderFragment extends Fragment {
    private final String EMPTY_FIELD = "";

    EditText qText;
    RadioGroup correct;

    TFInterface TFListener;

    public TFAdderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TFListener = (TFInterface) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tf_adder_fragment_layout, container, false);
        qText = v.findViewById(R.id.editTextQTF);
        correct = v.findViewById(R.id.tf_a_rg);
        return v;
    }

    public interface TFInterface {
        void sendTFQ(String question);
        void sendTFCorrect(boolean correctAnswer);
    }

    /*Called by parent when next button is clicked (user wishes to submit current question)
     *returns true if user has entered valid input for each field*/
    public boolean nextClicked() {
        if (allFieldsValid()) {
            TFListener.sendTFCorrect(getCorrectAnswer());
            TFListener.sendTFQ(getQuestion());
            clearFields();
            return true;
        }
        return false;
    }

    /*Called by parent when the done button is clicked
    *(user wishes to finish the question adding session).
    *Returns true if either all fields have valid input
    *or if no input has been entered*/
    public boolean doneClicked() {
        if (allFieldsValid() || allFieldsBlank()) {
            if (allFieldsValid()) {
                TFListener.sendTFCorrect(getCorrectAnswer());
                TFListener.sendTFQ(getQuestion());
            }
            return true;
        }
        return false;
    }

    /*Returns true if some RadioButton has been selected
    *and the EditText element for the question is not blank*/

     public boolean allFieldsValid() {

        if ((correct.getCheckedRadioButtonId() != -1) && !(getQuestion().equals(EMPTY_FIELD))) {
            return true;
        }
        return false;
    }

    /*Returns true if no RadioButton has been selected
     *and the EditText element for the question is blank*/
    private boolean allFieldsBlank() {
        if ((correct.getCheckedRadioButtonId() == -1) && (getQuestion().equals(EMPTY_FIELD))) {
            return true;
        }
        return false;
    }

    // Resets the input fields for the UI elements
    private void clearFields() {
        int id = correct.getCheckedRadioButtonId();
        RadioButton rb = correct.findViewById(id);
        rb.setChecked(false);

        qText.setText(EMPTY_FIELD);
    }

    // Returns the question from the EditText element
    private String getQuestion() {
        return qText.getText().toString();
    }

    /*Returns true if the first RadioButton in correct has
    *been selected, false otherwise*/
    private boolean getCorrectAnswer() {
        RadioButton rb = getView().findViewById(R.id.radioButton5);
        if (rb.isChecked()) {
            return true;
        }
        return false;
    }
}